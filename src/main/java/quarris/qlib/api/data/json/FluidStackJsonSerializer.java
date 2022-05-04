package quarris.qlib.api.data.json;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;
import quarris.qlib.api.QLibApi;

public class FluidStackJsonSerializer {

    public static JsonElement serialize(FluidStack fluid) {
        if (fluid.getAmount() == 1 && !fluid.hasTag()) {
            return new JsonPrimitive(fluid.getFluid().getRegistryName().toString());
        }

        JsonObject json = new JsonObject();
        json.addProperty("fluid", fluid.getFluid().getRegistryName().toString());
        if (fluid.getAmount() != FluidAttributes.BUCKET_VOLUME) {
            json.addProperty("amount", fluid.getAmount());
        }
        if (fluid.hasTag()) {
            json.addProperty("nbt", fluid.getTag().toString());
        }
        return json;
    }

    public static FluidStack deserialize(JsonElement json) {
        if (json.isJsonPrimitive()) {
            Fluid fluid = readFluid(json.getAsString());
            if (fluid == null) {
                QLibApi.LOGGER.error("Could not deserialize FluidStack {}. Invalid fluid, returning Empty", json);
                return FluidStack.EMPTY;
            }
            return new FluidStack(fluid, FluidAttributes.BUCKET_VOLUME);
        }

        JsonObject jObject = json.getAsJsonObject();
        Fluid fluid = readFluid(jObject.get("fluid").getAsString());
        if (fluid == null) {
            QLibApi.LOGGER.error("Could not deserialize FluidStack {}. Invalid fluid, returning Empty", json);
            return FluidStack.EMPTY;
        }

        int amount = jObject.has("amount") ? jObject.get("amount").getAsInt() : FluidAttributes.BUCKET_VOLUME;
        CompoundTag nbt = jObject.has("nbt") ? CraftingHelper.getNBT(jObject.get("nbt")) : null;

        return new FluidStack(fluid, amount, nbt);
    }

    private static Fluid readFluid(String fluid) {
        ResourceLocation id = new ResourceLocation(fluid);
        if (!ForgeRegistries.FLUIDS.containsKey(id)) {
            return null;
        }

        return ForgeRegistries.FLUIDS.getValue(id);
    }
}
