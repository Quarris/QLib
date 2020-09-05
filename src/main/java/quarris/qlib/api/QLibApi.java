package quarris.qlib.api;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementManager;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.LootTableProvider;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import quarris.qlib.api.data.BlockRegistryObject;
import quarris.qlib.api.data.ItemRegistryObject;
import quarris.qlib.api.data.nbt.NBTConverter;
import quarris.qlib.api.data.nbt.NBTSerializer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
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
    public static final Set<Block> BLOCKS = new HashSet<>();
    public static final Set<Item> ITEMS = new HashSet<>();
    public static final Set<ContainerType<?>> CONTAINERS = new HashSet<>();
    public static final Set<TileEntityType<?>> TILES = new HashSet<>();
    public static final Set<EntityType<?>> ENTITIES = new HashSet<>();


    public static BlockRegistryObject getBlockRegistryHandler(Block block) {
        return BlockRegistryObject.get(block);
    }

    public static ItemRegistryObject getItemRegistryHandler(Item item) {
        return ItemRegistryObject.get(item);
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
