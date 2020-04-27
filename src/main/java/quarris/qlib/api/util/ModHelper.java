package quarris.qlib.api.util;

import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class ModHelper {

    public static void switchActiveContainer(String namespace) {
        if (!ModLoadingContext.get().getActiveNamespace().equals(namespace)) {
            ModLoadingContext.get().setActiveContainer(ModList.get().getModContainerById(namespace).get(), FMLJavaModLoadingContext.get());
        }
    }
}
