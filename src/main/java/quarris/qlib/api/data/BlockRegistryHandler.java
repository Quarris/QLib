package quarris.qlib.api.data;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import quarris.qlib.api.datagen.loottable.CustomBlockLootTableProvider;
import quarris.qlib.api.datagen.model.CustomBlockStateProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

public class BlockRegistryHandler {

    public static final Map<Block, BlockRegistryHandler> HANDLERS = new HashMap<>();

    public final Block block;
    public BlockItem blockItem;
    public Function<Item, ItemRegistryHandler> customBlockItemHandler;
    public Consumer<CustomBlockStateProvider> model;
    public Function<CustomBlockLootTableProvider, LootTable.Builder> lootTable;

    private BlockRegistryHandler(Block block) {
        this.block = block;
        this.blockItem = new BlockItem(block, new Item.Properties());
        this.customBlockItemHandler = ItemRegistryHandler::get;
        this.lootTable = provider -> provider.defaultLootTable(block);
        this.model = provider -> provider.defaultStateAndModel(block);
    }

    public static BlockRegistryHandler get(Block block) {
        return new BlockRegistryHandler(block);
    }

    public BlockRegistryHandler noBlockItem() {
        this.blockItem = null;
        return this;
    }

    public BlockRegistryHandler customBlockItem(BlockItem customBlockItem, Function<Item, ItemRegistryHandler> customHandler) {
        this.blockItem = customBlockItem;
        this.customBlockItemHandler = customHandler;
        return this;
    }

    public BlockRegistryHandler customBlockItem(BlockItem customBlockItem) {
        this.blockItem = customBlockItem;
        return this;
    }

    public BlockRegistryHandler customBlockItemHandler(Function<Item, ItemRegistryHandler> customHandler) {
        this.customBlockItemHandler = customHandler;
        return this;
    }

    public BlockRegistryHandler noModel() {
        this.model = provider -> {};
        return this;
    }

    public BlockRegistryHandler customModel(Consumer<CustomBlockStateProvider> customModel) {
        this.model = customModel;
        return this;
    }

    public BlockRegistryHandler noLootTable() {
        this.lootTable = provider -> null;
        return this;
    }

    public BlockRegistryHandler customLootTable(Function<CustomBlockLootTableProvider, LootTable.Builder> customLootTable) {
        this.lootTable = customLootTable;
        return this;
    }

    public void register() {
        HANDLERS.put(this.block, this);
    }

}
