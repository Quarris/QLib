package quarris.qlib.api;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.LootTableProvider;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import quarris.qlib.api.data.BlockRegistryHandler;
import quarris.qlib.api.data.ItemRegistryHandler;
import quarris.qlib.api.data.nbt.NBTConverter;
import quarris.qlib.api.data.nbt.NBTSerializer;

import java.util.ArrayList;
import java.util.List;
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

    /**
     * The following are content registered by QLib Registry System
     */
    public static List<Block> BLOCKS = new ArrayList<>();
    public static List<Item> ITEMS = new ArrayList<>();
    public static List<ContainerType> CONTAINERS = new ArrayList<>();
    public static final List<TileEntityType> TILES = new ArrayList<>();


    public static BlockRegistryHandler getBlockRegistryHandler(Block block) {
        return BlockRegistryHandler.get(block);
    }

    public static ItemRegistryHandler getItemRegistryHandler(Item item) {
        return ItemRegistryHandler.get(item);
    }

    public static void addLootTableProvider(Function<DataGenerator, LootTableProvider> provider) {
        internals.addLootTableProvider(provider);
    }

    public static void addNBTConverter(NBTConverter converter) {
        SERIALIZER.addConverter(converter);
    }

    public interface IInternals {
        void addLootTableProvider(Function<DataGenerator, LootTableProvider> provider);
    }
}
