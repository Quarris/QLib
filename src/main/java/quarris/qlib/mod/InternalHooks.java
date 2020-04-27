package quarris.qlib.mod;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.LootTableProvider;
import quarris.qlib.api.QLibApi;
import quarris.qlib.mod.data.LootTableDataHandler;

import java.util.function.Function;

public class InternalHooks implements QLibApi.IInternals {
    @Override
    public void addLootTableProvider(Function<DataGenerator, LootTableProvider> provider) {
        LootTableDataHandler.addProvider(provider);
    }
}
