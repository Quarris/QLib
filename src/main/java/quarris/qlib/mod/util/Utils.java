package quarris.qlib.mod.util;

import net.minecraft.util.Direction;

public class Utils {

    public static Direction getDirectionFromNormVec(int x, int y, int z) {
        int val = Math.abs(y) * (    (y+1)/2)
                + Math.abs(z) * (2 + (z+1)/2)
                + Math.abs(x) * (4 + (x+1)/2);

        return Direction.byIndex(val);
    }

}
