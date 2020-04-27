package quarris.qlib.mod.registry.loader;

import net.minecraft.tileentity.TileEntityType;
import quarris.qlib.api.QLibApi;
import quarris.qlib.api.registry.ContentLoader;
import quarris.qlib.api.registry.registry.TileRegistry;

import java.util.ArrayList;
import java.util.List;

public class TileEntityLoader extends ContentLoader<TileEntityType, TileRegistry> {

    @Override
    protected void loadContent(String modId, String name, TileEntityType tile) {
        if (QLibApi.TILES.contains(tile))
            return;

        if (tile.getRegistryName() == null) {
            tile.setRegistryName(modId, name);
        }
        QLibApi.TILES.add(tile);
    }

    @Override
    protected Class<TileEntityType> getContentClass() {
        return TileEntityType.class;
    }

    @Override
    protected Class<TileRegistry> getRegistryClass() {
        return TileRegistry.class;
    }
}
