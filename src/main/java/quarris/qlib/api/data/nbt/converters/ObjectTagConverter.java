package quarris.qlib.api.data.nbt.converters;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import quarris.qlib.api.QLibApi;
import quarris.qlib.api.data.nbt.TagConverter;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ObjectTagConverter extends TagConverter<Object, CompoundTag> {
    @Override
    public CompoundTag serialize(Object object) {
        CompoundTag tag = new CompoundTag();
        for (Field field : object.getClass().getDeclaredFields()) {
            int fieldModifiers = field.getModifiers();
            if (Modifier.isStatic(fieldModifiers) || Modifier.isTransient(fieldModifiers) || Modifier.isFinal(fieldModifiers) || Modifier.isPrivate(fieldModifiers))
                continue;

            try {
                String key = field.getName();
                Object value = field.get(object);

                Tag serialized = QLibApi.SERIALIZER.serialize(value);

                if (!field.getType().isPrimitive() && value.getClass() != field.getType()) {
                    CompoundTag fieldTag = new CompoundTag();
                    fieldTag.putString("_CLASS_", value.getClass().getName());
                    fieldTag.put("_VALUE_", serialized);
                    tag.put(key, fieldTag);
                } else {
                    tag.put(key, serialized);
                }

            } catch (IllegalAccessException e) {
                QLibApi.LOGGER.warn("Tried to serialize field {} for object {} but it couldn't be accessed", field.getName(), object);
                e.printStackTrace();
            }
        }

        return tag;
    }

    @Override
    public Object deserialize(Class<?> clazz, Object instance, CompoundTag tag) {
        try {
            if (instance == null) {
                instance = clazz.newInstance();
                QLibApi.LOGGER.debug("Found null existing file for class {} for deserialization of {}. Creating new instance {}.", clazz, tag, instance);
            }

            for (String key : tag.getAllKeys()) {

                try {
                    Tag fieldTag = tag.get(key);

                    Field field = clazz.getDeclaredField(key);
                    Object value = field.get(instance);

                    Class valueClass = field.getType();

                    if (fieldTag instanceof CompoundTag && ((CompoundTag) fieldTag).contains("_CLASS_")) {
                        valueClass = Class.forName(((CompoundTag) fieldTag).getString("_CLASS_"));
                        fieldTag = ((CompoundTag) fieldTag).get("_VALUE_");
                    }

                    field.set(instance, QLibApi.SERIALIZER.deserialize(valueClass, value, fieldTag));
                } catch (NoSuchFieldException e) {
                    QLibApi.LOGGER.warn("Couldn't deserialize field {} as it does not exist in the class {}", key, tag.getCompound(key).getString("_CLASS_"));
                } catch (IllegalAccessException e) {
                    QLibApi.LOGGER.warn("Tried to deserialize field {} for object {} but it couldn't be accessed", key, instance);
                } catch (ClassNotFoundException e) {
                    QLibApi.LOGGER.warn("Tried to deserialize field {} for object {} as class {} couldn't be loaded", key, instance, tag.getString("_CLASS_"));
                }
            }
        } catch (IllegalAccessException | InstantiationException e) {
            QLibApi.LOGGER.warn("Couldn't create new instance of {} skipping deserialization of the nbt", tag.getString("_CLASS_"));
        }
        return instance;
    }

    @Override
    public Class<?>[] getTargetClasses() {
        return new Class[]{Object.class};
    }
}
