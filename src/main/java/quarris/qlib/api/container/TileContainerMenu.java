package quarris.qlib.api.container;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

public class TileContainerMenu extends AbstractContainerMenu {

    protected final Inventory playerInventory;
    protected final BlockPos pos;
    protected final ItemStackHandler tileInventory;

    protected static final int playerSizeX = 9*18;
    protected static final int playerSizeY = 4*18+4;

    protected TileContainerMenu(@Nullable MenuType<?> type, int id, Inventory playerInventory, BlockPos pos, ItemStackHandler tileInventory) {
        super(type, id);
        this.playerInventory = playerInventory;
        this.pos = pos;
        this.tileInventory = tileInventory;
    }

    @Override
    public boolean stillValid(Player player) {
        return player.distanceToSqr(this.pos.getX() + 0.5, this.pos.getY() + 0.5, this.pos.getZ() + 0.5) < 64.0;
    }

    public void addPlayerSlots(int x, int y) {
        // Inventory
        for(int j = 0; j < 3; j++) {
            for(int i = 0; i < 9; i++) {
                this.addSlot(new Slot(this.playerInventory, i + j * 9 + 9, x + i * 18, y + j * 18));
            }
        }

        // Hotbar
        for(int k = 0; k < 9; k++) {
            this.addSlot(new Slot(this.playerInventory, k, x + k * 18, y + 58));
        }
    }
}
