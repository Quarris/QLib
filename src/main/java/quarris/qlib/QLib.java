package quarris.qlib;

import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import quarris.qlib.proxy.ClientProxy;
import quarris.qlib.proxy.IProxy;
import quarris.qlib.proxy.ServerProxy;

@Mod(QLib.MODID)
public class QLib {
    public static final Logger LOGGER = LogManager.getLogger();

    public static final String MODID = "qlib";

    public static IProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> ServerProxy::new);

    public QLib() {

    }
}
