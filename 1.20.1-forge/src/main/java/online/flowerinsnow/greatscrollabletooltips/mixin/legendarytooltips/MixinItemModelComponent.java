package online.flowerinsnow.greatscrollabletooltips.mixin.legendarytooltips;

import com.anthonyhilyard.legendarytooltips.tooltip.ItemModelComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import online.flowerinsnow.greatscrollabletooltips.GreatScrollableTooltips;
import online.flowerinsnow.greatscrollabletooltips.object.ScrollSession;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(ItemModelComponent.class)
@OnlyIn(Dist.CLIENT)
public class MixinItemModelComponent {
    @ModifyArg(
            method = "renderImage",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/anthonyhilyard/iceberg/util/GuiHelper;drawGradientRect(Lorg/joml/Matrix4f;IIIIIII)V",
                    ordinal = 0
            ),
            index = 2
    )
    public int modifyDrawX0(int x) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        ScrollSession<ItemStack> session = instance.getScrollSession();
        int sensitivity = instance.getConfig().sensitivity;
        return x + session.getHorizontal() * sensitivity;
    }

    @ModifyArg(
            method = "renderImage",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/anthonyhilyard/iceberg/util/GuiHelper;drawGradientRect(Lorg/joml/Matrix4f;IIIIIII)V",
                    ordinal = 0
            ),
            index = 3
    )
    public int modifyDrawY0(int y) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        ScrollSession<ItemStack> session = instance.getScrollSession();
        int sensitivity = instance.getConfig().sensitivity;
        return y + session.getVertical() * sensitivity;
    }

    @ModifyArg(
            method = "renderImage",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/anthonyhilyard/iceberg/util/GuiHelper;drawGradientRect(Lorg/joml/Matrix4f;IIIIIII)V",
                    ordinal = 0
            ),
            index = 4
    )
    public int modifyDrawEndX0(int endX) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        ScrollSession<ItemStack> session = instance.getScrollSession();
        int sensitivity = instance.getConfig().sensitivity;
        return endX + session.getHorizontal() * sensitivity;
    }

    @ModifyArg(
            method = "renderImage",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/anthonyhilyard/iceberg/util/GuiHelper;drawGradientRect(Lorg/joml/Matrix4f;IIIIIII)V",
                    ordinal = 0
            ),
            index = 5
    )
    public int modifyDrawEndY0(int endY) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        ScrollSession<ItemStack> session = instance.getScrollSession();
        int sensitivity = instance.getConfig().sensitivity;
        return endY + session.getVertical() * sensitivity;
    }



    @ModifyArg(
            method = "renderImage",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/anthonyhilyard/iceberg/util/GuiHelper;drawGradientRect(Lorg/joml/Matrix4f;IIIIIII)V",
                    ordinal = 1
            ),
            index = 2
    )
    public int modifyDrawX1(int x) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        ScrollSession<ItemStack> session = instance.getScrollSession();
        int sensitivity = instance.getConfig().sensitivity;
        return x + session.getHorizontal() * sensitivity;
    }

    @ModifyArg(
            method = "renderImage",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/anthonyhilyard/iceberg/util/GuiHelper;drawGradientRect(Lorg/joml/Matrix4f;IIIIIII)V",
                    ordinal = 1
            ),
            index = 3
    )
    public int modifyDrawY1(int y) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        ScrollSession<ItemStack> session = instance.getScrollSession();
        int sensitivity = instance.getConfig().sensitivity;
        return y + session.getVertical() * sensitivity;
    }

    @ModifyArg(
            method = "renderImage",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/anthonyhilyard/iceberg/util/GuiHelper;drawGradientRect(Lorg/joml/Matrix4f;IIIIIII)V",
                    ordinal = 1
            ),
            index = 4
    )
    public int modifyDrawEndX1(int endX) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        ScrollSession<ItemStack> session = instance.getScrollSession();
        int sensitivity = instance.getConfig().sensitivity;
        return endX + session.getHorizontal() * sensitivity;
    }

    @ModifyArg(
            method = "renderImage",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/anthonyhilyard/iceberg/util/GuiHelper;drawGradientRect(Lorg/joml/Matrix4f;IIIIIII)V",
                    ordinal = 1
            ),
            index = 5
    )
    public int modifyDrawEndY1(int endY) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        ScrollSession<ItemStack> session = instance.getScrollSession();
        int sensitivity = instance.getConfig().sensitivity;
        return endY + session.getVertical() * sensitivity;
    }



    @ModifyArg(
            method = "renderImage",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/anthonyhilyard/iceberg/util/GuiHelper;drawGradientRectHorizontal(Lorg/joml/Matrix4f;IIIIIII)V",
                    ordinal = 0
            ),
            index = 2
    )
    public int modifyDrawX2(int x) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        ScrollSession<ItemStack> session = instance.getScrollSession();
        int sensitivity = instance.getConfig().sensitivity;
        return x + session.getHorizontal() * sensitivity;
    }

    @ModifyArg(
            method = "renderImage",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/anthonyhilyard/iceberg/util/GuiHelper;drawGradientRectHorizontal(Lorg/joml/Matrix4f;IIIIIII)V",
                    ordinal = 0
            ),
            index = 3
    )
    public int modifyDrawY2(int y) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        ScrollSession<ItemStack> session = instance.getScrollSession();
        int sensitivity = instance.getConfig().sensitivity;
        return y + session.getVertical() * sensitivity;
    }

    @ModifyArg(
            method = "renderImage",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/anthonyhilyard/iceberg/util/GuiHelper;drawGradientRectHorizontal(Lorg/joml/Matrix4f;IIIIIII)V",
                    ordinal = 0
            ),
            index = 4
    )
    public int modifyDrawEndX2(int endX) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        ScrollSession<ItemStack> session = instance.getScrollSession();
        int sensitivity = instance.getConfig().sensitivity;
        return endX + session.getHorizontal() * sensitivity;
    }

    @ModifyArg(
            method = "renderImage",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/anthonyhilyard/iceberg/util/GuiHelper;drawGradientRectHorizontal(Lorg/joml/Matrix4f;IIIIIII)V",
                    ordinal = 0
            ),
            index = 5
    )
    public int modifyDrawEndY2(int endY) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        ScrollSession<ItemStack> session = instance.getScrollSession();
        int sensitivity = instance.getConfig().sensitivity;
        return endY + session.getVertical() * sensitivity;
    }



    @ModifyArg(
            method = "renderImage",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/anthonyhilyard/iceberg/util/GuiHelper;drawGradientRectHorizontal(Lorg/joml/Matrix4f;IIIIIII)V",
                    ordinal = 1
            ),
            index = 2
    )
    public int modifyDrawX3(int x) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        ScrollSession<ItemStack> session = instance.getScrollSession();
        int sensitivity = instance.getConfig().sensitivity;
        return x + session.getHorizontal() * sensitivity;
    }

    @ModifyArg(
            method = "renderImage",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/anthonyhilyard/iceberg/util/GuiHelper;drawGradientRectHorizontal(Lorg/joml/Matrix4f;IIIIIII)V",
                    ordinal = 1
            ),
            index = 3
    )
    public int modifyDrawY3(int y) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        ScrollSession<ItemStack> session = instance.getScrollSession();
        int sensitivity = instance.getConfig().sensitivity;
        return y + session.getVertical() * sensitivity;
    }

    @ModifyArg(
            method = "renderImage",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/anthonyhilyard/iceberg/util/GuiHelper;drawGradientRectHorizontal(Lorg/joml/Matrix4f;IIIIIII)V",
                    ordinal = 1
            ),
            index = 4
    )
    public int modifyDrawEndX3(int endX) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        ScrollSession<ItemStack> session = instance.getScrollSession();
        int sensitivity = instance.getConfig().sensitivity;
        return endX + session.getHorizontal() * sensitivity;
    }

    @ModifyArg(
            method = "renderImage",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/anthonyhilyard/iceberg/util/GuiHelper;drawGradientRectHorizontal(Lorg/joml/Matrix4f;IIIIIII)V",
                    ordinal = 1
            ),
            index = 5
    )
    public int modifyDrawEndY3(int endY) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        ScrollSession<ItemStack> session = instance.getScrollSession();
        int sensitivity = instance.getConfig().sensitivity;
        return endY + session.getVertical() * sensitivity;
    }



    @ModifyArg(
            method = "renderImage",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/anthonyhilyard/iceberg/util/GuiHelper;drawGradientRect(Lorg/joml/Matrix4f;IIIIIII)V",
                    ordinal = 2
            ),
            index = 2
    )
    public int modifyDrawX4(int x) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        ScrollSession<ItemStack> session = instance.getScrollSession();
        int sensitivity = instance.getConfig().sensitivity;
        return x + session.getHorizontal() * sensitivity;
    }

    @ModifyArg(
            method = "renderImage",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/anthonyhilyard/iceberg/util/GuiHelper;drawGradientRect(Lorg/joml/Matrix4f;IIIIIII)V",
                    ordinal = 2
            ),
            index = 3
    )
    public int modifyDrawY4(int y) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        ScrollSession<ItemStack> session = instance.getScrollSession();
        int sensitivity = instance.getConfig().sensitivity;
        return y + session.getVertical() * sensitivity;
    }

    @ModifyArg(
            method = "renderImage",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/anthonyhilyard/iceberg/util/GuiHelper;drawGradientRect(Lorg/joml/Matrix4f;IIIIIII)V",
                    ordinal = 2
            ),
            index = 4
    )
    public int modifyDrawEndX4(int endX) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        ScrollSession<ItemStack> session = instance.getScrollSession();
        int sensitivity = instance.getConfig().sensitivity;
        return endX + session.getHorizontal() * sensitivity;
    }

    @ModifyArg(
            method = "renderImage",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/anthonyhilyard/iceberg/util/GuiHelper;drawGradientRect(Lorg/joml/Matrix4f;IIIIIII)V",
                    ordinal = 2
            ),
            index = 5
    )
    public int modifyDrawEndY4(int endY) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        ScrollSession<ItemStack> session = instance.getScrollSession();
        int sensitivity = instance.getConfig().sensitivity;
        return endY + session.getVertical() * sensitivity;
    }



    @ModifyArg(
            method = "renderImage",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/anthonyhilyard/iceberg/util/GuiHelper;drawGradientRect(Lorg/joml/Matrix4f;IIIIIII)V",
                    ordinal = 3
            ),
            index = 2
    )
    public int modifyDrawX5(int x) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        ScrollSession<ItemStack> session = instance.getScrollSession();
        int sensitivity = instance.getConfig().sensitivity;
        return x + session.getHorizontal() * sensitivity;
    }

    @ModifyArg(
            method = "renderImage",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/anthonyhilyard/iceberg/util/GuiHelper;drawGradientRect(Lorg/joml/Matrix4f;IIIIIII)V",
                    ordinal = 3
            ),
            index = 3
    )
    public int modifyDrawY5(int y) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        ScrollSession<ItemStack> session = instance.getScrollSession();
        int sensitivity = instance.getConfig().sensitivity;
        return y + session.getVertical() * sensitivity;
    }

    @ModifyArg(
            method = "renderImage",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/anthonyhilyard/iceberg/util/GuiHelper;drawGradientRect(Lorg/joml/Matrix4f;IIIIIII)V",
                    ordinal = 3
            ),
            index = 4
    )
    public int modifyDrawEndX5(int endX) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        ScrollSession<ItemStack> session = instance.getScrollSession();
        int sensitivity = instance.getConfig().sensitivity;
        return endX + session.getHorizontal() * sensitivity;
    }

    @ModifyArg(
            method = "renderImage",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/anthonyhilyard/iceberg/util/GuiHelper;drawGradientRect(Lorg/joml/Matrix4f;IIIIIII)V",
                    ordinal = 3
            ),
            index = 5
    )
    public int modifyDrawEndY5(int endY) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        ScrollSession<ItemStack> session = instance.getScrollSession();
        int sensitivity = instance.getConfig().sensitivity;
        return endY + session.getVertical() * sensitivity;
    }



    @ModifyArg(
            method = "renderImage",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/anthonyhilyard/iceberg/util/GuiHelper;drawGradientRect(Lorg/joml/Matrix4f;IIIIIII)V",
                    ordinal = 4
            ),
            index = 2
    )
    public int modifyDrawX6(int x) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        ScrollSession<ItemStack> session = instance.getScrollSession();
        int sensitivity = instance.getConfig().sensitivity;
        return x + session.getHorizontal() * sensitivity;
    }

    @ModifyArg(
            method = "renderImage",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/anthonyhilyard/iceberg/util/GuiHelper;drawGradientRect(Lorg/joml/Matrix4f;IIIIIII)V",
                    ordinal = 4
            ),
            index = 3
    )
    public int modifyDrawY6(int y) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        ScrollSession<ItemStack> session = instance.getScrollSession();
        int sensitivity = instance.getConfig().sensitivity;
        return y + session.getVertical() * sensitivity;
    }

    @ModifyArg(
            method = "renderImage",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/anthonyhilyard/iceberg/util/GuiHelper;drawGradientRect(Lorg/joml/Matrix4f;IIIIIII)V",
                    ordinal = 4
            ),
            index = 4
    )
    public int modifyDrawEndX6(int endX) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        ScrollSession<ItemStack> session = instance.getScrollSession();
        int sensitivity = instance.getConfig().sensitivity;
        return endX + session.getHorizontal() * sensitivity;
    }

    @ModifyArg(
            method = "renderImage",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/anthonyhilyard/iceberg/util/GuiHelper;drawGradientRect(Lorg/joml/Matrix4f;IIIIIII)V",
                    ordinal = 4
            ),
            index = 5
    )
    public int modifyDrawEndY6(int endY) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        ScrollSession<ItemStack> session = instance.getScrollSession();
        int sensitivity = instance.getConfig().sensitivity;
        return endY + session.getVertical() * sensitivity;
    }



    @ModifyArg(
            method = "renderImage",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/anthonyhilyard/iceberg/util/GuiHelper;drawGradientRect(Lorg/joml/Matrix4f;IIIIIII)V",
                    ordinal = 5
            ),
            index = 2
    )
    public int modifyDrawX7(int x) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        ScrollSession<ItemStack> session = instance.getScrollSession();
        int sensitivity = instance.getConfig().sensitivity;
        return x + session.getHorizontal() * sensitivity;
    }

    @ModifyArg(
            method = "renderImage",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/anthonyhilyard/iceberg/util/GuiHelper;drawGradientRect(Lorg/joml/Matrix4f;IIIIIII)V",
                    ordinal = 5
            ),
            index = 3
    )
    public int modifyDrawY7(int y) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        ScrollSession<ItemStack> session = instance.getScrollSession();
        int sensitivity = instance.getConfig().sensitivity;
        return y + session.getVertical() * sensitivity;
    }

    @ModifyArg(
            method = "renderImage",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/anthonyhilyard/iceberg/util/GuiHelper;drawGradientRect(Lorg/joml/Matrix4f;IIIIIII)V",
                    ordinal = 5
            ),
            index = 4
    )
    public int modifyDrawEndX7(int endX) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        ScrollSession<ItemStack> session = instance.getScrollSession();
        int sensitivity = instance.getConfig().sensitivity;
        return endX + session.getHorizontal() * sensitivity;
    }

    @ModifyArg(
            method = "renderImage",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/anthonyhilyard/iceberg/util/GuiHelper;drawGradientRect(Lorg/joml/Matrix4f;IIIIIII)V",
                    ordinal = 5
            ),
            index = 5
    )
    public int modifyDrawEndY7(int endY) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        ScrollSession<ItemStack> session = instance.getScrollSession();
        int sensitivity = instance.getConfig().sensitivity;
        return endY + session.getVertical() * sensitivity;
    }



    @ModifyArg(
            method = "renderImage",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/mojang/blaze3d/vertex/PoseStack;translate(FFF)V",
                    ordinal = 0
            ),
            index = 0
    )
    public float modifyTranslateX(float x) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        ScrollSession<ItemStack> session = instance.getScrollSession();
        int sensitivity = instance.getConfig().sensitivity;
        return x + (float) (session.getHorizontal() * sensitivity);
    }

    @ModifyArg(
            method = "renderImage",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/mojang/blaze3d/vertex/PoseStack;translate(FFF)V",
                    ordinal = 0
            ),
            index = 1
    )
    public float modifyTranslateY(float y) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        ScrollSession<ItemStack> session = instance.getScrollSession();
        int sensitivity = instance.getConfig().sensitivity;
        return y + (float) (session.getVertical() * sensitivity);
    }
}
