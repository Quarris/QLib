package quarris.qlib.api.data.nbt.converters;

import net.minecraft.nbt.IntNBT;
import quarris.qlib.api.data.nbt.NBTConverter;

public class IntegerNBTConverter extends NBTConverter<Integer, IntNBT> {
    @Override
    public IntNBT serialize(Integer object) {
        return IntNBT.valueOf(object);
    }

    @Override
    public Integer deserialize(Class clazz, Integer object, IntNBT nbt) {
        return nbt.getInt();
    }

    @Override
    public Class[] getTargetClasses() {
        return new Class[]{int.class, Integer.class};
    }
}
