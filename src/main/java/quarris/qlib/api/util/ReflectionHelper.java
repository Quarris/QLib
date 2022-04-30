package quarris.qlib.api.util;

import net.minecraftforge.fml.ModList;
import net.minecraftforge.forgespi.language.ModFileScanData;
import org.objectweb.asm.Type;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class ReflectionHelper {

    public static List<Class<?>> getClassesAnnotatedBy(Class<?> annotation, Consumer<ClassNotFoundException> onError) {
        List<Class<?>> classes = new ArrayList<>();

        ModList.get().getAllScanData().stream()
                .map(ModFileScanData::getAnnotations)
                .flatMap(Collection::stream)
                .filter(type -> type.annotationType().equals(Type.getType(annotation)))
                .map(type -> {
                    try {
                        return Class.forName(type.clazz().getClassName(), true, annotation.getClassLoader());
                    } catch (ClassNotFoundException e) {
                        onError.accept(e);
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .forEach(classes::add);

        return classes;
    }

}
