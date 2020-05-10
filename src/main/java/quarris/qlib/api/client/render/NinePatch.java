package quarris.qlib.api.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import quarris.qlib.api.QLibApi;
import quarris.qlib.api.util.math.Point;
import quarris.qlib.api.util.math.Rectangle;

@OnlyIn(Dist.CLIENT)
public class NinePatch {

    public final ResourceLocation texture;
    private final Point textureSize;

    public final Rectangle textureRegion;
    public final Padding padding;
    private final Rectangle[] patches;

    public NinePatch(ResourceLocation texture, Point textureSize, Rectangle textureRegion, Padding padding) {
        this.texture = texture;
        this.textureSize = textureSize;
        this.textureRegion = textureRegion;
        this.padding = padding;

        Rectangle centerPatch = textureRegion.shrink(padding.left, padding.top, padding.right, padding.bottom);
        int paddingWidth = centerPatch.width;
        int paddingHeight = centerPatch.height;

        this.patches = new Rectangle[9];
        this.patches[0] = textureRegion.shrink(0, 0, padding.right + paddingWidth, padding.bottom + paddingHeight);
        this.patches[1] = textureRegion.shrink(padding.left, 0, padding.right, padding.bottom + paddingHeight);
        this.patches[2] = textureRegion.shrink(padding.left + paddingWidth, 0, 0, padding.bottom + paddingHeight);
        textureRegion = textureRegion.shrink(0, padding.top, 0, 0);
        this.patches[3] = textureRegion.shrink(0, 0, padding.right + paddingWidth, padding.bottom);
        this.patches[4] = textureRegion.shrink(padding.left, 0, padding.right, padding.bottom);
        this.patches[5] = textureRegion.shrink(padding.left + paddingWidth, 0, 0, padding.bottom);
        textureRegion = textureRegion.shrink(0, paddingHeight, 0, 0);
        this.patches[6] = textureRegion.shrink(0, 0, padding.right + paddingWidth, 0);
        this.patches[7] = textureRegion.shrink(padding.left, 0, padding.right, 0);
        this.patches[8] = textureRegion.shrink(padding.left + paddingWidth, 0, 0, 0);
    }

    /**
     * Renders this NinePatch in the current context.
     * Loads the texture and the calls the equivalent of AbstractGui#innerBlit on each patch.
     * This method is defined only in the context of drawing a screen. Using this method in other contexts may produce undefined behaviour.
     *
     * @param dimensions The size of the patch to draw it at. Setting this smaller than the texture (or negative) will make the rendering look weird
     */
    public void render(Rectangle dimensions) {
        Minecraft.getInstance().textureManager.bindTexture(this.texture);
        Minecraft.getInstance().textureManager.getTexture(this.texture);

        int paddingWidth = dimensions.width - padding.getWidth();
        int paddingHeight = dimensions.height - padding.getHeight();
        Point left = dimensions.getPosition();
        RenderHelper.blit(new Rectangle(left.x, left.y, padding.left, padding.top), patches[0], textureSize);
        RenderHelper.blit(new Rectangle(left.x + padding.left, left.y, paddingWidth, padding.top), patches[1], textureSize);
        RenderHelper.blit(new Rectangle(left.x + padding.left + paddingWidth, left.y, padding.right, padding.top), patches[2], textureSize);
        left = left.add(0, padding.top);
        RenderHelper.blit(new Rectangle(left.x, left.y, padding.left, paddingHeight), patches[3], textureSize);
        RenderHelper.blit(new Rectangle(left.x + padding.left, left.y, paddingWidth, paddingHeight), patches[4], textureSize);
        RenderHelper.blit(new Rectangle(left.x + padding.left + paddingWidth, left.y, padding.right, paddingHeight), patches[5], textureSize);
        left = left.add(0, paddingHeight);
        RenderHelper.blit(new Rectangle(left.x, left.y, padding.left, padding.bottom), patches[6], textureSize);
        RenderHelper.blit(new Rectangle(left.x + padding.left, left.y, paddingWidth, padding.bottom), patches[7], textureSize);
        RenderHelper.blit(new Rectangle(left.x + padding.left + paddingWidth, left.y, padding.right, padding.bottom), patches[8], textureSize);
    }
}
