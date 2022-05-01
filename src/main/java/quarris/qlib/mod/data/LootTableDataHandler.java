package quarris.qlib.mod.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import quarris.qlib.api.datagen.loottable.CustomBlockLootTableProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class LootTableDataHandler {

    private static final List<Function<DataGenerator, LootTableProvider>> PROVIDERS = new ArrayList<>();

    public static void registerLootTables(GatherDataEvent event) {
        gatherLootTables();

        PROVIDERS.stream()
                .map(func -> func.apply(event.getGenerator()))
                .forEach(provider -> event.getGenerator().addProvider(provider));


    }

    public static void addProvider(Function<DataGenerator, LootTableProvider> provider) {
        PROVIDERS.add(provider);
    }

    private static void gatherLootTables() {
        addProvider(CustomBlockLootTableProvider::new);
    }

}
