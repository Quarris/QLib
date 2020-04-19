package quarris.qlib.mod.reg.loader;

import net.minecraft.item.Item;
import quarris.qlib.api.reg.ContentLoader;
import quarris.qlib.api.reg.registry.ItemRegistry;
import quarris.qlib.mod.data.model.ModelDataHandler;

import java.util.ArrayList;
import java.util.List;

public class ItemLoader extends ContentLoader<Item, ItemRegistry> {

    public final List<Item> ITEMS = new ArrayList<>();

    @Override
    protected void loadContent(String modId, String name, Item item) {
        if (ITEMS.contains(item)) return;
        if (item.getRegistryName() == null) {
            item.setRegistryName(modId, name);
        }
        ITEMS.add(item);
        ModelDataHandler.ITEMS.put(modId, item);
    }

    @Override
    protected Class<Item> getContentClass() {
        return Item.class;
    }

    @Override
    protected Class<ItemRegistry> getRegistryClass() {
        return ItemRegistry.class;
    }
}
