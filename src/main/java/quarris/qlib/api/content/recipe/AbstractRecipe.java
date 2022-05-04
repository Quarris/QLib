package quarris.qlib.api.content.recipe;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.Recipe;

public abstract class AbstractRecipe<C extends Container> implements Recipe<C> {

    private final ResourceLocation id;

    public AbstractRecipe(ResourceLocation id) {
        this.id = id;
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }
}
