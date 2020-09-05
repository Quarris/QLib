package quarris.qlib.mod.registry.loader;

import net.minecraft.entity.EntityType;
import quarris.qlib.api.QLibApi;
import quarris.qlib.api.registry.ContentLoader;
import quarris.qlib.api.registry.annotations.EntityRegistry;

public class EntityLoader extends ContentLoader<EntityType, EntityRegistry> {

    @Override
    protected void loadContent(String modId, String name, EntityType entity) {
        if (QLibApi.ENTITIES.contains(entity))
            return;

        if (entity.getRegistryName() == null) {
            entity.setRegistryName(modId, name);
        }

        QLibApi.ENTITIES.add(entity);
    }

    @Override
    protected Class<EntityType> getContentClass() {
        return EntityType.class;
    }

    @Override
    protected Class<EntityRegistry> getRegistryClass() {
        return EntityRegistry.class;
    }
}
