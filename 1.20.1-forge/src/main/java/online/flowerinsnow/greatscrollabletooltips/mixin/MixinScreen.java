package online.flowerinsnow.greatscrollabletooltips.mixin;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import online.flowerinsnow.greatscrollabletooltips.GreatScrollableTooltips;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(GuiGraphics.class)
@OnlyIn(Dist.CLIENT)
public class MixinScreen {
    @ModifyArg(
            method = "lambda$renderTooltipInternal$5",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screens/inventory/tooltip/TooltipRenderUtil;renderTooltipBackground(Lnet/minecraft/client/gui/GuiGraphics;IIIIIIIII)V",
                    ordinal = 0
            ),
            index = 1,
            remap = false
    )
    private int modifyBackgroundX(int x) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        return x + (instance.getHorizontal() * instance.getConfig().sensitivity);
    }

    @ModifyArg(
            method = "renderTooltipInternal",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screens/inventory/tooltip/ClientTooltipComponent;renderText(Lnet/minecraft/client/gui/Font;IILorg/joml/Matrix4f;Lnet/minecraft/client/renderer/MultiBufferSource$BufferSource;)V",
                    ordinal = 0
            ),
            index = 1
    )
    private int modifyTextX(int x) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        return x + (instance.getHorizontal() * instance.getConfig().sensitivity);
    }

    @ModifyArg(
            method = "lambda$renderTooltipInternal$5",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screens/inventory/tooltip/TooltipRenderUtil;renderTooltipBackground(Lnet/minecraft/client/gui/GuiGraphics;IIIIIIIII)V",
                    ordinal = 0
            ),
            index = 2,
            remap = false
    )
    private int modifyBackgroundY(int y) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        return y + (instance.getVertical() * instance.getConfig().sensitivity);
    }

    @ModifyArg(
            method = "renderTooltipInternal",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screens/inventory/tooltip/ClientTooltipComponent;renderText(Lnet/minecraft/client/gui/Font;IILorg/joml/Matrix4f;Lnet/minecraft/client/renderer/MultiBufferSource$BufferSource;)V",
                    ordinal = 0
            ),
            index = 2
    )
    private int modifyTextY(int y) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        return y + (instance.getVertical() * instance.getConfig().sensitivity);
    }
}
