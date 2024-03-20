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
                    target = "Lnet/minecraft/client/gui/screen/Screen;fillGradient(Lnet/minecraft/util/math/Matrix4f;Lnet/minecraft/client/render/BufferBuilder;IIIIIII)V",
                    ordinal = 0
            ),
            index = 2
    )
    private int modifyBackgroundX0(int x) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        return x + (instance.getHorizontal() * instance.getConfig().sensitivity);
    }

    @ModifyArg(
            method = "renderTooltipFromComponents",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screen/Screen;fillGradient(Lnet/minecraft/util/math/Matrix4f;Lnet/minecraft/client/render/BufferBuilder;IIIIIII)V",
                    ordinal = 1
            ),
            index = 2
    )
    private int modifyBackgroundX1(int x) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        return x + (instance.getHorizontal() * instance.getConfig().sensitivity);
    }

    @ModifyArg(
            method = "renderTooltipFromComponents",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screen/Screen;fillGradient(Lnet/minecraft/util/math/Matrix4f;Lnet/minecraft/client/render/BufferBuilder;IIIIIII)V",
                    ordinal = 2
            ),
            index = 2
    )
    private int modifyBackgroundX2(int x) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        return x + (instance.getHorizontal() * instance.getConfig().sensitivity);
    }

    @ModifyArg(
            method = "renderTooltipFromComponents",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screen/Screen;fillGradient(Lnet/minecraft/util/math/Matrix4f;Lnet/minecraft/client/render/BufferBuilder;IIIIIII)V",
                    ordinal = 3
            ),
            index = 2
    )
    private int modifyBackgroundX3(int x) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        return x + (instance.getHorizontal() * instance.getConfig().sensitivity);
    }

    @ModifyArg(
            method = "renderTooltipFromComponents",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screen/Screen;fillGradient(Lnet/minecraft/util/math/Matrix4f;Lnet/minecraft/client/render/BufferBuilder;IIIIIII)V",
                    ordinal = 4
            ),
            index = 2
    )
    private int modifyBackgroundX4(int x) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        return x + (instance.getHorizontal() * instance.getConfig().sensitivity);
    }

    @ModifyArg(
            method = "renderTooltipFromComponents",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screen/Screen;fillGradient(Lnet/minecraft/util/math/Matrix4f;Lnet/minecraft/client/render/BufferBuilder;IIIIIII)V",
                    ordinal = 5
            ),
            index = 2
    )
    private int modifyBackgroundX5(int x) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        return x + (instance.getHorizontal() * instance.getConfig().sensitivity);
    }

    @ModifyArg(
            method = "renderTooltipFromComponents",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screen/Screen;fillGradient(Lnet/minecraft/util/math/Matrix4f;Lnet/minecraft/client/render/BufferBuilder;IIIIIII)V",
                    ordinal = 6
            ),
            index = 2
    )
    private int modifyBackgroundX6(int x) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        return x + (instance.getHorizontal() * instance.getConfig().sensitivity);
    }

    @ModifyArg(
            method = "renderTooltipFromComponents",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screen/Screen;fillGradient(Lnet/minecraft/util/math/Matrix4f;Lnet/minecraft/client/render/BufferBuilder;IIIIIII)V",
                    ordinal = 7
            ),
            index = 2
    )
    private int modifyBackgroundX7(int x) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        return x + (instance.getHorizontal() * instance.getConfig().sensitivity);
    }

    @ModifyArg(
            method = "renderTooltipFromComponents",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screen/Screen;fillGradient(Lnet/minecraft/util/math/Matrix4f;Lnet/minecraft/client/render/BufferBuilder;IIIIIII)V",
                    ordinal = 8
            ),
            index = 2
    )
    private int modifyBackgroundX8(int x) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        return x + (instance.getHorizontal() * instance.getConfig().sensitivity);
    }
    
    @ModifyArg(
            method = "renderTooltipFromComponents",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screen/Screen;fillGradient(Lnet/minecraft/util/math/Matrix4f;Lnet/minecraft/client/render/BufferBuilder;IIIIIII)V",
                    ordinal = 0
            ),
            index = 4
    )
    private int modifyBackgroundEndX0(int x) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        return x + (instance.getHorizontal() * instance.getConfig().sensitivity);
    }

    @ModifyArg(
            method = "renderTooltipFromComponents",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screen/Screen;fillGradient(Lnet/minecraft/util/math/Matrix4f;Lnet/minecraft/client/render/BufferBuilder;IIIIIII)V",
                    ordinal = 1
            ),
            index = 4
    )
    private int modifyBackgroundEndX1(int x) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        return x + (instance.getHorizontal() * instance.getConfig().sensitivity);
    }

    @ModifyArg(
            method = "renderTooltipFromComponents",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screen/Screen;fillGradient(Lnet/minecraft/util/math/Matrix4f;Lnet/minecraft/client/render/BufferBuilder;IIIIIII)V",
                    ordinal = 2
            ),
            index = 4
    )
    private int modifyBackgroundEndX2(int x) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        return x + (instance.getHorizontal() * instance.getConfig().sensitivity);
    }

    @ModifyArg(
            method = "renderTooltipFromComponents",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screen/Screen;fillGradient(Lnet/minecraft/util/math/Matrix4f;Lnet/minecraft/client/render/BufferBuilder;IIIIIII)V",
                    ordinal = 3
            ),
            index = 4
    )
    private int modifyBackgroundEndX3(int x) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        return x + (instance.getHorizontal() * instance.getConfig().sensitivity);
    }

    @ModifyArg(
            method = "renderTooltipFromComponents",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screen/Screen;fillGradient(Lnet/minecraft/util/math/Matrix4f;Lnet/minecraft/client/render/BufferBuilder;IIIIIII)V",
                    ordinal = 4
            ),
            index = 4
    )
    private int modifyBackgroundEndX4(int x) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        return x + (instance.getHorizontal() * instance.getConfig().sensitivity);
    }

    @ModifyArg(
            method = "renderTooltipFromComponents",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screen/Screen;fillGradient(Lnet/minecraft/util/math/Matrix4f;Lnet/minecraft/client/render/BufferBuilder;IIIIIII)V",
                    ordinal = 5
            ),
            index = 4
    )
    private int modifyBackgroundEndX5(int x) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        return x + (instance.getHorizontal() * instance.getConfig().sensitivity);
    }

    @ModifyArg(
            method = "renderTooltipFromComponents",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screen/Screen;fillGradient(Lnet/minecraft/util/math/Matrix4f;Lnet/minecraft/client/render/BufferBuilder;IIIIIII)V",
                    ordinal = 6
            ),
            index = 4
    )
    private int modifyBackgroundEndX6(int x) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        return x + (instance.getHorizontal() * instance.getConfig().sensitivity);
    }

    @ModifyArg(
            method = "renderTooltipFromComponents",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screen/Screen;fillGradient(Lnet/minecraft/util/math/Matrix4f;Lnet/minecraft/client/render/BufferBuilder;IIIIIII)V",
                    ordinal = 7
            ),
            index = 4
    )
    private int modifyBackgroundEndX7(int x) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        return x + (instance.getHorizontal() * instance.getConfig().sensitivity);
    }

    @ModifyArg(
            method = "renderTooltipFromComponents",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screen/Screen;fillGradient(Lnet/minecraft/util/math/Matrix4f;Lnet/minecraft/client/render/BufferBuilder;IIIIIII)V",
                    ordinal = 8
            ),
            index = 4
    )
    private int modifyBackgroundEndX8(int x) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        return x + (instance.getHorizontal() * instance.getConfig().sensitivity);
    }
    
    @ModifyArg(
            method = "renderTooltipFromComponents",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screen/Screen;fillGradient(Lnet/minecraft/util/math/Matrix4f;Lnet/minecraft/client/render/BufferBuilder;IIIIIII)V",
                    ordinal = 0
            ),
            index = 3
    )
    private int modifyBackgroundY0(int y) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        return y + (instance.getVertical() * instance.getConfig().sensitivity);
    }

    @ModifyArg(
            method = "renderTooltipFromComponents",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screen/Screen;fillGradient(Lnet/minecraft/util/math/Matrix4f;Lnet/minecraft/client/render/BufferBuilder;IIIIIII)V",
                    ordinal = 1
            ),
            index = 3
    )
    private int modifyBackgroundY1(int y) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        return y + (instance.getVertical() * instance.getConfig().sensitivity);
    }

    @ModifyArg(
            method = "renderTooltipFromComponents",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screen/Screen;fillGradient(Lnet/minecraft/util/math/Matrix4f;Lnet/minecraft/client/render/BufferBuilder;IIIIIII)V",
                    ordinal = 2
            ),
            index = 3
    )
    private int modifyBackgroundY2(int y) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        return y + (instance.getVertical() * instance.getConfig().sensitivity);
    }

    @ModifyArg(
            method = "renderTooltipFromComponents",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screen/Screen;fillGradient(Lnet/minecraft/util/math/Matrix4f;Lnet/minecraft/client/render/BufferBuilder;IIIIIII)V",
                    ordinal = 3
            ),
            index = 3
    )
    private int modifyBackgroundY3(int y) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        return y + (instance.getVertical() * instance.getConfig().sensitivity);
    }

    @ModifyArg(
            method = "renderTooltipFromComponents",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screen/Screen;fillGradient(Lnet/minecraft/util/math/Matrix4f;Lnet/minecraft/client/render/BufferBuilder;IIIIIII)V",
                    ordinal = 4
            ),
            index = 3
    )
    private int modifyBackgroundY4(int y) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        return y + (instance.getVertical() * instance.getConfig().sensitivity);
    }

    @ModifyArg(
            method = "renderTooltipFromComponents",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screen/Screen;fillGradient(Lnet/minecraft/util/math/Matrix4f;Lnet/minecraft/client/render/BufferBuilder;IIIIIII)V",
                    ordinal = 5
            ),
            index = 3
    )
    private int modifyBackgroundY5(int y) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        return y + (instance.getVertical() * instance.getConfig().sensitivity);
    }

    @ModifyArg(
            method = "renderTooltipFromComponents",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screen/Screen;fillGradient(Lnet/minecraft/util/math/Matrix4f;Lnet/minecraft/client/render/BufferBuilder;IIIIIII)V",
                    ordinal = 6
            ),
            index = 3
    )
    private int modifyBackgroundY6(int y) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        return y + (instance.getVertical() * instance.getConfig().sensitivity);
    }

    @ModifyArg(
            method = "renderTooltipFromComponents",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screen/Screen;fillGradient(Lnet/minecraft/util/math/Matrix4f;Lnet/minecraft/client/render/BufferBuilder;IIIIIII)V",
                    ordinal = 7
            ),
            index = 3
    )
    private int modifyBackgroundY7(int y) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        return y + (instance.getVertical() * instance.getConfig().sensitivity);
    }

    @ModifyArg(
            method = "renderTooltipFromComponents",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screen/Screen;fillGradient(Lnet/minecraft/util/math/Matrix4f;Lnet/minecraft/client/render/BufferBuilder;IIIIIII)V",
                    ordinal = 8
            ),
            index = 3
    )
    private int modifyBackgroundY8(int y) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        return y + (instance.getVertical() * instance.getConfig().sensitivity);
    }

    @ModifyArg(
            method = "renderTooltipFromComponents",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screen/Screen;fillGradient(Lnet/minecraft/util/math/Matrix4f;Lnet/minecraft/client/render/BufferBuilder;IIIIIII)V",
                    ordinal = 0
            ),
            index = 5
    )
    private int modifyBackgroundEndY0(int y) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        return y + (instance.getVertical() * instance.getConfig().sensitivity);
    }

    @ModifyArg(
            method = "renderTooltipFromComponents",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screen/Screen;fillGradient(Lnet/minecraft/util/math/Matrix4f;Lnet/minecraft/client/render/BufferBuilder;IIIIIII)V",
                    ordinal = 1
            ),
            index = 5
    )
    private int modifyBackgroundEndY1(int y) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        return y + (instance.getVertical() * instance.getConfig().sensitivity);
    }

    @ModifyArg(
            method = "renderTooltipFromComponents",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screen/Screen;fillGradient(Lnet/minecraft/util/math/Matrix4f;Lnet/minecraft/client/render/BufferBuilder;IIIIIII)V",
                    ordinal = 2
            ),
            index = 5
    )
    private int modifyBackgroundEndY2(int y) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        return y + (instance.getVertical() * instance.getConfig().sensitivity);
    }

    @ModifyArg(
            method = "renderTooltipFromComponents",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screen/Screen;fillGradient(Lnet/minecraft/util/math/Matrix4f;Lnet/minecraft/client/render/BufferBuilder;IIIIIII)V",
                    ordinal = 3
            ),
            index = 5
    )
    private int modifyBackgroundEndY3(int y) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        return y + (instance.getVertical() * instance.getConfig().sensitivity);
    }

    @ModifyArg(
            method = "renderTooltipFromComponents",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screen/Screen;fillGradient(Lnet/minecraft/util/math/Matrix4f;Lnet/minecraft/client/render/BufferBuilder;IIIIIII)V",
                    ordinal = 4
            ),
            index = 5
    )
    private int modifyBackgroundEndY4(int y) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        return y + (instance.getVertical() * instance.getConfig().sensitivity);
    }

    @ModifyArg(
            method = "renderTooltipFromComponents",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screen/Screen;fillGradient(Lnet/minecraft/util/math/Matrix4f;Lnet/minecraft/client/render/BufferBuilder;IIIIIII)V",
                    ordinal = 5
            ),
            index = 5
    )
    private int modifyBackgroundEndY5(int y) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        return y + (instance.getVertical() * instance.getConfig().sensitivity);
    }

    @ModifyArg(
            method = "renderTooltipFromComponents",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screen/Screen;fillGradient(Lnet/minecraft/util/math/Matrix4f;Lnet/minecraft/client/render/BufferBuilder;IIIIIII)V",
                    ordinal = 6
            ),
            index = 5
    )
    private int modifyBackgroundEndY6(int y) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        return y + (instance.getVertical() * instance.getConfig().sensitivity);
    }

    @ModifyArg(
            method = "renderTooltipFromComponents",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screen/Screen;fillGradient(Lnet/minecraft/util/math/Matrix4f;Lnet/minecraft/client/render/BufferBuilder;IIIIIII)V",
                    ordinal = 7
            ),
            index = 5
    )
    private int modifyBackgroundEndY7(int y) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        return y + (instance.getVertical() * instance.getConfig().sensitivity);
    }

    @ModifyArg(
            method = "renderTooltipFromComponents",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screen/Screen;fillGradient(Lnet/minecraft/util/math/Matrix4f;Lnet/minecraft/client/render/BufferBuilder;IIIIIII)V",
                    ordinal = 8
            ),
            index = 5
    )
    private int modifyBackgroundEndY8(int y) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        return y + (instance.getVertical() * instance.getConfig().sensitivity);
    }

    @ModifyArg(
            method = "renderTooltipFromComponents",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/tooltip/TooltipComponent;drawText(Lnet/minecraft/client/font/TextRenderer;IILnet/minecraft/util/math/Matrix4f;Lnet/minecraft/client/render/VertexConsumerProvider$Immediate;)V",
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
                    target = "Lnet/minecraft/client/gui/tooltip/TooltipComponent;drawText(Lnet/minecraft/client/font/TextRenderer;IILnet/minecraft/util/math/Matrix4f;Lnet/minecraft/client/render/VertexConsumerProvider$Immediate;)V",
                    ordinal = 0
            ),
            index = 2
    )
    public int modifyTextY(int y) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        return y + (instance.getVertical() * instance.getConfig().sensitivity);
    }
}
