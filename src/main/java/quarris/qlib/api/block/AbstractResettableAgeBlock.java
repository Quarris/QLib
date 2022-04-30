package quarris.qlib.api.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import quarris.qlib.api.util.Utils;

import java.util.Random;

public abstract class AbstractResettableAgeBlock extends Block {

    public final int maxAge = Utils.maxFromIntProp(this.getAgeProp());

    public AbstractResettableAgeBlock(Properties properties) {
        super(properties);
    }

    public abstract IntegerProperty getAgeProp();
    public abstract ItemStack getItemDrop(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult ray);

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(this.getAgeProp());
    }


    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!world.isClientSide()) {
            if (state.getValue(this.getAgeProp()) == this.maxAge) {
                this.harvest(state, world, pos, player, hit);
                return InteractionResult.CONSUME;
            }
        }
        return InteractionResult.PASS;
    }

    public void harvest(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit) {
        if (!world.isClientSide()) {
            world.setBlock(pos, state.setValue(this.getAgeProp(), 0), 3);
            ItemStack item = this.getItemDrop(state, world, pos, player, hit);
            ItemEntity drop = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), item);
            world.addFreshEntity(drop);
        }
    }


    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return state.getValue(this.getAgeProp()) < this.maxAge;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, Random random) {
        int i = state.getValue(this.getAgeProp());
        if (i < this.maxAge) {
            level.setBlock(pos, state.setValue(this.getAgeProp(), i + 1), 2);
        }
    }
}
