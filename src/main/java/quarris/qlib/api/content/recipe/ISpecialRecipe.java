package quarris.qlib.api.content.recipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;
import quarris.qlib.api.content.container.EmptyContainer;

public interface ISpecialRecipe extends Recipe<EmptyContainer> {

    @Override
    default boolean matches(EmptyContainer pContainer, Level pLevel) {
        return false;
    }

    @Override
    default ItemStack assemble(EmptyContainer pContainer) {
        return ItemStack.EMPTY;
    }

    @Override
    default boolean canCraftInDimensions(int pWidth, int pHeight) {
        return false;
    }

    @Override
    default ItemStack getResultItem() {
        return ItemStack.EMPTY;
    }

    @Override
    default boolean isSpecial() {
        return true;
    }
}
