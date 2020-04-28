package quarris.qlib.api.data.nbt.converters;

import net.minecraft.nbt.ByteNBT;
import quarris.qlib.api.data.nbt.NBTConverter;

public class BooleanNBTConverter extends NBTConverter<Boolean, ByteNBT> {
    @Override
    public ByteNBT serialize(Boolean object) {
        return ByteNBT.valueOf(object);
    }

    @Override
    public Boolean deserialize(Class clazz, Boolean object, ByteNBT nbt) {
        return nbt.getByte() != 0;
    }

    @Override
    public Class[] getTargetClasses() {
        return new Class[]{boolean.class, Boolean.class};
    }
}
