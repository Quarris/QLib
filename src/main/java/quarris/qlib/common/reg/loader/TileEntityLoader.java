package quarris.qlib.common.reg.loader;

import net.minecraft.tileentity.TileEntityType;
import quarris.qlib.common.reg.ContentLoader;

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
