package online.flowerinsnow.greatscrollabletooltips.mixin;

import com.anthonyhilyard.legendarytooltips.tooltip.TooltipDecor;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import online.flowerinsnow.greatscrollabletooltips.GreatScrollableTooltips;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(TooltipDecor.class)
@Environment(EnvType.CLIENT)
public class MixinTooltipDecor {
    @ModifyArgs(
            method = "drawShadow",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/anthonyhilyard/iceberg/util/GuiHelper;drawGradientRect(Lorg/joml/Matrix4f;IIIIIII)V",
                    ordinal = 0
            )
    )
    private static void modifyShadowPos0(Args args) {
        modifyXY(args);
    }
    @ModifyArgs(
            method = "drawShadow",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/anthonyhilyard/iceberg/util/GuiHelper;drawGradientRect(Lorg/joml/Matrix4f;IIIIIII)V",
                    ordinal = 1
            )
    )
    private static void modifyShadowPos1(Args args) {
        modifyXY(args);
    }
    @ModifyArgs(
            method = "drawShadow",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/anthonyhilyard/iceberg/util/GuiHelper;drawGradientRect(Lorg/joml/Matrix4f;IIIIIII)V",
                    ordinal = 2
            )
    )
    private static void modifyShadowPos2(Args args) {
        modifyXY(args);
    }
    @ModifyArgs(
            method = "drawShadow",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/anthonyhilyard/iceberg/util/GuiHelper;drawGradientRect(Lorg/joml/Matrix4f;IIIIIII)V",
                    ordinal = 3
            )
    )
    private static void modifyShadowPos3(Args args) {
        modifyXY(args);
    }
    @ModifyArgs(
            method = "drawShadow",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/anthonyhilyard/iceberg/util/GuiHelper;drawGradientRect(Lorg/joml/Matrix4f;IIIIIII)V",
                    ordinal = 4
            )
    )
    private static void modifyShadowPos4(Args args) {
        modifyXY(args);
    }

    @ModifyArgs(
            method = "drawSeparator",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/anthonyhilyard/iceberg/util/GuiHelper;drawGradientRectHorizontal(Lorg/joml/Matrix4f;IIIIIII)V",
                    ordinal = 0
            )
    )
    private static void modifyDrawSeparator0(Args args) {
        modifyXY(args);
    }
    @ModifyArgs(
            method = "drawSeparator",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/anthonyhilyard/iceberg/util/GuiHelper;drawGradientRectHorizontal(Lorg/joml/Matrix4f;IIIIIII)V",
                    ordinal = 1
            )
    )
    private static void modifyDrawSeparator1(Args args) {
        modifyXY(args);
    }

    @Unique
    private static void modifyXY(Args args) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        args.set(2, ((int) args.get(2)) + instance.getModX());
        args.set(4, ((int) args.get(4)) + instance.getModX());

        args.set(3, ((int) args.get(3)) + instance.getModY());
        args.set(5, ((int) args.get(5)) + instance.getModY());
    }
}
