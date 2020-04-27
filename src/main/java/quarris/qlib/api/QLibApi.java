package quarris.qlib.api;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.LootTableProvider;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import quarris.qlib.api.data.BlockRegistryHandler;
import quarris.qlib.api.data.ItemRegistryHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class QLibApi {

    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "qlib";

    /**
     * The following are registered content by QLib Registry System
     */
    public static List<Block> BLOCKS = new ArrayList<>();
    public static List<BlockItem> BLOCK_ITEMS = new ArrayList<>();
    public static List<Item> ITEMS = new ArrayList<>();

    /**
     * Internal hooks to be used by the base QLib.
     * You shouldn't change or use it.
     */
    public static IInternals internals;

    public static BlockRegistryHandler getBlockRegistryHandler(Block block) {
        return BlockRegistryHandler.get(block);
    }

    public static ItemRegistryHandler getItemRegistryHandler(Item item) {
        return ItemRegistryHandler.get(item);
    }

    public static void addLootTableProvider(Function<DataGenerator, LootTableProvider> provider) {
        internals.addLootTableProvider(provider);
    }

    public interface IInternals {
        void addLootTableProvider(Function<DataGenerator, LootTableProvider> provider);
    }
}
