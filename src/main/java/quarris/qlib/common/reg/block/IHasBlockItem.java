package quarris.qlib.common.reg.block;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public interface IHasBlockItem {

    default BlockItem createItem() {
        return new BlockItem((Block) this, new Item.Properties());
    }

}
