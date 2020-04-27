package quarris.qlib.api.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import quarris.qlib.api.util.Utils;

import java.util.Random;

public abstract class AbstractResettableAgeBlock extends Block {

    public final int maxAge = Utils.maxFromIntProp(this.getAgeProp());

    public AbstractResettableAgeBlock(Properties properties) {
        super(properties);
    }

    public abstract IntegerProperty getAgeProp();
    public abstract ItemStack getItemDrop(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockRayTraceResult ray);

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(this.getAgeProp());
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult ray) {
        if (!world.isRemote) {
            if (state.get(this.getAgeProp()) == this.maxAge) {
                this.reset(state, world, pos, player, ray);
            }
        }
        return ActionResultType.PASS;
    }

    public void reset(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockRayTraceResult ray) {
        if (!world.isRemote) {
            world.setBlockState(pos, state.with(this.getAgeProp(), 0), 2);
            ItemStack item = this.getItemDrop(state, world, pos, player, ray);
            ItemEntity drop = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), item);
            world.addEntity(drop);
        }
    }

    @Override
    public boolean ticksRandomly(BlockState state) {
        return state.get(this.getAgeProp()) < this.maxAge;
    }

    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        int i = state.get(this.getAgeProp());
        if (i < this.maxAge) {
            worldIn.setBlockState(pos, state.with(this.getAgeProp(), i + 1), 2);
        }
    }
}
