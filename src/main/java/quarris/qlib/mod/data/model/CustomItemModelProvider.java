package quarris.qlib.mod.data.model;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import quarris.qlib.api.QLibApi;
import quarris.qlib.api.data.model.IHasCustomItemModel;

import java.util.Collection;

public class CustomItemModelProvider extends ItemModelProvider {

    public final Collection<Item> items;

    public CustomItemModelProvider(GatherDataEvent event, String modid, Collection<Item> items) {
        super(event.getGenerator(), modid, event.getExistingFileHelper());
        this.items = items;
    }

    @Override
    protected void registerModels() {
        for (Item item : this.items) {
            if (item instanceof IHasCustomItemModel) {
                ((IHasCustomItemModel) item).makeModel(this);
            } else if (item instanceof BlockItem) {
                this.defaultBlockItemModel(item);
            } else {
                this.defaultItemModel(item);
            }
        }
    }

    public void defaultItemModel(Item item) {
        this.withExistingParent(this.itemName(item), "item/generated");
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
        return QLibApi.MODID + ":item_model_provider";
    }
}
