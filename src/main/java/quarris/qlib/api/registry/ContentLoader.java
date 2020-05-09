package quarris.qlib.api.registry;

import quarris.qlib.api.QLibApi;
import quarris.qlib.api.util.ModHelper;
import quarris.qlib.api.util.ReflectionHelper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@SuppressWarnings("unchecked cast")
public abstract class ContentLoader<Content, Registry extends Annotation> {

    public void load() {
        List<Class<?>> registryClasses = new ArrayList<>();
        try {
            registryClasses = ReflectionHelper.getClassesAnnotatedBy(this.getRegistryClass());
        } catch (ClassNotFoundException e) {
            QLibApi.LOGGER.error("Error during registering content", e.getCause());
        }

        if (registryClasses.isEmpty())
            return;

        QLibApi.LOGGER.info("Loading content from {} classes for type {}", registryClasses.size(), this.getContentClass().getName());

        for (Class<?> registryClass : registryClasses) {
            Registry annotation = (Registry) registryClass.getDeclaredAnnotation(this.getRegistryClass());
            String modId;
            try {
                modId = (String) this.getRegistryClass().getMethod("value").invoke(annotation);
            } catch (Exception e) {
                QLibApi.LOGGER.error("Could not find the value() tag for class " + annotation.annotationType());
                continue;
            }
            for (Field field : registryClass.getDeclaredFields()) {
                if (Modifier.isStatic(field.getModifiers())) {
                    if (this.getContentClass().isAssignableFrom(field.getType())) {
                        try {
                            Content content = (Content) field.get(null);
                            if (content == null) continue;
                            ModHelper.switchActiveContainer(modId);
                            this.loadContent(modId, field.getName().toLowerCase(Locale.ROOT), content);
                        } catch (IllegalAccessException ignored) {
                        }
                    }
                }
            }
        }

        ModHelper.switchActiveContainer(QLibApi.MODID);
    }

    protected abstract void loadContent(String modId, String name, Content item);

    protected abstract Class<Content> getContentClass();

    protected abstract Class<Registry> getRegistryClass();

}
