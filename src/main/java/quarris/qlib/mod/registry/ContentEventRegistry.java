package quarris.qlib.mod.registry;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import quarris.qlib.api.QLibApi;
import quarris.qlib.mod.QLib;
import quarris.qlib.mod.registry.loader.*;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = QLib.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ContentEventRegistry {

    public static final BlockLoader BLOCK_LOADER = new BlockLoader();
    public static final ItemLoader ITEM_LOADER = new ItemLoader();
    public static final TileEntityLoader TILE_LOADER = new TileEntityLoader();
    public static final ContainerLoader CONTAINER_LOADER = new ContainerLoader();
    public static final EntityLoader ENTITY_LOADER = new EntityLoader();

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> register) {
        BLOCK_LOADER.load();
        QLib.LOGGER.info("Registering {} blocks", QLibApi.BLOCKS.size());
        for (Block block : QLibApi.BLOCKS) {
            register.getRegistry().register(block);
        }
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> register) {
        ITEM_LOADER.load();
        QLib.LOGGER.info("Registering {} items", QLibApi.ITEMS.size());
        for (Item item : QLibApi.ITEMS) {
            register.getRegistry().register(item);
        }
    }

    @SubscribeEvent
    public static void registerTileEntities(RegistryEvent.Register<TileEntityType<?>> register) {
        TILE_LOADER.load();
        QLib.LOGGER.info("Registering {} tile entity types", QLibApi.TILES.size());

        for (TileEntityType<?> tile : QLibApi.TILES) {
            register.getRegistry().register(tile);
        }
    }
    
    @SubscribeEvent
    public static void registerContainers(RegistryEvent.Register<ContainerType<?>> register) {
        CONTAINER_LOADER.load();
        QLib.LOGGER.info("Registering {} container types", QLibApi.CONTAINERS.size());

        for (ContainerType<?> container : QLibApi.CONTAINERS) {
            register.getRegistry().register(container);
        }
    }

    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityType<?>> register) {
        ENTITY_LOADER.load();
        QLib.LOGGER.info("Registering {} entity types", QLibApi.ENTITIES.size());

        for (EntityType<?> entity : QLibApi.ENTITIES) {
            register.getRegistry().register(entity);
        }
    }
}
