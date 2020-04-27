package quarris.qlib.mod.reg.loader;

import net.minecraft.item.Item;
import quarris.qlib.api.QLibApi;
import quarris.qlib.api.data.ItemRegistryHandler;
import quarris.qlib.api.reg.ContentLoader;
import quarris.qlib.api.reg.registry.ItemRegistry;
import quarris.qlib.mod.data.ModelDataHandler;

import java.util.ArrayList;
import java.util.List;

public class ItemLoader extends ContentLoader<Item, ItemRegistry> {

    @Override
    protected void loadContent(String modId, String name, Item item) {
        if (QLibApi.ITEMS.contains(item)) return;
        if (item.getRegistryName() == null) {
            item.setRegistryName(modId, name);
        }
        QLibApi.ITEMS.add(item);

        ItemRegistryHandler handler = ItemRegistryHandler.HANDLERS.get(item);
        if (handler == null) return;

        ModelDataHandler.ITEMS.put(modId, handler);
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
