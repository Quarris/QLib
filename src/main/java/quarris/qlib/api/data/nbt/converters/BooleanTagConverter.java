package quarris.qlib.api.data.nbt.converters;

import net.minecraft.nbt.ByteTag;
import quarris.qlib.api.data.nbt.TagConverter;

public class BooleanTagConverter extends TagConverter<Boolean, ByteTag> {
    @Override
    public ByteTag serialize(Boolean object) {
        return ByteTag.valueOf(object);
    }

    @Override
    public Boolean deserialize(Class<?> clazz, Boolean object, ByteTag nbt) {
        return nbt.getAsByte() != 0;
    }

    @Override
    public Class<?>[] getTargetClasses() {
        return new Class[]{boolean.class, Boolean.class};
    }
}
