package quarris.qlib.mod.data;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import quarris.qlib.api.data.BlockRegistryHandler;
import quarris.qlib.api.data.ItemRegistryHandler;
import quarris.qlib.api.datagen.model.CustomBlockStateProvider;
import quarris.qlib.api.datagen.model.CustomItemModelProvider;

public class ModelDataHandler {

    public static final ListMultimap<String, ItemRegistryHandler> ITEMS = ArrayListMultimap.create();
    public static final ListMultimap<String, BlockRegistryHandler> BLOCKS = ArrayListMultimap.create();
    
    private static void init() {
        for (BlockRegistryHandler handler : BlockRegistryHandler.HANDLERS.values()) {
            BLOCKS.put(handler.block.getRegistryName().getNamespace(), handler);
        }

        for (ItemRegistryHandler handler : ItemRegistryHandler.HANDLERS.values()) {
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
