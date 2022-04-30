package quarris.qlib.api.data.loottable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.HashCache;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import quarris.qlib.api.QLibApi;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

// Huge thanks to McJty for his modding tutorials on how loot table providers work.
// Most of the code comes from https://wiki.mcjty.eu/modding/index.php?title=Tut14_Ep7
public abstract class AbstractLootTableProvider<T> extends LootTableProvider {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    protected final Map<T, LootTable.Builder> lootTables = new HashMap<>();
    private final DataGenerator generator;

    public AbstractLootTableProvider(DataGenerator generator) {
        super(generator);
        this.generator = generator;
    }

    protected abstract void addTables();
    protected abstract ResourceLocation getLootTableLocation(T object);
    protected abstract LootContextParamSet getLootParameterSet();

    @Override
    public void run(HashCache cache) {
        this.addTables();

        Map<ResourceLocation, LootTable> tables = new HashMap<>();
        for (Map.Entry<T, LootTable.Builder> entry : this.lootTables.entrySet()) {
            tables.put(this.getLootTableLocation(entry.getKey()), entry.getValue().setParamSet(this.getLootParameterSet()).build());
        }

        Path outputFolder = this.generator.getOutputFolder();
        tables.forEach((key, lootTable) -> {
            Path path = outputFolder.resolve("data/" + key.getNamespace() + "/loot_tables/" + key.getPath() + ".json");
            try {
                DataProvider.save(GSON, cache, LootTables.serialize(lootTable), path);
            } catch (IOException e) {
                QLibApi.LOGGER.error("Couldn't write loot table {}", path, e);
            }
        });
    }
}
