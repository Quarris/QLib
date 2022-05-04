package quarris.qlib.api.content.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;

public interface IBlockEntityProvider<T extends BlockEntity> extends EntityBlock {

    @SuppressWarnings("unchecked")
    default T getTile(Level level, BlockPos pos) {
        return (T) level.getBlockEntity(pos);
    }

}
