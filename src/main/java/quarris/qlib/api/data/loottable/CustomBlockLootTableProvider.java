package quarris.qlib.api.data.loottable;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import quarris.qlib.api.QLibApi;
import quarris.qlib.api.data.BlockRegistryHandler;

public class CustomBlockLootTableProvider extends BlockLootTableProvider {

    public CustomBlockLootTableProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void addTables() {
        for (Block block : QLibApi.BLOCKS) {
            this.lootTables.put(block, BlockRegistryHandler.HANDLERS.get(block).lootTable.apply(this));
        }
    }
}
