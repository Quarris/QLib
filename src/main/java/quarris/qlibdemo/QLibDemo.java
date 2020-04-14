package quarris.qlibdemo;

import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(QLibDemo.MODID)
public class QLibDemo {
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();

    public static final String MODID = "qlibdemo";

    public QLibDemo() {
    }
}
