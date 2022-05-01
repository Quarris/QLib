package quarris.qlib.api.datagen.loottable;

import net.minecraft.data.DataGenerator;

public class CustomBlockLootTableProvider extends BlockLootTableProvider {

    public CustomBlockLootTableProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void addTables() {
        /*for (Block block : QLibApi.BLOCKS) {
            LootTable.Builder builder = BlockRegistryHandler.HANDLERS.get(block).lootTable.apply(this);
            if (builder != null) {
                this.lootTables.put(block, builder);
            }
        }*/
    }
}
