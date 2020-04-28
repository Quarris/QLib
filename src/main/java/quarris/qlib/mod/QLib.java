package quarris.qlib.mod;

import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import quarris.qlib.api.QLibApi;
import quarris.qlib.mod.data.LootTableDataHandler;
import quarris.qlib.mod.data.ModelDataHandler;
import quarris.qlib.mod.proxy.ClientProxy;
import quarris.qlib.mod.proxy.IProxy;
import quarris.qlib.mod.proxy.ServerProxy;

@Mod(QLib.MODID)
public class QLib {
    public static final Logger LOGGER = LogManager.getLogger();

    public static final String MODID = "qlib";

    public static IProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> ServerProxy::new);

    public QLib() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::gatherData);
        QLibApi.internals = new InternalHooks();
        QLibApi.SERIALIZER.registerDefaultConverters();
    }

    public void gatherData(GatherDataEvent event) {
        LOGGER.info("Gathering Data for {}", MODID);

        if (event.includeClient()) {
            LOGGER.info("Registering models");
            ModelDataHandler.registerModels(event);
        }

        if (event.includeServer()) {
            LOGGER.info("Registering Loot Tables");
            LootTableDataHandler.registerLootTables(event);
        }
    }
}
