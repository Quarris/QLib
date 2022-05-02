package quarris.qlib.api.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;

public interface IBlockEntityProvider<T extends BlockEntity> extends EntityBlock {

    @SuppressWarnings("unchecked")
    default T getTile(Level world, BlockPos pos) {
        return (T) world.getBlockEntity(pos);
    }

}
