package quarris.qlib.common.content;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import quarris.qlib.QLib;
import quarris.qlib.common.content.item.ItemLoader;

import java.util.List;

@Mod.EventBusSubscriber(modid = QLib.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ContentRegister {

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> register) {
        System.out.println("Registering");
        List<Item> itemsToRegister = ItemLoader.loadItems();
        for (Item item : itemsToRegister) {
            register.getRegistry().register(item);
        }
    }
}
