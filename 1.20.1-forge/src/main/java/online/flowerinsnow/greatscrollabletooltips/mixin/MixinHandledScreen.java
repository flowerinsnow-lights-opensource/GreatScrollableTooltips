package online.flowerinsnow.greatscrollabletooltips.mixin;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import online.flowerinsnow.greatscrollabletooltips.event.HandledScreenKeyPressedEvent;
import online.flowerinsnow.greatscrollabletooltips.event.MouseScrolledInScreenEvent;
import online.flowerinsnow.greatscrollabletooltips.event.RenderTooltipEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractContainerScreen.class)
@OnlyIn(Dist.CLIENT)
public class MixinHandledScreen extends Screen {
    @SuppressWarnings("AddedMixinMembersNamePattern")
    @Unique
    private final AbstractContainerScreen<?> THIS = (AbstractContainerScreen<?>) (Object) this;

    @Shadow
    @Final
    protected AbstractContainerMenu menu;
    @Shadow
    protected Slot hoveredSlot;

    protected MixinHandledScreen() {
        //noinspection DataFlowIssue
        super(null);
    }


    @Inject(
            method = "keyPressed",
            at = @At("HEAD"),
            cancellable = true
    )
    public void keyPressed(int keyCode, int scanCode, int modifiers, CallbackInfoReturnable<Boolean> cir) {
        HandledScreenKeyPressedEvent event = new HandledScreenKeyPressedEvent(this.THIS, keyCode, scanCode, modifiers);
        if (MinecraftForge.EVENT_BUS.post(event)) {
            cir.setReturnValue(false);
        }
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        MouseScrolledInScreenEvent event = new MouseScrolledInScreenEvent(this.THIS, mouseX, mouseY, amount);
        if (MinecraftForge.EVENT_BUS.post(event)) {
            return false;
        }
        return super.mouseScrolled(mouseX, mouseY, amount);
    }

    @Inject(
            method = "renderTooltip",
            at = @At("RETURN")
    )
    public void postDrawMouseoverTooltip(GuiGraphics graphics, int x, int y, CallbackInfo ci) {
        if (this.menu.getCarried().isEmpty() && this.hoveredSlot != null && this.hoveredSlot.hasItem()) {
            ItemStack itemStack = this.hoveredSlot.getItem();
            MinecraftForge.EVENT_BUS.post(new RenderTooltipEvent.Post(this.THIS, graphics, itemStack, x, y));
        } else {
            MinecraftForge.EVENT_BUS.post(new RenderTooltipEvent.Miss(this.THIS));
        }
    }
}
