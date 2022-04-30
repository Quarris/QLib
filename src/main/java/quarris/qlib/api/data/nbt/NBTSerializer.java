package quarris.qlib.api.data.nbt;

import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraftforge.common.util.INBTSerializable;
import quarris.qlib.api.QLibApi;
import quarris.qlib.api.data.nbt.converters.*;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({"unchecked", "ConstantConditions", "rawtypes"})
public class NBTSerializer {

    public Map<Class, TagConverter> CONVERTERS = new HashMap<>();

    public void addConverter(TagConverter converter) {
        for (Class clazz : converter.getTargetClasses()) {
            CONVERTERS.put(clazz, converter);
        }
    }

    public void registerDefaultConverters() {
        QLibApi.LOGGER.info("Adding NBT Converters");
        this.addConverter(new LongTagConverter());
        this.addConverter(new BooleanTagConverter());
        this.addConverter(new ByteTagConverter());
        this.addConverter(new DoubleTagConverter());
        this.addConverter(new FloatTagConverter());
        this.addConverter(new IntegerTagConverter());
        this.addConverter(new StringTagConverter());
        this.addConverter(new ObjectTagConverter());

        this.addConverter(new NBTSerializableTagConverter());
        this.addConverter(new FluidStackTagConverter());
    }

    public Tag serialize(Object value) {
        return this.serialize(value, null);
    }

    public Tag serialize(Object value, Class clazz) {
        if (value == null)
            return StringTag.valueOf("null");

        TagConverter converter = this.getConverter(clazz);
        if (converter == null) {
            if (value instanceof INBTSerializable<?>) {
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

    public <T> T deserialize(Class clazz, @Nullable T existing, Tag nbt) {
        if (nbt instanceof StringTag && nbt.getAsString().equals("null"))
            return null;

        TagConverter<T, Tag> converter = this.getConverter(clazz);

        if (converter == null) {
            converter = this.getConverter(Object.class);
            QLibApi.LOGGER.debug("No converted was found for {}, defaulting to {}", clazz, converter);
        }

        return converter.deserialize(clazz, existing, nbt);
    }

    @Nullable
    public TagConverter getConverter(Class clazz) {
        return CONVERTERS.get(clazz);
    }
}
