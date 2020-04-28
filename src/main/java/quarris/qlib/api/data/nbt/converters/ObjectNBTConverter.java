package quarris.qlib.api.data.nbt.converters;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import quarris.qlib.api.QLibApi;
import quarris.qlib.api.data.nbt.NBTConverter;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ObjectNBTConverter extends NBTConverter<Object, CompoundNBT> {
    @Override
    public CompoundNBT serialize(Object object) {
        CompoundNBT nbt = new CompoundNBT();
        for (Field field : object.getClass().getDeclaredFields()) {
            int fieldMods = field.getModifiers();
            if (Modifier.isStatic(fieldMods) || Modifier.isTransient(fieldMods) || Modifier.isFinal(fieldMods) || Modifier.isPrivate(fieldMods))
                continue;

            try {
                String key = field.getName();
                Object value = field.get(object);

                INBT serialized = QLibApi.SERIALIZER.serialize(value);

                if (!field.getType().isPrimitive() && value.getClass() != field.getType()) {
                    CompoundNBT tag = new CompoundNBT();
                    tag.putString("_CLASS_", value.getClass().getName());
                    tag.put("_VALUE_", serialized);
                    nbt.put(key, tag);
                } else {
                    nbt.put(key, serialized);
                }

            } catch (IllegalAccessException e) {
                QLibApi.LOGGER.warn("Tried to serialize field {} for object {} but it couldn't be accessed", field.getName(), object);
                e.printStackTrace();
            }
        }

        return nbt;
    }

    @Override
    public Object deserialize(Class clazz, Object existing, CompoundNBT nbt) {
        try {
            if (existing == null) {
                existing = clazz.newInstance();
                QLibApi.LOGGER.debug("Found null existing file for class {} for deserialization of {}. Creating new instance {}.", clazz, nbt, existing);
            }

            for (String key : nbt.keySet()) {

                try {
                    INBT fieldNBT = nbt.get(key);

                    Field field = clazz.getDeclaredField(key);
                    Object value = field.get(existing);

                    Class valueClass = field.getType();

                    if (fieldNBT instanceof CompoundNBT && ((CompoundNBT) fieldNBT).contains("_CLASS_")) {
                        valueClass = Class.forName(((CompoundNBT) fieldNBT).getString("_CLASS_"));
                        fieldNBT = ((CompoundNBT) fieldNBT).get("_VALUE_");
                    }

                    field.set(existing, QLibApi.SERIALIZER.deserialize(valueClass, value, fieldNBT));
                } catch (NoSuchFieldException e) {
                    QLibApi.LOGGER.warn("Couldn't deserialize field {} as it does not exist in the class {}", key, nbt.getCompound(key).getString("_CLASS_"));
                } catch (IllegalAccessException e) {
                    QLibApi.LOGGER.warn("Tried to deserialize field {} for object {} but it couldn't be accessed", key, existing);
                } catch (ClassNotFoundException e) {
                    QLibApi.LOGGER.warn("Tried to deserialize field {} for object {} as class {} couldn't be loaded", key, existing, nbt.getString("_CLASS_"));
                }
            }
        } catch (IllegalAccessException | InstantiationException e) {
            QLibApi.LOGGER.warn("Couldn't create new instance of {} skipping deserialization of the nbt", nbt.getString("_CLASS_"));
        }
        return existing;
    }

    @Override
    public Class[] getTargetClasses() {
        return new Class[]{Object.class};
    }
}
