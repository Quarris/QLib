package quarris.qlib.api.data.nbt.converters;

import net.minecraft.nbt.ByteNBT;
import quarris.qlib.api.data.nbt.NBTConverter;

public class ByteNBTConverter extends NBTConverter<Byte, ByteNBT> {
    @Override
    public ByteNBT serialize(Byte object) {
        return ByteNBT.valueOf(object);
    }

    @Override
    public Byte deserialize(Class clazz, Byte object, ByteNBT nbt) {
        return nbt.getByte();
    }

    @Override
    public Class[] getTargetClasses() {
        return new Class[]{byte.class, Byte.class};
    }
}
