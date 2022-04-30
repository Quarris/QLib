package quarris.qlib.api.data.nbt.converters;

import net.minecraft.nbt.StringTag;
import quarris.qlib.api.data.nbt.TagConverter;

public class StringTagConverter extends TagConverter<String, StringTag> {
    @Override
    public StringTag serialize(String object) {
        return StringTag.valueOf(object);
    }

    @Override
    public String deserialize(Class<?> clazz, String object, StringTag nbt) {
        return nbt.getAsString();
    }

    @Override
    public Class<?>[] getTargetClasses() {
        return new Class[]{String.class};
    }
}
