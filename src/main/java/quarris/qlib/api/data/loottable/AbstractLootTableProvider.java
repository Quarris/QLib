package quarris.qlib.api.data.loottable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.data.LootTableProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootParameterSet;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.LootTableManager;
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
    protected abstract LootParameterSet getLootParameterSet();

    @Override
    public void act(DirectoryCache cache) {
        this.addTables();

        Map<ResourceLocation, LootTable> tables = new HashMap<>();
        for (Map.Entry<T, LootTable.Builder> entry : this.lootTables.entrySet()) {
            tables.put(this.getLootTableLocation(entry.getKey()), entry.getValue().setParameterSet(this.getLootParameterSet()).build());
        }

        Path outputFolder = this.generator.getOutputFolder();
        tables.forEach((key, lootTable) -> {
            Path path = outputFolder.resolve("data/" + key.getNamespace() + "/loot_tables/" + key.getPath() + ".json");
            try {
                IDataProvider.save(GSON, cache, LootTableManager.toJson(lootTable), path);
            } catch (IOException e) {
                QLibApi.LOGGER.error("Couldn't write loot table {}", path, e);
            }
        });
    }
}
