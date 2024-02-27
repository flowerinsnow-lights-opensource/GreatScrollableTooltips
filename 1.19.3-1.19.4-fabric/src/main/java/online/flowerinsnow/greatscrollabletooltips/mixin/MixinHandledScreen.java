package online.flowerinsnow.greatscrollabletooltips.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import online.flowerinsnow.greatscrollabletooltips.event.RenderMouseoverTooltipEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HandledScreen.class)
@Environment(EnvType.CLIENT)
public class MixinHandledScreen<T extends ScreenHandler> extends Screen {
    @Unique
    @SuppressWarnings("unchecked")
    private final HandledScreen<T> THIS = (HandledScreen<T>) (Object) this;

    @Shadow
    @Final
    protected T handler;

    @Shadow
    protected Slot focusedSlot;

    protected MixinHandledScreen() {
        super(null);
    }

    @Inject(
            method = "drawMouseoverTooltip",
            at = @At("RETURN")
    )
    public void drawMouseoverTooltip(MatrixStack matrices, int x, int y, CallbackInfo ci) {
        if (this.handler.getCursorStack().isEmpty() && this.focusedSlot != null && this.focusedSlot.hasStack()) {
            RenderMouseoverTooltipEvent.Post.EVENT.invoker().startDrawMouseoverTooltip(THIS, matrices, this.focusedSlot.getStack(), x, y);
        } else {
            RenderMouseoverTooltipEvent.Miss.EVENT.invoker().onMiss(THIS);
        }
    }
}
