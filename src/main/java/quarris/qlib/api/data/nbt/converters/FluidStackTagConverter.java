package quarris.qlib.api.data.nbt.converters;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.fluids.FluidStack;
import quarris.qlib.api.data.nbt.TagConverter;

public class FluidStackTagConverter extends TagConverter<FluidStack, CompoundTag> {
    @Override
    public CompoundTag serialize(FluidStack object) {
        return object.writeToNBT(new CompoundTag());
    }

    @Override
    public FluidStack deserialize(Class<?> clazz, FluidStack existing, CompoundTag nbt) {
        return FluidStack.loadFluidStackFromNBT(nbt);
    }

    @Override
    public Class<?>[] getTargetClasses() {
        return new Class<?>[]{FluidStack.class};
    }
}
