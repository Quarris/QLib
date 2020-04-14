package quarris.qlib.common.reg.loader;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import quarris.qlib.common.reg.ContentLoader;
import quarris.qlib.common.reg.registry.BlockRegistry;
import quarris.qlib.common.block.IHasCustomBlockItem;
import quarris.qlib.common.block.IHasNoBlockItem;

import java.util.ArrayList;
import java.util.List;

public class BlockLoader extends ContentLoader<Block, BlockRegistry> {

    public final List<Block> BLOCKS = new ArrayList<>();
    public final List<Item> BLOCK_ITEMS = new ArrayList<>();

    @Override
    protected void loadContent(String modId, String name, Block block) {
        if (BLOCKS.contains(block))
            return;

        if (block.getRegistryName() == null) {
            block.setRegistryName(modId, name);
        }
        BLOCKS.add(block);

        if (!(block instanceof IHasNoBlockItem)) {
            BlockItem item;
            if (block instanceof IHasCustomBlockItem) {
                item = ((IHasCustomBlockItem) block).createItem();
            } else {
                item = new BlockItem(block, new Item.Properties());
            }

            if (item.getRegistryName() == null) {
                item.setRegistryName(block.getRegistryName());
            }

            BLOCK_ITEMS.add(item);
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
