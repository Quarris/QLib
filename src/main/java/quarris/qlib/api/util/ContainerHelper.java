package quarris.qlib.api.util;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ContainerHelper {

    /**
     * Adds player slots to a container dedicated by the addSlot function.
     *
     * @param addSlot   The function equal to {@link net.minecraft.inventory.container.Container#addSlot(Slot)}
     * @param playerInv the inventory of the player to add the slots contents.
     * @param x         The left position of the slots.
     * @param y         The top position of the slots.
     * @return The list of slots that were added. In order of right to left, top to bottom.
     */
    public static List<Slot> addPlayerSlots(Function<Slot, Slot> addSlot, PlayerInventory playerInv, int x, int y) {
        List<Slot> slots = new ArrayList<>();
        // Inventory
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 9; i++) {
                slots.add(addSlot.apply(new Slot(playerInv, i + j * 9 + 9, x + i * 18, y + j * 18)));
            }
        }

        // Hotbar
        for (int k = 0; k < 9; k++) {
            slots.add(addSlot.apply(new Slot(playerInv, k, x + k * 18, y + 58)));
        }

        return slots;
    }

}
