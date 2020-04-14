package quarris.qlibdemo.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import quarris.qlib.common.reg.block.BlockRegistry;
import quarris.qlibdemo.QLibDemo;

@BlockRegistry(QLibDemo.MODID)
public class ModBlocks {
    public static final ThingBlock BLOCK_THING = new ThingBlock(Block.Properties.create(Material.ROCK));
    private static final ThingBlock BLOCK_THING1 = new ThingBlock(Block.Properties.create(Material.ROCK));
    public static final ThingBlock BLOCK_THING2 = new ThingBlock(Block.Properties.create(Material.ROCK));
    public static ThingBlock BLOCK_THING3 = new ThingBlock(Block.Properties.create(Material.ROCK));
    public static final ThingBlock BLOCK_THING4 = new ThingBlock(Block.Properties.create(Material.ROCK));
    public final ThingBlock BLOCK_THING5 = new ThingBlock(Block.Properties.create(Material.ROCK));
    public static final ThingBlock BLOCK_THING6 = new ThingBlock(Block.Properties.create(Material.ROCK));
    public static final ThingBlock BLOCK_THING7 = new ThingBlock(Block.Properties.create(Material.ROCK));
    public static ThingBlock BLOCK_THING8;
}
