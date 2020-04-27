package quarris.qlib.mod.data;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import quarris.qlib.api.data.BlockRegistryHandler;
import quarris.qlib.api.data.ItemRegistryHandler;
import quarris.qlib.api.data.model.CustomBlockStateProvider;
import quarris.qlib.api.data.model.CustomItemModelProvider;

import java.util.Collection;
import java.util.Map;

public class ModelDataHandler {

    public static final ListMultimap<String, ItemRegistryHandler> ITEMS = ArrayListMultimap.create();
    public static final ListMultimap<String, BlockRegistryHandler> BLOCKS = ArrayListMultimap.create();


    public static void registerModels(GatherDataEvent event) {
        for (Map.Entry<String, Collection<BlockRegistryHandler>> entry : BLOCKS.asMap().entrySet()) {
            event.getGenerator().addProvider(new CustomBlockStateProvider(event, entry.getKey(), entry.getValue()));
        }

        for (Map.Entry<String, Collection<ItemRegistryHandler>> entry : ITEMS.asMap().entrySet()) {
            event.getGenerator().addProvider(new CustomItemModelProvider(event, entry.getKey(), entry.getValue()));
        }
    }
}
