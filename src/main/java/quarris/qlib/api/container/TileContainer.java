package quarris.qlib.api.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

public class TileContainer extends Container {

    protected final PlayerInventory playerInv;
    protected final BlockPos tilePos;
    protected final ItemStackHandler tileInventory;

    protected static final int playerSizeX = 9*18;
    protected static final int playerSizeY = 4*18+4;

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

    public void addPlayerSlots(int x, int y) {
        // Inventory
        for(int j = 0; j < 3; j++) {
            for(int i = 0; i < 9; i++) {
                this.addSlot(new Slot(this.playerInv, i + j * 9 + 9, x + i * 18, y + j * 18));
            }
        }

        // Hotbar
        for(int k = 0; k < 9; k++) {
            this.addSlot(new Slot(this.playerInv, k, x + k * 18, y + 58));
        }
    }

}
