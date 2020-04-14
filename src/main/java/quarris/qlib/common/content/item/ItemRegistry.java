package quarris.qlib.common.content.item;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface ItemRegistry {

    /**
     * The mod id to register the items for.
     *
     * @return The modid namespace
     */
    String value();
}
