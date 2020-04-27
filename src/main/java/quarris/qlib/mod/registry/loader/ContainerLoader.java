package quarris.qlib.mod.registry.loader;

import net.minecraft.inventory.container.ContainerType;
import quarris.qlib.api.QLibApi;
import quarris.qlib.api.registry.ContentLoader;
import quarris.qlib.api.registry.registry.ContainerRegistry;

import java.util.ArrayList;
import java.util.List;

public class ContainerLoader extends ContentLoader<ContainerType, ContainerRegistry> {

    @Override
    protected void loadContent(String modId, String name, ContainerType container) {
        if (QLibApi.CONTAINERS.contains(container))
            return;

        if (container.getRegistryName() == null) {
            container.setRegistryName(modId, name);
        }

        QLibApi.CONTAINERS.add(container);
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
