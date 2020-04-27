package quarris.qlib.api.util.extension;

import net.minecraft.util.ResourceLocation;

// Resource Location extension methods
public class RL {

    public static ResourceLocation suffix(ResourceLocation location, String suffix) {
        return new ResourceLocation(location.getNamespace(), location.getPath() + suffix);
    }

    public static ResourceLocation prefix(String prefix, ResourceLocation location) {
        return new ResourceLocation(location.getNamespace(), prefix + location.getPath());
    }

}
