package quarris.qlib.api.data.nbt;

import net.minecraft.nbt.Tag;

public abstract class TagConverter<Obj, NBTType extends Tag> {

    public abstract NBTType serialize(Obj object);

    public abstract Obj deserialize(Class<?> clazz, Obj existing, NBTType nbt);

    public abstract Class<?>[] getTargetClasses();

}
