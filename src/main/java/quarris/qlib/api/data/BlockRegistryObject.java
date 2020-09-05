package quarris.qlib.api.data;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.world.storage.loot.LootTable;
import quarris.qlib.api.data.loottable.CustomBlockLootTableProvider;
import quarris.qlib.api.data.model.CustomBlockStateProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

public class BlockRegistryObject {

    public static final Map<Block, BlockRegistryObject> HANDLERS = new HashMap<>();

    public final Block block;
    public BlockItem blockItem;
    public Function<Item, ItemRegistryObject> customBlockItemHandler;
    public Consumer<CustomBlockStateProvider> model;
    public Function<CustomBlockLootTableProvider, LootTable.Builder> lootTable;

    private BlockRegistryObject(Block block) {
        this.block = block;
        this.blockItem = new BlockItem(block, new Item.Properties());
        this.customBlockItemHandler = ItemRegistryObject::get;
        this.lootTable = provider -> provider.defaultLootTable(block);
        this.model = provider -> provider.defaultStateAndModel(block);
    }

    public static BlockRegistryObject get(Block block) {
        return new BlockRegistryObject(block);
    }

    public BlockRegistryObject noBlockItem() {
        this.blockItem = null;
        return this;
    }

    public BlockRegistryObject customBlockItem(BlockItem customBlockItem, Function<Item, ItemRegistryObject> customHandler) {
        this.blockItem = customBlockItem;
        this.customBlockItemHandler = customHandler;
        return this;
    }

    public BlockRegistryObject customBlockItem(BlockItem customBlockItem) {
        this.blockItem = customBlockItem;
        return this;
    }

    public BlockRegistryObject customBlockItemHandler(Function<Item, ItemRegistryObject> customHandler) {
        this.customBlockItemHandler = customHandler;
        return this;
    }

    public BlockRegistryObject noModel() {
        this.model = provider -> {};
        return this;
    }

    public BlockRegistryObject customModel(Consumer<CustomBlockStateProvider> customModel) {
        this.model = customModel;
        return this;
    }

    public BlockRegistryObject noLootTable() {
        this.lootTable = provider -> null;
        return this;
    }

    public BlockRegistryObject customLootTable(Function<CustomBlockLootTableProvider, LootTable.Builder> customLootTable) {
        this.lootTable = customLootTable;
        return this;
    }

    public void register() {
        HANDLERS.put(this.block, this);
    }

}
