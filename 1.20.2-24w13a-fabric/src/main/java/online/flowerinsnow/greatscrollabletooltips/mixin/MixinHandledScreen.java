package online.flowerinsnow.greatscrollabletooltips.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.ActionResult;
import online.flowerinsnow.greatscrollabletooltips.event.RenderTooltipEvent;
import online.flowerinsnow.greatscrollabletooltips.event.HandledScreenKeyPressedEvent;
import online.flowerinsnow.greatscrollabletooltips.event.MouseScrolledInScreenEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HandledScreen.class)
@Environment(EnvType.CLIENT)
public abstract class MixinHandledScreen extends Screen {
    @Unique
    private final HandledScreen<?> THIS = (HandledScreen<?>) (Object) this;

    @Shadow
    @Final
    protected ScreenHandler handler;
    @Shadow
    protected Slot focusedSlot;

    protected MixinHandledScreen() {
        super(null);
    }

    @Inject(
            method = "keyPressed",
            at = @At("HEAD"),
            cancellable = true
    )
    public void keyPressed(int keyCode, int scanCode, int modifiers, CallbackInfoReturnable<Boolean> cir) {
        ActionResult actionResult = HandledScreenKeyPressedEvent.EVENT.invoker().keyPressed(this.THIS, keyCode, scanCode, modifiers);
        if (actionResult == ActionResult.FAIL) {
            cir.setReturnValue(false);
        }
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
        ActionResult actionResult = MouseScrolledInScreenEvent.EVENT.invoker()
                .onMouseScrolled(this.THIS, mouseX, mouseY, horizontalAmount, verticalAmount);
        if (actionResult == ActionResult.FAIL) {
            return false;
        }
        return super.mouseScrolled(mouseX, mouseY, horizontalAmount, verticalAmount);
    }

    @Inject(
            method = "drawMouseoverTooltip",
            at = @At(
                    value = "RETURN",
                    ordinal = 0
            )
    )
    public void postDrawMouseoverTooltip(DrawContext context, int x, int y, CallbackInfo ci) {
        if (this.handler.getCursorStack().isEmpty() && this.focusedSlot != null && this.focusedSlot.hasStack()) {
            ItemStack itemStack = this.focusedSlot.getStack();
            RenderTooltipEvent.Post.EVENT.invoker().postRenderTooltip(this.THIS, context, this.textRenderer, itemStack, x, y);
        } else {
            RenderTooltipEvent.Miss.EVENT.invoker().missRenderTooltip(this.THIS, context, this.textRenderer, x, y);
        }
    }
}
