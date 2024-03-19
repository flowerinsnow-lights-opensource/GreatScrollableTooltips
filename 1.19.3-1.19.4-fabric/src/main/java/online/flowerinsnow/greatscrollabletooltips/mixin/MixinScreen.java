package online.flowerinsnow.greatscrollabletooltips.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import online.flowerinsnow.greatscrollabletooltips.GreatScrollableTooltips;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(Screen.class)
@Environment(EnvType.CLIENT)
public class MixinScreen {
    @ModifyArg(
            method = "renderTooltipFromComponents",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/tooltip/TooltipBackgroundRenderer;render(Lnet/minecraft/client/gui/tooltip/TooltipBackgroundRenderer$RectangleRenderer;Lorg/joml/Matrix4f;Lnet/minecraft/client/render/BufferBuilder;IIIII)V",
                    ordinal = 0
            ),
            index = 3
    )
    public int modifyBackgroundX(int x) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        return x + (instance.getHorizontal() * instance.getConfig().sensitivity);
    }

    @ModifyArg(
            method = "renderTooltipFromComponents",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/tooltip/TooltipComponent;drawText(Lnet/minecraft/client/font/TextRenderer;IILorg/joml/Matrix4f;Lnet/minecraft/client/render/VertexConsumerProvider$Immediate;)V",
                    ordinal = 0
            ),
            index = 1
    )
    public int modifyTextX(int x) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        return x + (instance.getHorizontal() * instance.getConfig().sensitivity);
    }

    @ModifyArg(
            method = "renderTooltipFromComponents",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/tooltip/TooltipBackgroundRenderer;render(Lnet/minecraft/client/gui/tooltip/TooltipBackgroundRenderer$RectangleRenderer;Lorg/joml/Matrix4f;Lnet/minecraft/client/render/BufferBuilder;IIIII)V",
                    ordinal = 0
            ),
            index = 4
    )
    public int modifyBackgroundY(int y) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        return y + (instance.getVertical() * instance.getConfig().sensitivity);
    }

    @ModifyArg(
            method = "renderTooltipFromComponents",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/tooltip/TooltipComponent;drawText(Lnet/minecraft/client/font/TextRenderer;IILorg/joml/Matrix4f;Lnet/minecraft/client/render/VertexConsumerProvider$Immediate;)V",
                    ordinal = 0
            ),
            index = 2
    )
    public int modifyTextY(int y) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        return y + (instance.getVertical() * instance.getConfig().sensitivity);
    }
}
