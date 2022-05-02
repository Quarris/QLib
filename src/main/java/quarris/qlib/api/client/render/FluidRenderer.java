package quarris.qlib.api.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3d;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;
import quarris.qlib.api.client.helper.ColorHelper;

public class FluidRenderer {

    public static void renderFluidFace(Level level, BlockPos blockPos, PoseStack matrix, FluidStack stack, VertexConsumer buffer, Direction dir, Vector3d center, float radius, int packedLight) {
        matrix.pushPose();
        Fluid fluid = stack.getFluid();
        int[] colors = ColorHelper.fromColori(fluid.getAttributes().getColor(level, blockPos));
        TextureAtlasSprite sprite = getFluidSprites(level, blockPos, fluid.getAttributes())[0];
        matrix.translate(center.x, center.y, center.z);
        float minU = sprite.getU0();
        float minV = sprite.getV0();
        float maxU = sprite.getU1();
        float maxV = sprite.getV1();
        vertex(matrix, buffer, -radius, 0, -radius, colors[0], colors[1], colors[2], colors[3], minU, minV, packedLight);
        vertex(matrix, buffer, -radius, 0, radius, colors[0], colors[1], colors[2], colors[3], minU, maxV, packedLight);
        vertex(matrix, buffer, radius, 0, radius, colors[0], colors[1], colors[2], colors[3], maxU, maxV, packedLight);
        vertex(matrix, buffer, radius, 0, -radius, colors[0], colors[1], colors[2], colors[3], maxU, minV, packedLight);
        matrix.popPose();
    }

    private static void vertex(PoseStack matrix, VertexConsumer buffer,
                               float x, float y, float z,
                               int r, int g, int b, int a,
                               float u, float v,
                               int packedLight
    ) {
        buffer.vertex(matrix.last().pose(), x, y, z).color(r, g, b, a).uv(u, v).uv2(packedLight).normal(matrix.last().normal(), 0, 1, 0).endVertex();
    }

    private static TextureAtlasSprite[] getFluidSprites(BlockAndTintGetter level, BlockPos pos, FluidAttributes fluidAttributes) {
        ResourceLocation overlayTexture = fluidAttributes.getOverlayTexture();
        return new TextureAtlasSprite[]{
                Minecraft.getInstance().getTextureAtlas(TextureAtlas.LOCATION_BLOCKS).apply(fluidAttributes.getStillTexture(level, pos)),
                Minecraft.getInstance().getTextureAtlas(TextureAtlas.LOCATION_BLOCKS).apply(fluidAttributes.getFlowingTexture(level, pos)),
                overlayTexture == null ? null : Minecraft.getInstance().getTextureAtlas(TextureAtlas.LOCATION_BLOCKS).apply(overlayTexture),
        };
    }
}
