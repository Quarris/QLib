package quarris.qlib.api.data.nbt.converters;

import net.minecraft.nbt.DoubleNBT;
import quarris.qlib.api.data.nbt.NBTConverter;

public class DoubleNBTConverter extends NBTConverter<Double, DoubleNBT> {
    @Override
    public DoubleNBT serialize(Double object) {
        return DoubleNBT.valueOf(object);
    }

    @Override
    public Double deserialize(Class clazz, Double object, DoubleNBT nbt) {
        return nbt.getDouble();
    }

    @Override
    public Class[] getTargetClasses() {
        return new Class[]{double.class, Double.class};
    }
}
