package online.flowerinsnow.greatscrollabletooltips.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
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
import online.flowerinsnow.greatscrollabletooltips.event.RenderMouseoverTooltipEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractContainerScreen.class)
@OnlyIn(Dist.CLIENT)
public class MixinHandledScreen<T extends AbstractContainerMenu> extends Screen {
    @SuppressWarnings({"unchecked", "MissingUnique", "AddedMixinMembersNamePattern"})
    private final AbstractContainerScreen<T> THIS = (AbstractContainerScreen<T>) (Object) this;

    @Shadow
    @Final
    protected T menu;

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
        HandledScreenKeyPressedEvent event = new HandledScreenKeyPressedEvent(keyCode, scanCode, modifiers);
        if (MinecraftForge.EVENT_BUS.post(event)) {
            cir.setReturnValue(false);
        }
    }

    @Inject(
            method = "renderTooltip",
            at = @At("RETURN")
    )
    public void drawMouseoverTooltip(GuiGraphics graphics, int x, int y, CallbackInfo ci) {
        if (this.menu.getCarried().isEmpty() && this.hoveredSlot != null && this.hoveredSlot.hasItem()) {
            ItemStack itemStack = this.hoveredSlot.getItem();
            MinecraftForge.EVENT_BUS.post(new RenderMouseoverTooltipEvent.Post(this.THIS, graphics, itemStack, x, y));
        } else {
            MinecraftForge.EVENT_BUS.post(new RenderMouseoverTooltipEvent.Miss(this.THIS));
        }
    }
}
