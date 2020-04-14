package quarris.qlibdemo.container;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import quarris.qlib.common.reg.container.ContainerRegistry;
import quarris.qlibdemo.QLibDemo;

@ContainerRegistry(QLibDemo.MODID)
public class ModContainers {

    public static final ContainerType<ThingyContainer> THINGY_CONTAINER_TYPE = IForgeContainerType.create((id, inv, data) -> new ThingyContainer(id));

}
