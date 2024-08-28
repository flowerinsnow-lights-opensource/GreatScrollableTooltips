package online.flowerinsnow.greatscrollabletooltips.mixin.legendarytooltips;

import com.anthonyhilyard.legendarytooltips.LegendaryTooltips;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import online.flowerinsnow.greatscrollabletooltips.GreatScrollableTooltips;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(LegendaryTooltips.class)
@OnlyIn(Dist.CLIENT)
public class MixinLegendaryTooltips {
    @ModifyArg(
            method = "onPostTooltipEvent",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/anthonyhilyard/legendarytooltips/tooltip/TooltipDecor;drawShadow(Lcom/mojang/blaze3d/vertex/PoseStack;IIII)V",
                    ordinal = 0
            ),
            remap = false,
            index = 1
    )
    private static int modifyDrawShadowX0(int x) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        return x + instance.getScrollSession().getHorizontal() * instance.getConfig().sensitivity;
    }

    @ModifyArg(
            method = "onPostTooltipEvent",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/anthonyhilyard/legendarytooltips/tooltip/TooltipDecor;drawShadow(Lcom/mojang/blaze3d/vertex/PoseStack;IIII)V",
                    ordinal = 0
            ),
            remap = false,
            index = 2
    )
    private static int modifyDrawShadowY0(int y) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        return y + instance.getScrollSession().getVertical() * instance.getConfig().sensitivity;
    }

    @ModifyArg(
            method = "onPostTooltipEvent",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/anthonyhilyard/legendarytooltips/tooltip/TooltipDecor;drawShadow(Lcom/mojang/blaze3d/vertex/PoseStack;IIII)V",
                    ordinal = 1
            ),
            remap = false,
            index = 1
    )
    private static int modifyDrawShadowX1(int x) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        return x + instance.getScrollSession().getHorizontal() * instance.getConfig().sensitivity;
    }

    @ModifyArg(
            method = "onPostTooltipEvent",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/anthonyhilyard/legendarytooltips/tooltip/TooltipDecor;drawShadow(Lcom/mojang/blaze3d/vertex/PoseStack;IIII)V",
                    ordinal = 1
            ),
            remap = false,
            index = 2
    )
    private static int modifyDrawShadowY1(int y) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        return y + instance.getScrollSession().getVertical() * instance.getConfig().sensitivity;
    }

    @ModifyArg(
            method = "onPostTooltipEvent",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/anthonyhilyard/legendarytooltips/tooltip/TooltipDecor;drawBorder(Lcom/mojang/blaze3d/vertex/PoseStack;IIIILnet/minecraft/world/item/ItemStack;Ljava/util/List;Lnet/minecraft/client/gui/Font;Lcom/anthonyhilyard/legendarytooltips/config/LegendaryTooltipsConfig$FrameDefinition;ZI)V",
                    ordinal = 0
            ),
            remap = false,
            index = 1
    )
    private static int modifyDrawBorderX0(int x) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        return x + instance.getScrollSession().getHorizontal() * instance.getConfig().sensitivity;
    }

    @ModifyArg(
            method = "onPostTooltipEvent",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/anthonyhilyard/legendarytooltips/tooltip/TooltipDecor;drawBorder(Lcom/mojang/blaze3d/vertex/PoseStack;IIIILnet/minecraft/world/item/ItemStack;Ljava/util/List;Lnet/minecraft/client/gui/Font;Lcom/anthonyhilyard/legendarytooltips/config/LegendaryTooltipsConfig$FrameDefinition;ZI)V",
                    ordinal = 0
            ),
            remap = false,
            index = 2
    )
    private static int modifyDrawBorderY0(int y) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        return y + instance.getScrollSession().getVertical() * instance.getConfig().sensitivity;
    }

    @ModifyArg(
            method = "onPostTooltipEvent",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/anthonyhilyard/legendarytooltips/tooltip/TooltipDecor;drawBorder(Lcom/mojang/blaze3d/vertex/PoseStack;IIIILnet/minecraft/world/item/ItemStack;Ljava/util/List;Lnet/minecraft/client/gui/Font;Lcom/anthonyhilyard/legendarytooltips/config/LegendaryTooltipsConfig$FrameDefinition;ZI)V",
                    ordinal = 1
            ),
            remap = false,
            index = 1
    )
    private static int modifyDrawBorderX1(int x) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        return x + instance.getScrollSession().getHorizontal() * instance.getConfig().sensitivity;
    }

    @ModifyArg(
            method = "onPostTooltipEvent",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/anthonyhilyard/legendarytooltips/tooltip/TooltipDecor;drawBorder(Lcom/mojang/blaze3d/vertex/PoseStack;IIIILnet/minecraft/world/item/ItemStack;Ljava/util/List;Lnet/minecraft/client/gui/Font;Lcom/anthonyhilyard/legendarytooltips/config/LegendaryTooltipsConfig$FrameDefinition;ZI)V",
                    ordinal = 1
            ),
            remap = false,
            index = 2
    )
    private static int modifyDrawBorderY1(int y) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        return y + instance.getScrollSession().getVertical() * instance.getConfig().sensitivity;
    }
}
