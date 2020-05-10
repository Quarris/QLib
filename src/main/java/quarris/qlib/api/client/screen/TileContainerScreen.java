package quarris.qlib.api.client.screen;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.util.text.ITextComponent;
import quarris.qlib.api.client.render.NinePatch;
import quarris.qlib.api.client.render.NinePatches;
import quarris.qlib.api.container.TileContainer;
import quarris.qlib.api.util.math.Rectangle;

public class TileContainerScreen<T extends TileContainer> extends ContainerScreen<T> {

    public NinePatch containerPatch;
    public NinePatch slotPatch;

    public TileContainerScreen(T container, PlayerInventory inv, ITextComponent titleIn) {
        super(container, inv, titleIn);
        this.setContainerPatch(NinePatches.MC_CONTAINER);
        this.setSlotPatch(NinePatches.MC_SLOT);
    }

    public void drawSlots() {
        for (Slot slot : this.container.inventorySlots) {
            slotPatch.render(new Rectangle(this.guiLeft + slot.xPos - 1, this.guiTop + slot.yPos - 1, 18, 18));
        }
    }

    public void drawContainer() {
        this.containerPatch.render(new Rectangle(this.guiLeft, this.guiTop, this.xSize, this.ySize));
    }

    public void drawBackground() {
        this.renderBackground();
        this.drawContainer();
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        this.drawBackground();
        this.drawSlots();
    }

    public void setContainerPatch(NinePatch containerPatch) {
        this.containerPatch = containerPatch;
    }

    public void setSlotPatch(NinePatch slotPatch) {
        this.slotPatch = slotPatch;
    }
}
