package quarris.qlib.api.data.nbt.converters;

import net.minecraft.nbt.ByteTag;
import quarris.qlib.api.data.nbt.TagConverter;

public class ByteTagConverter extends TagConverter<Byte, ByteTag> {
    @Override
    public ByteTag serialize(Byte object) {
        return ByteTag.valueOf(object);
    }

    @Override
    public Byte deserialize(Class<?> clazz, Byte object, ByteTag nbt) {
        return nbt.getAsByte();
    }

    @Override
    public Class<?>[] getTargetClasses() {
        return new Class[]{byte.class, Byte.class};
    }
}
