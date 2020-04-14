package quarris.qlib.common.block;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public interface IHasCustomBlockItem {

    default BlockItem createItem() {
        return new BlockItem((Block) this, new Item.Properties());
    }

}
