package quarris.qlib.api.client.helper;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quarris.qlib.api.util.math.Point;
import quarris.qlib.api.util.math.Rectangle;

@OnlyIn(Dist.CLIENT)
public class RenderHelper {

    public static void blit(PoseStack matrix, Rectangle rect, Rectangle uv, Point texSize) {
        GuiComponent.blit(matrix, rect.left(), rect.top(), rect.width, rect.height, uv.left(), uv.top(), uv.width, uv.height, texSize.x, texSize.y);
    }

}
