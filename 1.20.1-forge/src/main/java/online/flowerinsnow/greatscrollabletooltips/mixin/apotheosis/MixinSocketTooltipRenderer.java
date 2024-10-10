package online.flowerinsnow.greatscrollabletooltips.mixin.apotheosis;

import dev.shadowsoffire.apotheosis.adventure.client.SocketTooltipRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import online.flowerinsnow.greatscrollabletooltips.GreatScrollableTooltips;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(SocketTooltipRenderer.class)
@OnlyIn(Dist.CLIENT)
public class MixinSocketTooltipRenderer {
    /*
    @ModifyVariable(
            method = "renderImage",
            at = @At(
                    value = "HEAD",
                    ordinal = 0
            ),
            index = 2,
            argsOnly = true
    )
    public int modifyXX(int x) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        return x + instance.getScrollSession().getHorizontal() * instance.getConfig().sensitivity;
    }
    @ModifyVariable(
            method = "renderImage",
            at = @At(
                    value = "HEAD",
                    ordinal = 0
            ),
            index = 2,
            argsOnly = true
    )

    public int modifyYY(int y) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        return y + instance.getScrollSession().getHorizontal() * instance.getConfig().sensitivity;
    }
     */

    @ModifyArg(
            method = "renderImage",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Lnet/minecraft/resources/ResourceLocation;IIIFFIIII)V",
                    ordinal = 0
            ),
            index = 1
    )
    public int modifyBlitX(int x) {
        return MixinSocketTooltipRenderer.modifyX(x);
    }
    @ModifyArg(
            method = "renderImage",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Lnet/minecraft/resources/ResourceLocation;IIIFFIIII)V",
                    ordinal = 0
            ),
            index = 2
    )
    public int modifyBlitY(int y) {
        return MixinSocketTooltipRenderer.modifyY(y);
    }

    @ModifyArg(
            method = "renderImage",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphics;renderFakeItem(Lnet/minecraft/world/item/ItemStack;II)V",
                    ordinal = 0
            ),
            index = 1
    )
    public int modifyRenderFakeItemX(int x) {
        return MixinSocketTooltipRenderer.modifyX(x);
    }

    @ModifyArg(
            method = "renderImage",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphics;renderFakeItem(Lnet/minecraft/world/item/ItemStack;II)V",
                    ordinal = 0
            ),
            index = 2
    )
    public int modifyRenderFakeItemY(int y) {
        return MixinSocketTooltipRenderer.modifyX(y);
    }

    @Unique
    private static int modifyX(int x) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        return x + instance.getScrollSession().getHorizontal() * instance.getConfig().sensitivity;
    }

    @Unique
    private static int modifyY(int y) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        return y + instance.getScrollSession().getVertical() * instance.getConfig().sensitivity;
    }
}
