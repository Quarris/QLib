package quarris.qlib.api.data.nbt.converters;

import net.minecraft.nbt.LongNBT;
import quarris.qlib.api.data.nbt.NBTConverter;

public class LongNBTConverter extends NBTConverter<Long, LongNBT> {
    @Override
    public LongNBT serialize(Long object) {
        return LongNBT.valueOf(object);
    }

    @Override
    public Long deserialize(Class clazz, Long object, LongNBT nbt) {
        return nbt.getLong();
    }

    @Override
    public Class[] getTargetClasses() {
        return new Class[] {long.class, Long.class};
    }
}
