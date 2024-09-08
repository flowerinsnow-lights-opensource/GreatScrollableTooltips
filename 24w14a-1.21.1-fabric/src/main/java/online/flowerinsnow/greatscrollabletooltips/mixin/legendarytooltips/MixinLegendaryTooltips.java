package online.flowerinsnow.greatscrollabletooltips.mixin.legendarytooltips;

import com.anthonyhilyard.legendarytooltips.LegendaryTooltips;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.ItemStack;
import online.flowerinsnow.greatscrollabletooltips.GreatScrollableTooltips;
import online.flowerinsnow.greatscrollabletooltips.object.ScrollSession;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(LegendaryTooltips.class)
@Environment(EnvType.CLIENT)
public class MixinLegendaryTooltips {
    @ModifyArgs(
            method = "onPostTooltipEvent",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/anthonyhilyard/legendarytooltips/tooltip/TooltipDecor;drawShadow(Lnet/minecraft/client/util/math/MatrixStack;IIII)V",
                    ordinal = 0
            )
    )
    private static void modifyShadow0(Args args) {
        MixinLegendaryTooltips.modifyArgs(args);
    }

    @ModifyArgs(
            method = "onPostTooltipEvent",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/anthonyhilyard/legendarytooltips/tooltip/TooltipDecor;drawShadow(Lnet/minecraft/client/util/math/MatrixStack;IIII)V",
                    ordinal = 1
            )
    )
    private static void modifyShadow1(Args args) {
        MixinLegendaryTooltips.modifyArgs(args);
    }

    @ModifyArgs(
            method = "onPostTooltipEvent",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/anthonyhilyard/legendarytooltips/tooltip/TooltipDecor;drawBorder(Lnet/minecraft/client/util/math/MatrixStack;IIIILnet/minecraft/item/ItemStack;Ljava/util/List;Lnet/minecraft/client/font/TextRenderer;Lcom/anthonyhilyard/legendarytooltips/config/LegendaryTooltipsConfig$FrameDefinition;ZI)V",
                    ordinal = 0
            )
    )
    private static void modifyBorder0(Args args) {
        MixinLegendaryTooltips.modifyArgs(args);
    }

    @ModifyArgs(
            method = "onPostTooltipEvent",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/anthonyhilyard/legendarytooltips/tooltip/TooltipDecor;drawBorder(Lnet/minecraft/client/util/math/MatrixStack;IIIILnet/minecraft/item/ItemStack;Ljava/util/List;Lnet/minecraft/client/font/TextRenderer;Lcom/anthonyhilyard/legendarytooltips/config/LegendaryTooltipsConfig$FrameDefinition;ZI)V",
                    ordinal = 1
            )
    )
    private static void modifyBorder1(Args args) {
        MixinLegendaryTooltips.modifyArgs(args);
    }

    @Unique
    private static void modifyArgs(Args args) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        ScrollSession<ItemStack> session = instance.getScrollSession();
        int sensitivity = instance.getConfig().sensitivity;

        args.set(1, (int) args.get(1) + session.getHorizontal() * sensitivity);
        args.set(2, (int) args.get(2) + session.getVertical() * sensitivity);
    }
}
