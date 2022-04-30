package quarris.qlib.mod.registry.loader;

import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import quarris.qlib.api.registry.ContentLoader;
import quarris.qlib.api.registry.registry.BlockRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class BlockLoader extends ContentLoader<Block, BlockRegistry> {

    public final Map<String, DeferredRegister<Block>> registers = new HashMap<>();

    @Override
    protected void loadContent(String modId, String name, Supplier<Block> blockSupplier) {
        DeferredRegister<Block> registry = registers.computeIfAbsent(modId, id -> DeferredRegister.create(ForgeRegistries.BLOCKS, id));
        registry.register(name, blockSupplier);

        /*BlockRegistryHandler.HANDLERS.putIfAbsent(blockSupplier, BlockRegistryHandler.get(blockSupplier));
        BlockRegistryHandler handler = BlockRegistryHandler.HANDLERS.get(blockSupplier);

        BlockItem item = handler.blockItem;
        if (item != null) {
            if (item.getRegistryName() == null) {
                item.setRegistryName(blockSupplier.getRegistryName());
            }

            QLibApi.ITEMS.add(item);

            ItemRegistryHandler.HANDLERS.putIfAbsent(item, ItemRegistryHandler.get(item));
        }*/
    }

    @Override
    protected Class<Block> getContentClass() {
        return Block.class;
    }

    @Override
    protected Class<BlockRegistry> getRegistryClass() {
        return BlockRegistry.class;
    }
}
