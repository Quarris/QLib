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

        BlockRegistryHandler handler = BlockRegistryHandler.HANDLERS.get(block);

        if (handler == null)
            return;

        BlockItem item = handler.blockItem.apply(block);
        if (item != null) {
            if (item.getRegistryName() == null) {
                item.setRegistryName(block.getRegistryName());
            }

            QLibApi.ITEMS.add(item);

            ItemRegistryHandler blockItemHandler = ItemRegistryHandler.HANDLERS.get(item);

            if (blockItemHandler != null) {
                ModelDataHandler.ITEMS.put(modId, blockItemHandler);
            }
        }

        ModelDataHandler.BLOCKS.put(modId, handler);
        // TODO Add ItemRegistryHandler for the BlockItem
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
