package quarris.qlib.api.data.model;

import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.VariantBlockStateBuilder;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import quarris.qlib.api.QLibApi;
import quarris.qlib.api.data.BlockRegistryHandler;
import quarris.qlib.api.util.extension.RL;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.function.Function;

public class CustomBlockStateProvider extends BlockStateProvider {

    private final Collection<BlockRegistryHandler> blocks;

    public CustomBlockStateProvider(GatherDataEvent event, String modid, Collection<BlockRegistryHandler> blocks) {
        super(event.getGenerator(), modid, event.getExistingFileHelper());
        this.blocks = blocks;
    }

    @Override
    protected void registerStatesAndModels() {
        for (BlockRegistryHandler block : this.blocks) {
            block.model.accept(this);
        }
    }

    // TODO: Make this nicer. If possible have a way to default any state property.
    public void defaultStateAndModel(Block block) {
        StateDefinition<?, ?> states = block.getStateDefinition();
        if (states.getProperty(BlockStateProperties.AXIS.getName()) != null) {
            this.axisBlock(block);
        } else if (states.getProperty(BlockStateProperties.HORIZONTAL_FACING.getName()) != null) {
            this.horizontalBlock(block);
        } else {
            this.simpleBlock(block);
        }
    }

    public <T extends Comparable<T>> void forProperty(Block block, Property<T> property, Function<T, ConfiguredModel[]> mapper) {
        VariantBlockStateBuilder builder = this.getVariantBuilder(block);

        for (T value : property.getPossibleValues()) {
            builder.addModels(this.withProp(block, property, value), mapper.apply(value));
        }
    }

    public void crossBlock(Block block) {
        this.getVariantBuilder(block)
                .partialState().addModels(ConfiguredModel.builder()
                .modelFile(this.models()
                        .cross(this.blockName(block), RL.prefix("block/", block.getRegistryName())))
                .build()
        );
    }

    public void cropBlock(Block block) {
        this.models().crop(this.blockName(block), RL.prefix("block/", block.getRegistryName()));
    }

    public <T extends Comparable<T>> VariantBlockStateBuilder.PartialBlockstate withProp(Block block, Property<T> property, T value) {
        return this.getVariantBuilder(block)
                .partialState().with(property, value);
    }

    // Why does forge not add this one when its been added for the others?????
    public void horizontalBlock(Block block) {
        ResourceLocation baseName = blockTexture(block);
        horizontalBlock(block, RL.suffix(baseName, "_side"), RL.suffix(baseName, "_front"), RL.suffix(baseName, "_top"));
    }

    // The original axisBlock methods requires RotatedPillarBlock for no reason at all...
    public void axisBlock(Block block) {
        axisBlock(block, blockTexture(block));
    }

    public void axisBlock(Block block, ResourceLocation baseName) {
        axisBlock(block, RL.suffix(baseName, "_side"), RL.suffix(baseName, "_end"));
    }

    public void axisBlock(Block block, ResourceLocation side, ResourceLocation end) {
        axisBlock(block, models().cubeColumn(blockName(block), side, end));
    }

    public void axisBlock(Block block, ModelFile model) {
        getVariantBuilder(block)
                .partialState().with(BlockStateProperties.AXIS, Direction.Axis.Y)
                .modelForState().modelFile(model).addModel()
                .partialState().with(BlockStateProperties.AXIS, Direction.Axis.Z)
                .modelForState().modelFile(model).rotationX(90).addModel()
                .partialState().with(BlockStateProperties.AXIS, Direction.Axis.X)
                .modelForState().modelFile(model).rotationX(90).rotationY(90).addModel();
    }

    public String blockName(Block block) {
        return block.getRegistryName().getPath();
    }

    @Nonnull
    @Override
    public String getName() {
        return QLibApi.MODID + ":BlockModel";
    }
}
