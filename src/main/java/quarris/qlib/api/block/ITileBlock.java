package quarris.qlib.api.block;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;

public interface ITileBlock<T extends TileEntity> {

    default T getTile(IWorldReader world, BlockPos position) {
        TileEntity tile = world.getTileEntity(position);
        try {
            return tile == null ? null : (T) tile;
        } catch (ClassCastException e) {
            return null;
        }
    }

}
