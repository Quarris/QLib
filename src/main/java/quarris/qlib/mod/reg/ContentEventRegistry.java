package quarris.qlib.mod.reg;

import net.minecraft.block.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import quarris.qlib.api.QLibApi;
import quarris.qlib.mod.QLib;
import quarris.qlib.mod.reg.loader.BlockLoader;
import quarris.qlib.mod.reg.loader.ContainerLoader;
import quarris.qlib.mod.reg.loader.ItemLoader;
import quarris.qlib.mod.reg.loader.TileEntityLoader;

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

        QLib.LOGGER.info("Registering {} block items", QLibApi.BLOCK_ITEMS.size());
        for (Item block : QLibApi.BLOCK_ITEMS) {
            register.getRegistry().register(block);
        }
    }

    @SubscribeEvent
    public static void registerTileEntities(RegistryEvent.Register<TileEntityType<?>> register) {
        tileLoader.load();
        QLib.LOGGER.info("Registering {} tile entity types", tileLoader.TILES.size());

        for (TileEntityType tile : tileLoader.TILES) {
            register.getRegistry().register(tile);
        }
    }
    
    @SubscribeEvent
    public static void registerContainers(RegistryEvent.Register<ContainerType<?>> register) {
        containerLoader.load();
        QLib.LOGGER.info("Registering {} container entity types", containerLoader.CONTAINERS.size());

        for (ContainerType container : containerLoader.CONTAINERS) {
            register.getRegistry().register(container);
        }
    }
}
