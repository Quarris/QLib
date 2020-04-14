package quarris.qlib.common.reg;

import net.minecraft.block.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import quarris.qlib.QLib;
import quarris.qlib.common.reg.loader.BlockLoader;
import quarris.qlib.common.reg.loader.ContainerLoader;
import quarris.qlib.common.reg.loader.ItemLoader;
import quarris.qlib.common.reg.loader.TileEntityLoader;

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
        QLib.LOGGER.info("Registering " + blockLoader.BLOCKS.size() + " blocks");
        for (Block block : blockLoader.BLOCKS) {
            register.getRegistry().register(block);
        }
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> register) {
        itemLoader.load();
        QLib.LOGGER.info("Registering {} items", itemLoader.ITEMS.size());
        for (Item item : itemLoader.ITEMS) {
            register.getRegistry().register(item);
        }

        QLib.LOGGER.info("Registering {} block items", blockLoader.BLOCK_ITEMS.size());
        for (Item block : blockLoader.BLOCK_ITEMS) {
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
