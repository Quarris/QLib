package quarris.qlib.api.data.advancement;

import net.minecraft.advancements.DisplayInfo;
import net.minecraft.advancements.FrameType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class DisplayInfoBuilder {

    private ItemStack icon;
    private ITextComponent title;
    private ITextComponent description;
    private ResourceLocation background;
    private FrameType frame;
    private boolean showToast;
    private boolean announceToChat;
    private boolean hidden;

    public DisplayInfoBuilder icon(ItemStack icon) {
        this.icon = icon;
        return this;
    }

    public DisplayInfoBuilder title(ITextComponent title) {
        this.title = title;
        return this;
    }

    public DisplayInfoBuilder title(String title) {
        this.title = new TranslationTextComponent(title);
        return this;
    }

    public DisplayInfoBuilder description(ITextComponent description) {
        this.description = description;
        return this;
    }

    public DisplayInfoBuilder description(String description) {
        this.description = new TranslationTextComponent(description);
        return this;
    }

    public DisplayInfoBuilder background(ResourceLocation background) {
        this.background = background;
        return this;
    }

    public DisplayInfoBuilder frame(FrameType frame) {
        this.frame = frame;
        return this;
    }

    public DisplayInfoBuilder showToast() {
        this.showToast = true;
        return this;
    }

    public DisplayInfoBuilder announceToChat() {
        this.announceToChat = true;
        return this;
    }

    public DisplayInfoBuilder hidden() {
        this.hidden = true;
        return this;
    }

    public DisplayInfo build() {
        return new DisplayInfo(this.icon, this.title, this.description, this.background, this.frame, this.showToast, this.announceToChat, this.hidden);
    }
}
