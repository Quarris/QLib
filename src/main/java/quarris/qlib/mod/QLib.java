package quarris.qlib.mod;

import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import quarris.qlib.mod.data.model.ModelDataHandler;
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
    }

    public void gatherData(GatherDataEvent event) {
        LOGGER.info("Gathering Data for {}", MODID);

        LOGGER.info("Registering models");
        ModelDataHandler.registerModels(event);

    }
}
