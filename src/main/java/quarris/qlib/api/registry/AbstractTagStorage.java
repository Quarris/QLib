package quarris.qlib.api.registry;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;

public class AbstractTagStorage<T> {

    private final String modid;
    private final ResourceKey<Registry<T>> key;

    public AbstractTagStorage(String modid, ResourceKey<Registry<T>> key) {
        this.modid = modid;
        this.key = key;
    }

    protected TagKey<T> create(String name) {
        return TagKey.create(this.key, new ResourceLocation(this.modid, name));
    }
}
