package quarris.qlib.common.content.item;

import net.minecraft.item.Item;
import quarris.qlib.QLib;
import quarris.qlib.util.ModHelper;
import quarris.qlib.util.ReflectionHelper;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class ItemLoader {

    public static List<Item> loadItems() {
        List<Class> regClasses = new ArrayList<>();
        try {
            regClasses = ReflectionHelper.getClassesAnnotatedBy(ItemRegistry.class);
        } catch (ClassNotFoundException e) {
            QLib.LOGGER.error("Error during registering items", e.getCause());
        }

        if (regClasses.isEmpty())
            return Collections.emptyList();

        QLib.LOGGER.info("Loading items from " + regClasses.size() + " classes");

        List<Item> itemsToLoad = new ArrayList<>();

        for (Class regClass : regClasses) {
            ItemRegistry annotation = (ItemRegistry) regClass.getDeclaredAnnotation(ItemRegistry.class);
            String namespace = annotation.value();
            for (Field field : regClass.getDeclaredFields()) {
                if (Modifier.isStatic(field.getModifiers())) {
                    if (Item.class.isAssignableFrom(field.getType())) {
                        try {
                            Item item = (Item) field.get(null);
                            if (itemsToLoad.contains(item)) continue;
                            if (item.getRegistryName() == null) {
                                ModHelper.switchActiveContainer(namespace);
                                item.setRegistryName(namespace, field.getName().toLowerCase(Locale.ROOT));
                            }
                            itemsToLoad.add(item);
                        } catch (IllegalAccessException ignored) { }
                    }
                }
            }
        }

        ModHelper.switchActiveContainer(QLib.MODID);
        return itemsToLoad;
    }
}
