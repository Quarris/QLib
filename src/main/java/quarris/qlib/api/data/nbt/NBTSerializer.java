package quarris.qlib.api.data.nbt;

import net.minecraft.nbt.INBT;
import net.minecraft.nbt.StringNBT;
import net.minecraftforge.common.util.INBTSerializable;
import quarris.qlib.api.QLibApi;
import quarris.qlib.api.data.nbt.converters.*;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({"unchecked", "ConstantConditions"})
public class NBTSerializer {

    public Map<Class, NBTConverter> CONVERTERS = new HashMap<>();

    public void addConverter(NBTConverter converter) {
        for (Class clazz : converter.getTargetClasses()) {
            CONVERTERS.put(clazz, converter);
        }
    }

    public void registerDefaultConverters() {
        QLibApi.LOGGER.info("Adding NBT Converters");
        this.addConverter(new LongNBTConverter());
        this.addConverter(new BooleanNBTConverter());
        this.addConverter(new ByteNBTConverter());
        this.addConverter(new DoubleNBTConverter());
        this.addConverter(new FloatNBTConverter());
        this.addConverter(new IntegerNBTConverter());
        this.addConverter(new ObjectNBTConverter());
        this.addConverter(new StringNBTConverter());
        this.addConverter(new NBTSerializableNBTConverter());
    }

    public INBT serialize(Object value) {
        return this.serialize(value, null);
    }

    public INBT serialize(Object value, Class clazz) {
        if (value == null)
            return StringNBT.valueOf("null");

        NBTConverter converter = this.getConverter(clazz);
        if (converter == null) {
            if (value instanceof INBTSerializable) {
                converter = this.getConverter(INBTSerializable.class);
            } else {
                converter = this.getConverter(value.getClass());
            }
        }

        if (converter == null) {
            converter = this.getConverter(Object.class);
            QLibApi.LOGGER.debug("No converted was found for {}, defaulting to {}", value.getClass(), converter);
        }

        return converter.serialize(value);
    }

    public <T> T deserialize(Class clazz, @Nullable T existing, INBT nbt) {
        if (nbt instanceof StringNBT && nbt.getString().equals("null"))
            return null;

        NBTConverter<T, INBT> converter = this.getConverter(clazz);

        if (converter == null) {
            converter = this.getConverter(Object.class);
            QLibApi.LOGGER.debug("No converted was found for {}, defaulting to {}", clazz, converter);
        }

        return converter.deserialize(clazz, existing, nbt);
    }

    @Nullable
    public NBTConverter getConverter(Class clazz) {
        return CONVERTERS.get(clazz);
    }
}
