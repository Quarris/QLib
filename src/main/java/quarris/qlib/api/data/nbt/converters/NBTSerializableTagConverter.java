package quarris.qlib.api.data.nbt.converters;

import net.minecraft.nbt.Tag;
import net.minecraftforge.common.util.INBTSerializable;
import quarris.qlib.api.data.nbt.TagConverter;

@SuppressWarnings({"rawtypes", "unchecked"})
public class NBTSerializableTagConverter extends TagConverter<INBTSerializable, Tag> {
    @Override
    public Tag serialize(INBTSerializable object) {
        return object.serializeNBT();
    }

    @Override
    public INBTSerializable deserialize(Class<?> clazz, INBTSerializable object, Tag nbt) {
        object.deserializeNBT(nbt);
        return object;
    }

    @Override
    public Class<?>[] getTargetClasses() {
        return new Class[] {INBTSerializable.class};
    }
}
