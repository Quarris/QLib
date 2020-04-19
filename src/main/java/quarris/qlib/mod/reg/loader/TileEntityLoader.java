package quarris.qlib.mod.reg.loader;

import net.minecraft.tileentity.TileEntityType;
import quarris.qlib.api.reg.ContentLoader;
import quarris.qlib.api.reg.registry.TileRegistry;

import java.util.ArrayList;
import java.util.List;

public class TileEntityLoader extends ContentLoader<TileEntityType, TileRegistry> {

    public final List<TileEntityType> TILES = new ArrayList<>();

    @Override
    protected void loadContent(String modId, String name, TileEntityType tile) {
        if (TILES.contains(tile))
            return;

        if (tile.getRegistryName() == null) {
            tile.setRegistryName(modId, name);
        }
        TILES.add(tile);
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
