package quarris.qlib.mod.data;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import quarris.qlib.api.data.BlockRegistryObject;
import quarris.qlib.api.data.ItemRegistryObject;
import quarris.qlib.api.data.model.CustomBlockStateProvider;
import quarris.qlib.api.data.model.CustomItemModelProvider;

public class ModelDataHandler {

    public static final ListMultimap<String, ItemRegistryObject> ITEMS = ArrayListMultimap.create();
    public static final ListMultimap<String, BlockRegistryObject> BLOCKS = ArrayListMultimap.create();
    
    private static void init() {
        for (BlockRegistryObject handler : BlockRegistryObject.HANDLERS.values()) {
            BLOCKS.put(handler.block.getRegistryName().getNamespace(), handler);
        }

        for (ItemRegistryObject handler : ItemRegistryObject.HANDLERS.values()) {
            ITEMS.put(handler.item.getRegistryName().getNamespace(), handler);
        }
    }

    public static void registerModels(GatherDataEvent event) {
        init();
        
        for (String modid : BLOCKS.keySet()) {
            event.getGenerator().addProvider(new CustomBlockStateProvider(event, modid, BLOCKS.get(modid)));
        }

        for (String modid : ITEMS.keySet()) {
            event.getGenerator().addProvider(new CustomItemModelProvider(event, modid, ITEMS.get(modid)));
        }
    }
}
