package online.flowerinsnow.greatscrollabletooltips.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import online.flowerinsnow.greatscrollabletooltips.event.HandledScreenKeyPressedEvent;
import online.flowerinsnow.greatscrollabletooltips.event.RenderMouseoverTooltipEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(HandledScreen.class)
@Environment(EnvType.CLIENT)
public abstract class MixinHandledScreen<T extends ScreenHandler> extends Screen {
    @Unique
    @SuppressWarnings("unchecked")
    private final HandledScreen<T> THIS = (HandledScreen<T>) (Object) this;

    @Shadow
    @Final
    protected T handler;

    @Shadow
    protected Slot focusedSlot;

    @Shadow
    protected abstract List<Text> getTooltipFromItem(ItemStack stack);

    protected MixinHandledScreen() {
        super(null);
    }


    @Inject(
            method = "keyPressed",
            at = @At("HEAD"),
            cancellable = true
    )
    public void keyPressed(int keyCode, int scanCode, int modifiers, CallbackInfoReturnable<Boolean> cir) {
        ActionResult actionResult = HandledScreenKeyPressedEvent.EVENT.invoker().keyPressed(keyCode, scanCode, modifiers);
        if (actionResult == ActionResult.SUCCESS || actionResult == ActionResult.FAIL) {
            cir.setReturnValue(false);
        }
    }

    @Inject(
            method = "drawMouseoverTooltip",
            at = @At("RETURN")
    )
    public void drawMouseoverTooltip(DrawContext context, int x, int y, CallbackInfo ci) {
        if (this.handler.getCursorStack().isEmpty() && this.focusedSlot != null && this.focusedSlot.hasStack()) {
            ItemStack itemStack = this.focusedSlot.getStack();
            RenderMouseoverTooltipEvent.Post.EVENT.invoker().endDrawMouseoverTooltip(this.THIS, this.textRenderer, itemStack, this.getTooltipFromItem(itemStack), context, x, y);
        } else {
            RenderMouseoverTooltipEvent.Miss.EVENT.invoker().onMiss(THIS);
        }
    }
}
