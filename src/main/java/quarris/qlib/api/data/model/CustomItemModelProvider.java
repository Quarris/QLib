package quarris.qlib.api.data.model;

import net.minecraft.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import quarris.qlib.api.QLibApi;
import quarris.qlib.api.data.ItemRegistryHandler;

import java.util.Collection;

public class CustomItemModelProvider extends ItemModelProvider {

    public final Collection<ItemRegistryHandler> items;

    public CustomItemModelProvider(GatherDataEvent event, String modid, Collection<ItemRegistryHandler> items) {
        super(event.getGenerator(), modid, event.getExistingFileHelper());
        this.items = items;
    }

    @Override
    protected void registerModels() {
        for (ItemRegistryHandler item : this.items) {
            item.model.accept(this);
        }
    }

    public void defaultItemModel(Item item) {
        this.withExistingParent(itemName(item), "item/generated").texture("layer0", this.modLoc("item/"+itemName(item)));
    }

    public void defaultBlockItemModel(Item item) {
        String name = this.itemName(item);
        this.getBuilder(name).parent(new ModelFile.UncheckedModelFile(this.modLoc("block/"+name)));
    }

    public String itemName(Item item) {
        return item.getRegistryName().getPath();
    }

    @Override
    public String getName() {
        return QLibApi.MODID + ":ItemModel";
    }
}
