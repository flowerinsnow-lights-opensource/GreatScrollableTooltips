package online.flowerinsnow.greatscrollabletooltips.mixin;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraftforge.common.MinecraftForge;
import online.flowerinsnow.greatscrollabletooltips.event.RenderTooltipEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(GuiContainer.class)
public class MixinHandledScreen {
    @Unique
    private final GuiContainer THIS = (GuiContainer) (Object) this;

    @Shadow
    private Slot theSlot;

    @Inject(
            method = "drawScreen",
            at = @At(
                    value = "INVOKE_ASSIGN",
                    target = "Lnet/minecraft/client/renderer/GlStateManager;popMatrix()V",
                    ordinal = 0
            ),
            locals = LocalCapture.CAPTURE_FAILSOFT
    )
    public void preRenderTooltip(int mouseX, int mouseY, float partialTicks, CallbackInfo ci, InventoryPlayer inventoryplayer) {
        if (inventoryplayer.getItemStack().isEmpty() && this.theSlot != null && this.theSlot.getHasStack()) {
            MinecraftForge.EVENT_BUS.post(new RenderTooltipEvent.Pre(this.THIS, this.theSlot));
        } else {
            MinecraftForge.EVENT_BUS.post(new RenderTooltipEvent.Miss(this.THIS));
        }
    }
}
