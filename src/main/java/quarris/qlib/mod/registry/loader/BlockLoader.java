package quarris.qlib.mod.registry.loader;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import quarris.qlib.api.QLibApi;
import quarris.qlib.api.data.BlockRegistryHandler;
import quarris.qlib.api.data.ItemRegistryHandler;
import quarris.qlib.api.registry.ContentLoader;
import quarris.qlib.api.registry.registry.BlockRegistry;
import quarris.qlib.mod.data.ModelDataHandler;

public class BlockLoader extends ContentLoader<Block, BlockRegistry> {

    @Override
    protected void loadContent(String modId, String name, Block block) {
        if (QLibApi.BLOCKS.contains(block))
            return;

        if (block.getRegistryName() == null) {
            block.setRegistryName(modId, name);
        }
        QLibApi.BLOCKS.add(block);

        BlockRegistryHandler.HANDLERS.putIfAbsent(block, BlockRegistryHandler.get(block));
        BlockRegistryHandler handler = BlockRegistryHandler.HANDLERS.get(block);

        BlockItem item = handler.blockItem;
        if (item != null) {
            if (item.getRegistryName() == null) {
                item.setRegistryName(block.getRegistryName());
            }

            QLibApi.ITEMS.add(item);

            ItemRegistryHandler.HANDLERS.putIfAbsent(item, ItemRegistryHandler.get(item));
        }
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
