package quarris.qlib.mod.registry;

import net.minecraft.block.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import quarris.qlib.api.QLibApi;
import quarris.qlib.mod.QLib;
import quarris.qlib.mod.registry.loader.BlockLoader;
import quarris.qlib.mod.registry.loader.ContainerLoader;
import quarris.qlib.mod.registry.loader.ItemLoader;
import quarris.qlib.mod.registry.loader.TileEntityLoader;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = QLib.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ContentEventRegistry {

    public static final BlockLoader blockLoader = new BlockLoader();
    public static final ItemLoader itemLoader = new ItemLoader();
    public static final TileEntityLoader tileLoader = new TileEntityLoader();
    public static final ContainerLoader containerLoader = new ContainerLoader();

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> register) {
        blockLoader.load();
        QLib.LOGGER.info("Registering {} blocks", QLibApi.BLOCKS.size());
        for (Block block : QLibApi.BLOCKS) {
            register.getRegistry().register(block);
        }
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> register) {
        itemLoader.load();
        QLib.LOGGER.info("Registering {} items", QLibApi.ITEMS.size());
        for (Item item : QLibApi.ITEMS) {
            register.getRegistry().register(item);
        }
    }

    @SubscribeEvent
    public static void registerTileEntities(RegistryEvent.Register<TileEntityType<?>> register) {
        tileLoader.load();
        QLib.LOGGER.info("Registering {} tile entity types", QLibApi.TILES.size());

        for (TileEntityType tile : QLibApi.TILES) {
            register.getRegistry().register(tile);
        }
    }
    
    @SubscribeEvent
    public static void registerContainers(RegistryEvent.Register<ContainerType<?>> register) {
        containerLoader.load();
        QLib.LOGGER.info("Registering {} container entity types", QLibApi.CONTAINERS.size());

        for (ContainerType container : QLibApi.CONTAINERS) {
            register.getRegistry().register(container);
        }
    }
}
