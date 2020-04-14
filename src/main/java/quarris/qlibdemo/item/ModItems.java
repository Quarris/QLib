package quarris.qlibdemo.item;

import net.minecraft.item.Item;
import quarris.qlib.common.content.item.ItemRegistry;
import quarris.qlibdemo.QLibDemo;

@ItemRegistry(QLibDemo.MODID)
public class ModItems {

    public static final ThingItem THINGY = new ThingItem(new Item.Properties());
    public static ThingItem THINGY1 = new ThingItem(new Item.Properties());
    public static final ThingItem THINGY2 = new ThingItem(new Item.Properties());

    public final ThingItem THINGY3 = new ThingItem(new Item.Properties());

    public static final ThingItem THINGY4 = new ThingItem(new Item.Properties());
    public static final ThingItem THINGY5 = new ThingItem(new Item.Properties());

    private static final ThingItem THINGY6 = new ThingItem(new Item.Properties());

    public static final ThingItem THINGY7 = new ThingItem(new Item.Properties());
    public static final ThingItem THINGY8 = new ThingItem(new Item.Properties());

}
