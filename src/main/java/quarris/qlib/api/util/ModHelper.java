package quarris.qlib.api.util;

import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forgespi.language.ModFileScanData;
import org.objectweb.asm.Type;

import java.util.ArrayList;
import java.util.List;

public class ModHelper {

    public static void switchActiveContainer(String namespace) {
        if (!ModLoadingContext.get().getActiveNamespace().equals(namespace)) {
            ModLoadingContext.get().setActiveContainer(ModList.get().getModContainerById(namespace).get(), FMLJavaModLoadingContext.get());
        }
    }

    public static List<Class<?>> getClassesAnnotatedBy(Class<?> annotation) throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();

        for (ModFileScanData modFile : ModList.get().getAllScanData()) {
            for (ModFileScanData.AnnotationData data : modFile.getAnnotations()) {
                if (data.getAnnotationType().equals(Type.getType(annotation))) {
                    classes.add(Class.forName(data.getClassType().getClassName(), true, annotation.getClassLoader()));
                }
            }
        }

        return classes;
    }
}
