package quarris.qlib.api.block;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

public interface ITileProvider<T extends TileEntity> {

    @SuppressWarnings("unchecked")
    default T getTile(IWorld world, BlockPos pos) {
        return (T) world.getTileEntity(pos);
    }

}
