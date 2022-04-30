package quarris.qlib.mod.registry.loader;

import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import quarris.qlib.api.QLibApi;
import quarris.qlib.api.registry.ContentLoader;
import quarris.qlib.api.registry.registry.ContainerRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class MenuLoader extends ContentLoader<MenuType, ContainerRegistry> {

    public final Map<String, DeferredRegister<MenuType<?>>> registers = new HashMap<>();

    @Override
    protected void loadContent(String modId, String name, Supplier<MenuType> containerSupplier) {
        DeferredRegister<MenuType<?>> registry = registers.computeIfAbsent(modId, id -> DeferredRegister.create(ForgeRegistries.CONTAINERS, id));
        registry.register(name, containerSupplier);
    }

    @Override
    protected Class<MenuType> getContentClass() {
        return MenuType.class;
    }

    @Override
    protected Class<ContainerRegistry> getRegistryClass() {
        return ContainerRegistry.class;
    }
}
