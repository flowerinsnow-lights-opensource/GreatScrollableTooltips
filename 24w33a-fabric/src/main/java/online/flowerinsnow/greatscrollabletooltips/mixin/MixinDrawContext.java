package online.flowerinsnow.greatscrollabletooltips.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import online.flowerinsnow.greatscrollabletooltips.GreatScrollableTooltips;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(DrawContext.class)
@Environment(EnvType.CLIENT)
public class MixinDrawContext {
    @ModifyArgs(
            method = "drawTooltip(Lnet/minecraft/client/font/TextRenderer;Ljava/util/List;IILnet/minecraft/client/gui/tooltip/TooltipPositioner;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/tooltip/TooltipComponent;drawText(Lnet/minecraft/client/font/TextRenderer;IILorg/joml/Matrix4f;Lnet/minecraft/client/render/VertexConsumerProvider$Immediate;)V",
                    ordinal = 0
            )
    )
    public void modifyTextPos(Args args) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        args.set(1, ((int) args.get(1)) + instance.getModX());
        args.set(2, ((int) args.get(2)) + instance.getModY());
    }

    @ModifyArgs(
            method = "drawTooltip(Lnet/minecraft/client/font/TextRenderer;Ljava/util/List;IILnet/minecraft/client/gui/tooltip/TooltipPositioner;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/tooltip/TooltipBackgroundRenderer;render(Lnet/minecraft/client/gui/DrawContext;IIIII)V",
                    ordinal = 0
            )
    )
    public void modifyBackground(Args args) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        args.set(1, ((int) args.get(1)) + instance.getModX());
        args.set(2, ((int) args.get(2)) + instance.getModY());
    }
}
