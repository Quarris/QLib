package quarris.qlib.api.util;

import net.minecraft.state.IntegerProperty;
import net.minecraft.util.Direction;

public class Utils {

    public static Direction getDirectionFromNormVec(int x, int y, int z) {
        int val = Math.abs(y) * (    (y+1)/2)
                + Math.abs(z) * (2 + (z+1)/2)
                + Math.abs(x) * (4 + (x+1)/2);

        return Direction.byIndex(val);
    }

    @SuppressWarnings("all") // IntegerPropery has to contain *A* value
    public static int maxFromIntProp(IntegerProperty prop) {
        return prop.getAllowedValues().stream().max(Integer::compareTo).get();
    }
}
