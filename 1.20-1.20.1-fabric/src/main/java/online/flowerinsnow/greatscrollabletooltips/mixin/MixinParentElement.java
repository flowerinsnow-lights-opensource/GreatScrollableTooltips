package online.flowerinsnow.greatscrollabletooltips.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.AbstractParentElement;
import net.minecraft.client.gui.ParentElement;
import net.minecraft.util.ActionResult;
import online.flowerinsnow.greatscrollabletooltips.event.MouseScrolledInParentElementEvent;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(AbstractParentElement.class)
@Environment(EnvType.CLIENT)
public abstract class MixinParentElement implements ParentElement {
    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        ActionResult actionResult = MouseScrolledInParentElementEvent.EVENT.invoker()
                .onMouseScrolled(this, mouseX, mouseY, amount);
        if (actionResult == ActionResult.FAIL) {
            return false;
        }
        return ParentElement.super.mouseScrolled(mouseX, mouseY, amount);
    }
}
