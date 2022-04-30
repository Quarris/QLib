package quarris.qlib.api;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.minecraftforge.registries.RegistryObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import quarris.qlib.api.data.BlockRegistryHandler;
import quarris.qlib.api.data.ItemRegistryHandler;
import quarris.qlib.api.data.nbt.TagConverter;
import quarris.qlib.api.data.nbt.NBTSerializer;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class QLibApi {

    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "qlib";

    /**
     * Internal hooks to be used by the base QLib.
     * You shouldn't change or use it.
     */
    public static IInternals internals;

    public static final NBTSerializer SERIALIZER = new NBTSerializer();

    /* ----------------------------------OLD---------------------------------------- */

    public static BlockRegistryHandler getBlockRegistryHandler(Block block) {
        return BlockRegistryHandler.get(block);
    }

    public static ItemRegistryHandler getItemRegistryHandler(Item item) {
        return ItemRegistryHandler.get(item);
    }

    public static void addLootTableProvider(Function<DataGenerator, LootTableProvider> provider) {
        internals.addLootTableProvider(provider);
    }

    public static void addNBTConverter(TagConverter converter) {
        SERIALIZER.addConverter(converter);
    }

    public interface IInternals {
        void addLootTableProvider(Function<DataGenerator, LootTableProvider> provider);
    }
}
