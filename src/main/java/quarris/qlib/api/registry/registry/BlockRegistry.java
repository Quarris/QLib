package quarris.qlib.api.registry.registry;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface BlockRegistry {

    /**
     * The mod id to register the blocks for.
     *
     * @return The modid namespace
     */
    String value();
}
