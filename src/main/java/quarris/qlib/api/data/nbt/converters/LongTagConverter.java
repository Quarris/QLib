package quarris.qlib.api.data.nbt.converters;

import net.minecraft.nbt.LongTag;
import quarris.qlib.api.data.nbt.TagConverter;

public class LongTagConverter extends TagConverter<Long, LongTag> {
    @Override
    public LongTag serialize(Long object) {
        return LongTag.valueOf(object);
    }

    @Override
    public Long deserialize(Class<?> clazz, Long object, LongTag nbt) {
        return nbt.getAsLong();
    }

    @Override
    public Class<?>[] getTargetClasses() {
        return new Class[] {long.class, Long.class};
    }
}
