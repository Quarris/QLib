package quarris.qlib.mod.data.model;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

import java.util.Collection;
import java.util.Map;

public class ModelDataHandler {

    public static final ListMultimap<String, Item> ITEMS = ArrayListMultimap.create();
    public static final ListMultimap<String, Block> BLOCKS = ArrayListMultimap.create();


    public static void registerModels(GatherDataEvent event) {
        for (Map.Entry<String, Collection<Block>> entry : BLOCKS.asMap().entrySet()) {
            event.getGenerator().addProvider(new CustomBlockStateProvider(event, entry.getKey(), entry.getValue()));
        }

        for (Map.Entry<String, Collection<Item>> entry : ITEMS.asMap().entrySet()) {
            event.getGenerator().addProvider(new CustomItemModelProvider(event, entry.getKey(), entry.getValue()));
        }
    }
}
