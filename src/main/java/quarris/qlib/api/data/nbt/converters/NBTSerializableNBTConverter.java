package quarris.qlib.api.data.nbt.converters;

import net.minecraft.nbt.INBT;
import net.minecraftforge.common.util.INBTSerializable;
import quarris.qlib.api.data.nbt.NBTConverter;

public class NBTSerializableNBTConverter extends NBTConverter<INBTSerializable<?>, INBT> {
    @Override
    public INBT serialize(INBTSerializable object) {
        return object.serializeNBT();
    }

    @Override
    public INBTSerializable deserialize(Class clazz, INBTSerializable object, INBT nbt) {
        object.deserializeNBT(nbt);
        return object;
    }

    @Override
    public Class[] getTargetClasses() {
        return new Class[] {INBTSerializable.class};
    }
}
