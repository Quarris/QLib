package quarris.qlib.api.registry;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public abstract class ContentRegistry<T extends IForgeRegistryEntry<T>>{

    final DeferredRegister<T> registry;

    public ContentRegistry(String modid, IForgeRegistry<T> registry) {
        this.registry = DeferredRegister.create(registry, modid);
    }

    public ContentRegistry<T> init(IEventBus bus) {
        this.registry.register(bus);
        return this;
    }

    protected <R extends T> RegistryObject<R> register(String name, Supplier<R> supp) {
        return this.registry.register(name, supp);
    }
}
