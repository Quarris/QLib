package quarris.qlib.api.data;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import quarris.qlib.api.data.model.CustomItemModelProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class ItemRegistryHandler {

    public static final Map<Item, ItemRegistryHandler> HANDLERS = new HashMap<>();

    public final Item item;
    public Consumer<CustomItemModelProvider> model;

    ItemRegistryHandler(Item item) {
        this.item = item;

        if (this.item instanceof BlockItem) {
            this.model = provider -> provider.defaultBlockItemModel(item);
        } else {
            this.model = provider -> provider.defaultItemModel(item);
        }
    }

    public static ItemRegistryHandler get(Item item) {
        return new ItemRegistryHandler(item);
    }

    public ItemRegistryHandler noModel() {
        this.model = provider -> {};
        return this;
    }

    public ItemRegistryHandler customModel(Consumer<CustomItemModelProvider> customModel) {
        this.model = customModel;
        return this;
    }
}
