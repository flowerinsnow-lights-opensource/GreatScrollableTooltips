package online.flowerinsnow.greatscrollabletooltips.mixin;

import com.anthonyhilyard.legendarytooltips.tooltip.ItemModelComponent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import online.flowerinsnow.greatscrollabletooltips.GreatScrollableTooltips;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(ItemModelComponent.class)
@Environment(EnvType.CLIENT)
public class MixinItemModelComponent {
    @ModifyArgs(
            method = "drawItems",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/anthonyhilyard/iceberg/util/GuiHelper;drawGradientRect(Lorg/joml/Matrix4f;IIIIIII)V",
                    ordinal = 0
            )
    )
    public void modifyPos0(Args args) {
        this.modifyXY(args);
    }

    @ModifyArgs(
            method = "drawItems",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/anthonyhilyard/iceberg/util/GuiHelper;drawGradientRect(Lorg/joml/Matrix4f;IIIIIII)V",
                    ordinal = 1
            )
    )
    public void modifyPos1(Args args) {
        this.modifyXY(args);
    }

    @ModifyArgs(
            method = "drawItems",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/anthonyhilyard/iceberg/util/GuiHelper;drawGradientRect(Lorg/joml/Matrix4f;IIIIIII)V",
                    ordinal = 2
            )
    )
    public void modifyPos2(Args args) {
        this.modifyXY(args);
    }

    @ModifyArgs(
            method = "drawItems",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/anthonyhilyard/iceberg/util/GuiHelper;drawGradientRect(Lorg/joml/Matrix4f;IIIIIII)V",
                    ordinal = 3
            )
    )
    public void modifyPos3(Args args) {
        this.modifyXY(args);
    }

    @ModifyArgs(
            method = "drawItems",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/anthonyhilyard/iceberg/util/GuiHelper;drawGradientRect(Lorg/joml/Matrix4f;IIIIIII)V",
                    ordinal = 4
            )
    )
    public void modifyPos4(Args args) {
        this.modifyXY(args);
    }

    @ModifyArgs(
            method = "drawItems",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/anthonyhilyard/iceberg/util/GuiHelper;drawGradientRect(Lorg/joml/Matrix4f;IIIIIII)V",
                    ordinal = 5
            )
    )
    public void modifyPos5(Args args) {
        this.modifyXY(args);
    }

    @ModifyArgs(
            method = "drawItems",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/anthonyhilyard/iceberg/util/GuiHelper;drawGradientRectHorizontal(Lorg/joml/Matrix4f;IIIIIII)V",
                    ordinal = 0
            )
    )
    public void modifyPos6(Args args) {
        this.modifyXY(args);
    }

    @ModifyArgs(
            method = "drawItems",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/anthonyhilyard/iceberg/util/GuiHelper;drawGradientRectHorizontal(Lorg/joml/Matrix4f;IIIIIII)V",
                    ordinal = 1
            )
    )
    public void modifyPos7(Args args) {
        this.modifyXY(args);
    }

    @ModifyArgs(
            method = "drawItems",
            at = @At(
                    value = "INVOKE",
                    target = "Lorg/joml/Matrix4fStack;translate(FFF)Lorg/joml/Matrix4f;",
                    ordinal = 0
            )
    )
    public void modifyModelPos(Args args) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        args.set(0, ((float) args.get(0)) + (float) instance.getModX());
        args.set(1, ((float) args.get(1)) + (float) instance.getModY());
    }

    @Unique
    private void modifyXY(Args args) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        args.set(2, ((int) args.get(2)) + instance.getModX());
        args.set(4, ((int) args.get(4)) + instance.getModX());

        args.set(3, ((int) args.get(3)) + instance.getModY());
        args.set(5, ((int) args.get(5)) + instance.getModY());
    }
}
