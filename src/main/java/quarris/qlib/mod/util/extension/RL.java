package quarris.qlib.mod.util.extension;

import net.minecraft.util.ResourceLocation;

// Resource Location extension methods
public class RL {

    public static ResourceLocation suffix(ResourceLocation location, String suffix) {
        return new ResourceLocation(location.getNamespace(), location.getPath()+suffix);
    }

}
