package quarris.qlib.api.registry;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.IForgeRegistry;
import quarris.qlib.api.registry.components.block.ICustomBlockItem;
import quarris.qlib.api.registry.components.block.IOverrideItemProperties;

public class AbstractItemRegistry extends ContentRegistry<Item> {

    public AbstractItemRegistry(String modid, IForgeRegistry<Item> registry) {
        super(modid, registry);
    }

    public void registerBlockItems(ContentRegistry<Block> blockRegistry) {
        blockRegistry.registry.getEntries().stream().forEach(blockReg -> this.register(
                blockReg.getId().getPath(),
                () -> {
                    Block block = blockReg.get();
                    if (block instanceof ICustomBlockItem customItem) {
                        return customItem.buildItem();
                    } else {
                        Item.Properties properties = new Item.Properties();
                        if (block instanceof IOverrideItemProperties propOverride) {
                            propOverride.override(properties);
                        }
                        return new BlockItem(block, properties);
                    }
                }));
    }
}
