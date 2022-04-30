package quarris.qlib.api.data.nbt.converters;

import net.minecraft.nbt.IntTag;
import quarris.qlib.api.data.nbt.TagConverter;

public class IntegerTagConverter extends TagConverter<Integer, IntTag> {
    @Override
    public IntTag serialize(Integer object) {
        return IntTag.valueOf(object);
    }

    @Override
    public Integer deserialize(Class<?> clazz, Integer object, IntTag nbt) {
        return nbt.getAsInt();
    }

    @Override
    public Class<?>[] getTargetClasses() {
        return new Class[]{int.class, Integer.class};
    }
}
