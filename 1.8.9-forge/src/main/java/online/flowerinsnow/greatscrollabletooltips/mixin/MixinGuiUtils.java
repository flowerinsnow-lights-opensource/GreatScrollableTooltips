package online.flowerinsnow.greatscrollabletooltips.mixin;

import net.minecraft.client.gui.FontRenderer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.config.GuiUtils;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import online.flowerinsnow.greatscrollabletooltips.GreatScrollableTooltips;
import online.flowerinsnow.greatscrollabletooltips.event.DrawHoveringTextEvent;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(GuiUtils.class)
@SideOnly(Side.CLIENT)
public class MixinGuiUtils {
    @Inject(
            method = "drawHoveringText",
            at = @At("HEAD"),
            cancellable = true,
            remap = false
    )
    private static void preDrawHoveringText(List<String> textLines, int mouseX, int mouseY, int screenWidth, int screenHeight, int maxTextWidth, FontRenderer font, CallbackInfo ci) {
        if (MinecraftForge.EVENT_BUS.post(new DrawHoveringTextEvent.Pre(textLines, mouseX, mouseY, screenWidth, screenHeight, maxTextWidth, font))) {
            ci.cancel();
        }
    }

    @Inject(
            method = "drawHoveringText",
            at = @At("RETURN"),
            remap = false
    )
    private static void postDrawHoveringText(List<String> textLines, int mouseX, int mouseY, int screenWidth, int screenHeight, int maxTextWidth, FontRenderer font, CallbackInfo ci) {
        MinecraftForge.EVENT_BUS.post(new DrawHoveringTextEvent.Post(textLines, mouseX, mouseY, screenWidth, screenHeight, maxTextWidth, font));
    }

    @ModifyVariable(
            method = "drawHoveringText",
            at = @At(
                    value = "LOAD",
                    opcode = Opcodes.ILOAD,
                    ordinal = 3
            ),
            index = 11,
            remap = false
    )
    private static int modifyTooltipY(int value) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        return value + instance.getVertical() * instance.getConfig().sensitivity;
    }
}
