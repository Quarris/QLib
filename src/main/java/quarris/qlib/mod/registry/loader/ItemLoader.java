package quarris.qlib.mod.registry.loader;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import quarris.qlib.api.QLibApi;
import quarris.qlib.api.data.ItemRegistryHandler;
import quarris.qlib.api.registry.ContentLoader;
import quarris.qlib.api.registry.registry.ItemRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ItemLoader extends ContentLoader<Item, ItemRegistry> {

    public final Map<String, DeferredRegister<Item>> registers = new HashMap<>();

    @Override
    protected void loadContent(String modId, String name, Supplier<Item> itemSupplier) {
        DeferredRegister<Item> registry = registers.computeIfAbsent(modId, id -> DeferredRegister.create(ForgeRegistries.ITEMS, id));
        registry.register(name, itemSupplier);

        //ItemRegistryHandler.HANDLERS.putIfAbsent(itemSupplier, ItemRegistryHandler.get(itemSupplier));
    }

    @Override
    protected Class<Item> getContentClass() {
        return Item.class;
    }

    @Override
    protected Class<ItemRegistry> getRegistryClass() {
        return ItemRegistry.class;
    }
}
