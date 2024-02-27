package online.flowerinsnow.greatscrollabletooltips.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import online.flowerinsnow.greatscrollabletooltips.GreatScrollableTooltips;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(Screen.class)
@Environment(EnvType.CLIENT)
public class MixinScreen {
    @ModifyVariable(
            method = "renderTooltipFromComponents",
            at = @At(
                    value = "LOAD",
                    opcode = Opcodes.ILOAD,
                    ordinal = 0
            ),
            ordinal = 7
    )
    private int modifyY(int value) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        return value + instance.getVertical() * instance.getConfig().sensitivity;
    }
}
