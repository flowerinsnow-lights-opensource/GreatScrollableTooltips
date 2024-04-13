package online.flowerinsnow.greatscrollabletooltips.event;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.gui.ParentElement;
import net.minecraft.util.ActionResult;

@Environment(EnvType.CLIENT)
public interface MouseScrolledInParentElementEvent {
    Event<MouseScrolledInParentElementEvent> EVENT = EventFactory.createArrayBacked(MouseScrolledInParentElementEvent.class, listeners -> (parentElement, mouseX, mouseY, horizontalAmount, verticalAmount) -> {
        for (MouseScrolledInParentElementEvent listener : listeners) {
            ActionResult actionResult = listener.onMouseScrolled(parentElement, mouseX, mouseY, horizontalAmount, verticalAmount);
            if (actionResult != ActionResult.PASS) {
                return actionResult;
            }
        }
        return ActionResult.PASS;
    });

    ActionResult onMouseScrolled(ParentElement parentElement, double mouseX, double mouseY, double horizontalAmount, double verticalAmount);
}
