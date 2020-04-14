package quarris.qlibdemo.block.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

public class ThingTile extends TileEntity {

    public ThingTile() {
        super(ModTiles.THINGY_TILE);
    }

    public ThingTile(TileEntityType<?> type) {
        super(type);
    }

}
