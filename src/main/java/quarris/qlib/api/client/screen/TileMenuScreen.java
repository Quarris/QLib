package quarris.qlib.api.client.screen;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;
import quarris.qlib.api.client.util.NinePatch;
import quarris.qlib.api.client.util.NinePatches;
import quarris.qlib.api.container.TileContainerMenu;
import quarris.qlib.api.util.math.Rectangle;

public class TileMenuScreen<T extends TileContainerMenu> extends AbstractContainerScreen<T> {

    public NinePatch containerPatch;
    public NinePatch slotPatch;

    public TileMenuScreen(T container, Inventory inv, Component title) {
        super(container, inv, title);
        this.setContainerPatch(NinePatches.MC_CONTAINER);
        this.setSlotPatch(NinePatches.MC_SLOT);
    }

    public void drawContainer(PoseStack matrix) {
        this.containerPatch.render(matrix, new Rectangle(this.leftPos, this.topPos, this.getXSize(), this.getYSize()));
    }

    public void drawSlots(PoseStack matrix) {
        for (Slot slot : this.menu.slots) {
            slotPatch.render(matrix, new Rectangle(this.leftPos + slot.x - 1, this.topPos + slot.y - 1, 18, 18));
        }
    }

    public void drawBackground(PoseStack matrix) {
        this.renderBackground(matrix);
        this.drawContainer(matrix);
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        this.drawBackground(pPoseStack);
        this.drawSlots(pPoseStack);
    }

    public void setContainerPatch(NinePatch containerPatch) {
        this.containerPatch = containerPatch;
    }

    public void setSlotPatch(NinePatch slotPatch) {
        this.slotPatch = slotPatch;
    }
}
