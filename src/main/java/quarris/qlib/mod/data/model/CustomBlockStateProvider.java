package quarris.qlib.mod.data.model;

import net.minecraft.block.Block;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import quarris.qlib.api.data.model.IHasCustomBlockState;
import quarris.qlib.mod.util.extension.RL;

import java.util.Collection;

public class CustomBlockStateProvider extends BlockStateProvider {

    private final Collection<Block> blocks;

    public CustomBlockStateProvider(GatherDataEvent event, String modid, Collection<Block> blocks) {
        super(event.getGenerator(), modid, event.getExistingFileHelper());
        this.blocks = blocks;
    }

    @Override
    protected void registerStatesAndModels() {
        for (Block block : this.blocks) {
            if (block instanceof IHasCustomBlockState) {
                ((IHasCustomBlockState) block).makeStateAndModel(this);
            } else {
                this.defaultStateAndModel(block);
            }
        }
    }

    // TODO: Make this nicer. If possible have a way to default any state property.
    public void defaultStateAndModel(Block block) {
        StateContainer<?, ?> states = block.getStateContainer();
        if (states.getProperty(BlockStateProperties.AXIS.getName()) != null) {
            this.axisBlock(block);
        } else if (states.getProperty(BlockStateProperties.HORIZONTAL_FACING.getName()) != null) {
            this.horizontalBlock(block);
        } else {
            this.simpleBlock(block);
        }
    }

    // Why does forge not add this on when its been added for the others?????
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
}
