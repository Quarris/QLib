package quarris.qlib.api.data.nbt;

import net.minecraft.nbt.INBT;

public abstract class NBTConverter<Obj, NBTType extends INBT> {

    public abstract NBTType serialize(Obj object);

    public abstract Obj deserialize(Class clazz, Obj existing, NBTType nbt);

    public abstract Class[] getTargetClasses();

}
