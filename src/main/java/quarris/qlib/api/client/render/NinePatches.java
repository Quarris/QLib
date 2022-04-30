package quarris.qlib.api.client.render;

import net.minecraft.resources.ResourceLocation;
import quarris.qlib.api.QLibApi;
import quarris.qlib.api.util.math.Point;
import quarris.qlib.api.util.math.Rectangle;

public class NinePatches {

    private static final Point DEFAULT_TEX_SIZE = new Point(18, 18);
    private static final ResourceLocation DEFAULT_NINEPATCH = new ResourceLocation(QLibApi.MODID, "textures/gui/default_ninepatch.png");

    public static final NinePatch MC_CONTAINER = new NinePatch(DEFAULT_NINEPATCH, DEFAULT_TEX_SIZE, new Rectangle(0, 0, 9, 9), new Padding(4));
    public static final NinePatch MC_CONTAINER_INV = new NinePatch(DEFAULT_NINEPATCH, DEFAULT_TEX_SIZE, new Rectangle(9, 0, 9, 9), new Padding(4));
    public static final NinePatch MC_SLOT = new NinePatch(DEFAULT_NINEPATCH, DEFAULT_TEX_SIZE, new Rectangle(0, 9, 3, 3), new Padding(1));
    public static final NinePatch MC_SLOT_INV = new NinePatch(DEFAULT_NINEPATCH, DEFAULT_TEX_SIZE, new Rectangle(9, 9, 3, 3), new Padding(1));

}
