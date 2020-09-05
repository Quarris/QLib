package quarris.qlib.api.registry.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface EntityRegistry {

    /**
     * The mod id to register the entities for.
     *
     * @return The modid namespace
     */
    String value();
}
