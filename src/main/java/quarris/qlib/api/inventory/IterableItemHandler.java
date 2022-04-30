package quarris.qlib.api.inventory;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

import java.util.Iterator;

public record IterableItemHandler(IItemHandler itemHandler) implements Iterable<ItemStack> {

    @Override
    public Iterator<ItemStack> iterator() {
        return new Ite();
    }

    private class Ite implements Iterator<ItemStack> {
        private int index;

        @Override
        public boolean hasNext() {
            return this.index != IterableItemHandler.this.itemHandler.getSlots();
        }

        @Override
        public ItemStack next() {
            return IterableItemHandler.this.itemHandler.getStackInSlot(this.index++);
        }
    }
}
