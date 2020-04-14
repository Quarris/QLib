package quarris.qlibdemo.block.tileentity;

import net.minecraft.tileentity.TileEntityType;
import quarris.qlib.common.reg.tileentity.TileRegistry;
import quarris.qlibdemo.QLibDemo;
import quarris.qlibdemo.block.ModBlocks;

@TileRegistry(QLibDemo.MODID)
public class ModTiles {

    public static final TileEntityType<ThingTile> THINGY_TILE = TileEntityType.Builder.create(ThingTile::new, ModBlocks.BLOCK_THING).build(null);

}
