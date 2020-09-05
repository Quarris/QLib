package quarris.qlib.api.util;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
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

    public static ItemStack createPlayerHead(String playerName) {
        //minecraft:player_head{SkullOwner:"<PlayerName>"}
        CompoundNBT nbt = new CompoundNBT();
        nbt.putString("SkullOwner", playerName);
        ItemStack head = new ItemStack(Items.PLAYER_HEAD);
        head.setTag(nbt);
        return head;
    }
}
