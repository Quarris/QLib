package quarris.qlib.api.registry;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.IForgeRegistry;

public class AbstractItemRegistry extends ContentRegistry<Item> {

    public AbstractItemRegistry(String modid, IForgeRegistry<Item> registry) {
        super(modid, registry);
    }

    public void registerBlockItems(ContentRegistry<Block> blockRegistry, CreativeModeTab tab) {
        blockRegistry.registry.getEntries().stream().forEach(block -> {
            this.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
        });
    }
}
