package quarris.qlib.common.reg.loader;

import net.minecraft.inventory.container.ContainerType;
import quarris.qlib.common.reg.ContentLoader;

import java.util.ArrayList;
import java.util.List;

public class ContainerLoader extends ContentLoader<ContainerType, ContainerRegistry> {

    public List<ContainerType> CONTAINERS = new ArrayList<>();

    @Override
    protected void loadContent(String modId, String name, ContainerType container) {
        if (CONTAINERS.contains(container))
            return;

        if (container.getRegistryName() == null) {
            container.setRegistryName(modId, name);
        }

        CONTAINERS.add(container);
    }

    @Override
    protected Class<ContainerType> getContentClass() {
        return ContainerType.class;
    }

    @Override
    protected Class<ContainerRegistry> getRegistryClass() {
        return ContainerRegistry.class;
    }
}
