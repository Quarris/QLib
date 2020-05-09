package quarris.qlib.api.client.render;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldVertexBufferUploader;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quarris.qlib.api.util.math.Point;
import quarris.qlib.api.util.math.Rectangle;

@OnlyIn(Dist.CLIENT)
public class RenderHelper {

    protected static void blit(Rectangle draw, Rectangle uv, Point texSize) {
        float texSizeX = (float) texSize.x;
        float texSizeY = (float) texSize.y;

        BufferBuilder bufferbuilder = Tessellator.getInstance().getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferbuilder.pos(draw.getBottomLeft().x, draw.getBottomLeft().y, 0).tex(uv.getBottomLeft().x / texSizeX, uv.getBottomLeft().y / texSizeY).endVertex();
        bufferbuilder.pos(draw.getBottomRight().x, draw.getBottomRight().y, 0).tex(uv.getBottomRight().x / texSizeX, uv.getBottomRight().y / texSizeY).endVertex();
        bufferbuilder.pos(draw.getTopRight().x, draw.getTopRight().y, 0).tex(uv.getTopRight().x / texSizeX, uv.getTopRight().y / texSizeY).endVertex();
        bufferbuilder.pos(draw.getTopLeft().x, draw.getTopLeft().y, 0).tex(uv.getTopLeft().x / texSizeX, uv.getTopLeft().y / texSizeY).endVertex();
        bufferbuilder.finishDrawing();
        RenderSystem.enableAlphaTest();
        WorldVertexBufferUploader.draw(bufferbuilder);
    }


}
