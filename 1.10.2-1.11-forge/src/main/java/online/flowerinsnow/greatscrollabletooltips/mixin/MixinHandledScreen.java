package online.flowerinsnow.greatscrollabletooltips.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.MinecraftForge;
import online.flowerinsnow.greatscrollabletooltips.event.RenderTooltipEvent;
import online.flowerinsnow.greatscrollabletooltips.event.ScreenKeyPressedEvent;
import online.flowerinsnow.greatscrollabletooltips.event.ScreenMouseScrollEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.io.IOException;

@Mixin(GuiContainer.class)
public class MixinHandledScreen extends GuiScreen {
    @Unique
    private final GuiContainer THIS = (GuiContainer) (Object) this;

    @Shadow
    private Slot theSlot;

    @Inject(
            method = "drawScreen",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/player/InventoryPlayer;getItemStack()Lnet/minecraft/item/ItemStack;",
                    ordinal = 1
            )
    )
    public void preRenderTooltip(int mouseX, int mouseY, float partialTicks, CallbackInfo ci) {
        InventoryPlayer inventoryplayer = this.mc.player.inventory;
        if (inventoryplayer.getItemStack().isEmpty() && this.theSlot != null && this.theSlot.getHasStack()) {
            MinecraftForge.EVENT_BUS.post(new RenderTooltipEvent.Pre(this.THIS, this.theSlot));
        } else {
            MinecraftForge.EVENT_BUS.post(new RenderTooltipEvent.Miss(this.THIS));
        }
    }

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
            at = @At("RETURN")
    )
    protected void postKeyTyped(char typedChar, int keyCode, CallbackInfo ci) {
        MinecraftForge.EVENT_BUS.post(new ScreenKeyPressedEvent.Post(this.THIS, typedChar, keyCode));
    }

    @Override
    public void handleMouseInput() throws IOException {
        super.handleMouseInput();
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
