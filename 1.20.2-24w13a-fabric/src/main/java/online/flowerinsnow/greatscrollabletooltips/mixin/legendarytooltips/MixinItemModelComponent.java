package online.flowerinsnow.greatscrollabletooltips.mixin.legendarytooltips;

import com.anthonyhilyard.legendarytooltips.tooltip.ItemModelComponent;
import net.minecraft.item.ItemStack;
import online.flowerinsnow.greatscrollabletooltips.GreatScrollableTooltips;
import online.flowerinsnow.greatscrollabletooltips.object.ScrollSession;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(ItemModelComponent.class)
public class MixinItemModelComponet {
    @ModifyArgs(
            method = "drawItems",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/anthonyhilyard/iceberg/util/GuiHelper;drawGradientRect(Lorg/joml/Matrix4f;IIIIIII)V",
                    ordinal = 0
            )
    )
    public void modifyDraw0(Args args) {
        this.modifyArgs(args);
    }

    @ModifyArgs(
            method = "drawItems",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/anthonyhilyard/iceberg/util/GuiHelper;drawGradientRect(Lorg/joml/Matrix4f;IIIIIII)V",
                    ordinal = 1
            )
    )
    public void modifyDraw1(Args args) {
        this.modifyArgs(args);
    }

    @ModifyArgs(
            method = "drawItems",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/anthonyhilyard/iceberg/util/GuiHelper;drawGradientRectHorizontal(Lorg/joml/Matrix4f;IIIIIII)V",
                    ordinal = 0
            )
    )
    public void modifyDraw2(Args args) {
        this.modifyArgs(args);
    }

    @ModifyArgs(
            method = "drawItems",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/anthonyhilyard/iceberg/util/GuiHelper;drawGradientRectHorizontal(Lorg/joml/Matrix4f;IIIIIII)V",
                    ordinal = 1
            )
    )
    public void modifyDraw3(Args args) {
        this.modifyArgs(args);
    }

    @ModifyArgs(
            method = "drawItems",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/anthonyhilyard/iceberg/util/GuiHelper;drawGradientRect(Lorg/joml/Matrix4f;IIIIIII)V",
                    ordinal = 2
            )
    )
    public void modifyDraw4(Args args) {
        this.modifyArgs(args);
    }

    @ModifyArgs(
            method = "drawItems",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/anthonyhilyard/iceberg/util/GuiHelper;drawGradientRect(Lorg/joml/Matrix4f;IIIIIII)V",
                    ordinal = 3
            )
    )
    public void modifyDraw5(Args args) {
        this.modifyArgs(args);
    }

    @ModifyArgs(
            method = "drawItems",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/anthonyhilyard/iceberg/util/GuiHelper;drawGradientRect(Lorg/joml/Matrix4f;IIIIIII)V",
                    ordinal = 4
            )
    )
    public void modifyDraw6(Args args) {
        this.modifyArgs(args);
    }

    @ModifyArgs(
            method = "drawItems",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/anthonyhilyard/iceberg/util/GuiHelper;drawGradientRect(Lorg/joml/Matrix4f;IIIIIII)V",
                    ordinal = 5
            )
    )
    public void modifyDraw7(Args args) {
        this.modifyArgs(args);
    }



    @ModifyArgs(
            method = "drawItems",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/util/math/MatrixStack;translate(FFF)V",
                    ordinal = 0
            )
    )
    public void modifyTranslate(Args args) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        ScrollSession<ItemStack> session = instance.getScrollSession();
        int sensitivity = instance.getConfig().sensitivity;
        args.set(0, ((int) args.get(0)) + session.getHorizontal() * sensitivity);
        args.set(1, ((int) args.get(1)) + session.getVertical() * sensitivity);
    }



    @ModifyArgs(
            method = "drawItems",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/anthonyhilyard/iceberg/renderer/CustomItemRenderer;renderDetailModelIntoGUI(Lnet/minecraft/item/ItemStack;IILorg/joml/Quaternionf;Lnet/minecraft/client/gui/DrawContext;)V",
                    ordinal = 0
            )
    )
    public void modifyModel(Args args) {
        this.modifyArgs(args);
    }

    @Unique
    private void modifyArgs(Args args) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        ScrollSession<ItemStack> session = instance.getScrollSession();
        int sensitivity = instance.getConfig().sensitivity;
        args.set(1, ((int) args.get(1)) + session.getHorizontal() * sensitivity);
        args.set(2, ((int) args.get(2)) + session.getVertical() * sensitivity);
        args.set(3, ((int) args.get(3)) + session.getHorizontal() * sensitivity);
        args.set(4, ((int) args.get(4)) + session.getVertical() * sensitivity);
    }
}
