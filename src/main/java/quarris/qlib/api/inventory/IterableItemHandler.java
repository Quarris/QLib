package quarris.qlib.api.inventory;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

import java.util.Iterator;

public class IterableItemHandler implements Iterable<ItemStack> {

    private IItemHandler itemHandler;


    public IterableItemHandler(IItemHandler itemHandler) {
        this.itemHandler = itemHandler;
    }

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
