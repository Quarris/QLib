package quarris.qlib.api.data;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import quarris.qlib.api.data.model.CustomItemModelProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class ItemRegistryObject {

    public static final Map<Item, ItemRegistryObject> HANDLERS = new HashMap<>();

    public final Item item;
    public Consumer<CustomItemModelProvider> model;

    private ItemRegistryObject(Item item) {
        this.item = item;

        if (this.item instanceof BlockItem) {
            this.model = provider -> provider.defaultBlockItemModel(item);
        } else {
            this.model = provider -> provider.defaultItemModel(item);
        }
    }

    public static ItemRegistryObject get(Item item) {
        return new ItemRegistryObject(item);
    }

    public ItemRegistryObject noModel() {
        this.model = provider -> {};
        return this;
    }

    public ItemRegistryObject customModel(Consumer<CustomItemModelProvider> customModel) {
        this.model = customModel;
        return this;
    }

    public void register() {
        HANDLERS.put(this.item, this);
    }
}
