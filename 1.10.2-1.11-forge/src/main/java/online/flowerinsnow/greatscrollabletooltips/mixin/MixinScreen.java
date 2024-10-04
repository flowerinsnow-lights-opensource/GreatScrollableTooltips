package online.flowerinsnow.greatscrollabletooltips.mixin;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import online.flowerinsnow.greatscrollabletooltips.event.ScreenKeyPressedEvent;
import online.flowerinsnow.greatscrollabletooltips.event.ScreenMouseScrollEvent;
import org.lwjgl.input.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiScreen.class)
@SideOnly(Side.CLIENT)
public class MixinScreen extends Gui {
    @Unique
    private final GuiScreen THIS = (GuiScreen) (Object) this;

    @Inject(
            method = "keyTyped",
            at = @At("HEAD"),
            cancellable = true
    )
    protected void preKeyTyped(char typedChar, int keyCode, CallbackInfo ci) {
        if (MinecraftForge.EVENT_BUS.post(new ScreenKeyPressedEvent.Pre(this.THIS, typedChar, keyCode))) {
            ci.cancel();
        }
    }

    @Inject(
            method = "keyTyped",
            at = @At("HEAD")
    )
    protected void postKeyTyped(char typedChar, int keyCode, CallbackInfo ci) {
        MinecraftForge.EVENT_BUS.post(new ScreenKeyPressedEvent.Post(this.THIS, typedChar, keyCode));
    }

    @Inject(
            method = "handleMouseInput",
            at = @At("RETURN")
    )
    protected void postHandleMouseInput(CallbackInfo ci) {
        int amount = Mouse.getEventDWheel();
        if (amount == 0) {
            return;
        }

        if (amount > 1) {
            amount = 1;
        } else if (amount < -1) {
            amount = -1;
        }

        MinecraftForge.EVENT_BUS.post(new ScreenMouseScrollEvent(this.THIS, amount));
    }
}
