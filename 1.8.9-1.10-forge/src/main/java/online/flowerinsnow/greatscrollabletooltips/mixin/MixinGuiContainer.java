package online.flowerinsnow.greatscrollabletooltips.mixin;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import online.flowerinsnow.greatscrollabletooltips.event.GuiContainerKeyTypedEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiContainer.class)
@SideOnly(Side.CLIENT)
public class MixinGuiContainer {
    private final GuiContainer THIS = (GuiContainer) (Object) this;

    @Inject(
            method = "keyTyped",
            at = @At("HEAD"),
            cancellable = true
    )
    public void preKeyTyped(char typedChar, int keyCode, CallbackInfo ci) {
        if (MinecraftForge.EVENT_BUS.post(new GuiContainerKeyTypedEvent.Pre(THIS, typedChar, keyCode))) {
            ci.cancel();
        }
    }

    @Inject(
            method = "keyTyped",
            at = @At("RETURN")
    )
    public void postKeyTyped(char typedChar, int keyCode, CallbackInfo ci) {
        MinecraftForge.EVENT_BUS.post(new GuiContainerKeyTypedEvent.Post(THIS, typedChar, keyCode));
    }
}
