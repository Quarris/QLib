package quarris.qlib.api.data.nbt.converters;

import net.minecraft.nbt.DoubleTag;
import quarris.qlib.api.data.nbt.TagConverter;

public class DoubleTagConverter extends TagConverter<Double, DoubleTag> {
    @Override
    public DoubleTag serialize(Double object) {
        return DoubleTag.valueOf(object);
    }

    @Override
    public Double deserialize(Class<?> clazz, Double object, DoubleTag nbt) {
        return nbt.getAsDouble();
    }

    @Override
    public Class<?>[] getTargetClasses() {
        return new Class[]{double.class, Double.class};
    }
}
