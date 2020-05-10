package quarris.qlib.api.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

public class TileContainer extends Container {

    protected final PlayerInventory playerInv;
    protected final BlockPos tilePos;
    protected final ItemStackHandler tileInventory;

    protected TileContainer(@Nullable ContainerType<?> type, int id, PlayerInventory playerInv, BlockPos tilePos, ItemStackHandler inventory) {
        super(type, id);
        this.playerInv = playerInv;
        this.tilePos = tilePos;
        this.tileInventory = inventory;
    }

    @Override
    public boolean canInteractWith(PlayerEntity player) {
        return player.getDistanceSq(this.tilePos.getX() + 0.5, this.tilePos.getY() + 0.5, this.tilePos.getZ() + 0.5) < 64.0;
    }

}
