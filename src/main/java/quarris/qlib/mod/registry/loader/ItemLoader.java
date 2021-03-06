package quarris.qlib.mod.registry.loader;

import net.minecraft.item.Item;
import quarris.qlib.api.QLibApi;
import quarris.qlib.api.data.ItemRegistryHandler;
import quarris.qlib.api.registry.ContentLoader;
import quarris.qlib.api.registry.registry.ItemRegistry;
import quarris.qlib.mod.data.ModelDataHandler;

public class ItemLoader extends ContentLoader<Item, ItemRegistry> {

    @Override
    protected void loadContent(String modId, String name, Item item) {
        if (QLibApi.ITEMS.contains(item)) return;
        if (item.getRegistryName() == null) {
            item.setRegistryName(modId, name);
        }
        QLibApi.ITEMS.add(item);

        ItemRegistryHandler.HANDLERS.putIfAbsent(item, ItemRegistryHandler.get(item));
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
