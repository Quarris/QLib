package quarris.qlib.mod.registry;

import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import quarris.qlib.api.QLibApi;
import quarris.qlib.mod.QLib;
import quarris.qlib.mod.registry.loader.BlockLoader;
import quarris.qlib.mod.registry.loader.MenuLoader;
import quarris.qlib.mod.registry.loader.ItemLoader;
import quarris.qlib.mod.registry.loader.BlockEntityLoader;

@SuppressWarnings("unused")
public class ContentRegistry {

    public static final BlockLoader blockLoader = new BlockLoader();
    public static final ItemLoader itemLoader = new ItemLoader();
    public static final BlockEntityLoader tileLoader = new BlockEntityLoader();
    public static final MenuLoader menuLoader = new MenuLoader();

    public static void addRegister(DeferredRegister<?> registry) {
        registry.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    /*@SubscribeEvent
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
    public static void registerTileEntities(RegistryEvent.Register<BlockEntityType<?>> register) {
        tileLoader.load();
        QLib.LOGGER.info("Registering {} tile entity types", QLibApi.TILES.size());

        for (BlockEntityType tile : QLibApi.TILES) {
            register.getRegistry().register(tile);
        }
    }
    
    @SubscribeEvent
    public static void registerContainers(RegistryEvent.Register<MenuType<?>> register) {
        menuLoader.load();
        QLib.LOGGER.info("Registering {} container entity types", QLibApi.CONTAINERS.size());

        for (MenuType container : QLibApi.CONTAINERS) {
            register.getRegistry().register(container);
        }
    }*/
}
