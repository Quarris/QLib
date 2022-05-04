package quarris.qlib.api.content.recipe;

import net.minecraft.resources.ResourceLocation;
import quarris.qlib.api.content.container.EmptyContainer;

public abstract class SpecialRecipe extends AbstractRecipe<EmptyContainer> implements ISpecialRecipe {

    public SpecialRecipe(ResourceLocation id) {
        super(id);
    }
}
