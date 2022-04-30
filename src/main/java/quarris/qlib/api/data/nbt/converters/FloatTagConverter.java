package quarris.qlib.api.data.nbt.converters;

import net.minecraft.nbt.FloatTag;
import quarris.qlib.api.data.nbt.TagConverter;

public class FloatTagConverter extends TagConverter<Float, FloatTag> {
    @Override
    public FloatTag serialize(Float object) {
        return FloatTag.valueOf(object);
    }

    @Override
    public Float deserialize(Class<?> clazz, Float object, FloatTag nbt) {
        return nbt.getAsFloat();
    }

    @Override
    public Class<?>[] getTargetClasses() {
        return new Class[]{float.class, Float.class};
    }
}
