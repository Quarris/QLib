package quarris.qlib.api.data.nbt.converters;

import net.minecraft.nbt.StringNBT;
import quarris.qlib.api.data.nbt.NBTConverter;

public class StringNBTConverter extends NBTConverter<String, StringNBT> {
    @Override
    public StringNBT serialize(String object) {
        return StringNBT.valueOf(object);
    }

    @Override
    public String deserialize(Class clazz, String object, StringNBT nbt) {
        return nbt.getString();
    }

    @Override
    public Class[] getTargetClasses() {
        return new Class[]{String.class};
    }
}
