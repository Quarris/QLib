package quarris.qlib.mod.reg.loader;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import quarris.qlib.api.reg.ContentLoader;
import quarris.qlib.api.reg.registry.BlockRegistry;
import quarris.qlib.api.block.IHasCustomBlockItem;
import quarris.qlib.api.block.IHasNoBlockItem;
import quarris.qlib.mod.data.model.ModelDataHandler;

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
        ModelDataHandler.BLOCKS.put(modId, block);

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
            ModelDataHandler.ITEMS.put(modId, item);
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
