package quarris.qlib.mod.registry.loader;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import quarris.qlib.api.QLibApi;
import quarris.qlib.api.data.BlockRegistryObject;
import quarris.qlib.api.data.ItemRegistryObject;
import quarris.qlib.api.registry.ContentLoader;
import quarris.qlib.api.registry.annotations.BlockRegistry;

public class BlockLoader extends ContentLoader<Block, BlockRegistry> {

    @Override
    protected void loadContent(String modId, String name, Block block) {
        if (QLibApi.BLOCKS.contains(block))
            return;

        if (block.getRegistryName() == null) {
            block.setRegistryName(modId, name);
        }
        QLibApi.BLOCKS.add(block);

        BlockRegistryObject.HANDLERS.putIfAbsent(block, BlockRegistryObject.get(block));
        BlockRegistryObject handler = BlockRegistryObject.HANDLERS.get(block);

        BlockItem item = handler.blockItem;
        if (item != null) {
            if (item.getRegistryName() == null) {
                item.setRegistryName(block.getRegistryName());
            }

            QLibApi.ITEMS.add(item);

            ItemRegistryObject.HANDLERS.putIfAbsent(item, ItemRegistryObject.get(item));
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
