package quarris.qlib.api.registry;

import com.mojang.serialization.Codec;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;

public class AbstractParticleTypeRegistry extends ContentRegistry<ParticleType<?>> {

    public AbstractParticleTypeRegistry(String modid, IForgeRegistry<ParticleType<?>> registry) {
        super(modid, registry);
    }

    protected RegistryObject<ParticleType<?>> register(String name, boolean alwaysShow) {
        return this.register(name, () -> new SimpleParticleType(alwaysShow));
    }

    protected <T extends ParticleOptions> RegistryObject<ParticleType<T>> register(String name, ParticleOptions.Deserializer<T> deserializer, Function<ParticleType<T>, Codec<T>> codec) {
        return this.register(name, false, deserializer, codec);
    }

    protected <T extends ParticleOptions> RegistryObject<ParticleType<T>> register(String name, boolean alwaysShow, ParticleOptions.Deserializer<T> deserializer, Function<ParticleType<T>, Codec<T>> codec) {
        return this.register(name, () -> new ParticleType<>(alwaysShow, deserializer) {
            @Override
            public Codec<T> codec() {
                return codec.apply(this);
            }
        });
    }
}
