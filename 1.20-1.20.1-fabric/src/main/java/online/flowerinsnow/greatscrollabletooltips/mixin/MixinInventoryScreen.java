package online.flowerinsnow.greatscrollabletooltips.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.ParentElement;
import net.minecraft.client.gui.screen.ingame.AbstractInventoryScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import online.flowerinsnow.greatscrollabletooltips.event.MouseScrolledInParentElementEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;

@Mixin(InventoryScreen.class)
@Environment(EnvType.CLIENT)
public abstract class MixinInventoryScreen extends AbstractInventoryScreen<PlayerScreenHandler> {
    public MixinInventoryScreen() {
        //noinspection DataFlowIssue
        super(null, null, null);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        ActionResult actionResult = MouseScrolledInParentElementEvent.EVENT.invoker()
                .onMouseScrolled(this, mouseX, mouseY, amount);
        if (actionResult == ActionResult.FAIL) {
            return false;
        }
        return super.mouseScrolled(mouseX, mouseY, amount);
    }
}
