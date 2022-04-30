package quarris.qlib.mod.registry.loader;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import quarris.qlib.api.registry.ContentLoader;
import quarris.qlib.api.registry.registry.TileRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class BlockEntityLoader extends ContentLoader<BlockEntityType, TileRegistry> {

    public final Map<String, DeferredRegister<BlockEntityType<?>>> registers = new HashMap<>();

    @Override
    protected void loadContent(String modId, String name, Supplier<BlockEntityType> blockEntitySupplier) {
        DeferredRegister<BlockEntityType<?>> registry = registers.computeIfAbsent(modId, id -> DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, id));
        registry.register(name, blockEntitySupplier);
    }

    @Override
    protected Class<BlockEntityType> getContentClass() {
        return BlockEntityType.class;
    }

    @Override
    protected Class<TileRegistry> getRegistryClass() {
        return TileRegistry.class;
    }
}
