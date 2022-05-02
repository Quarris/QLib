package quarris.qlib.api.registry;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

import java.util.Arrays;

public class AbstractBlockEntityTypeRegistry extends ContentRegistry<BlockEntityType<?>> {

    public AbstractBlockEntityTypeRegistry(String modid, IForgeRegistry<BlockEntityType<?>> registry) {
        super(modid, registry);
    }

    @SafeVarargs
    protected final <T extends BlockEntity> RegistryObject<BlockEntityType<T>> register(String name, BlockEntityType.BlockEntitySupplier<T> tileSupp, RegistryObject<Block>... validBlocks) {
        return this.register(name, () -> BlockEntityType.Builder.of(tileSupp, Arrays.stream(validBlocks).map(RegistryObject::get).toArray(Block[]::new)).build(null));
    }
}
