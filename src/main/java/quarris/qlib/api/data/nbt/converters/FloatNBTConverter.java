package quarris.qlib.api.data.nbt.converters;

import net.minecraft.nbt.FloatNBT;
import quarris.qlib.api.data.nbt.NBTConverter;

public class FloatNBTConverter extends NBTConverter<Float, FloatNBT> {
    @Override
    public FloatNBT serialize(Float object) {
        return FloatNBT.valueOf(object);
    }

    @Override
    public Float deserialize(Class clazz, Float object, FloatNBT nbt) {
        return nbt.getFloat();
    }

    @Override
    public Class[] getTargetClasses() {
        return new Class[]{float.class, Float.class};
    }
}
