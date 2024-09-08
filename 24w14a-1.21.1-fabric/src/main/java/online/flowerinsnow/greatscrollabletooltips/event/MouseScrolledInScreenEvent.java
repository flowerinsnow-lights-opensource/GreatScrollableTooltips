package online.flowerinsnow.greatscrollabletooltips.event;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.util.ActionResult;

@Environment(EnvType.CLIENT)
public interface MouseScrolledInScreenEvent {
    Event<MouseScrolledInScreenEvent> EVENT = EventFactory.createArrayBacked(MouseScrolledInScreenEvent.class, listeners -> (parentElement, mouseX, mouseY, horizontalAmount, verticalAmount) -> {
        for (MouseScrolledInScreenEvent listener : listeners) {
            ActionResult actionResult = listener.onMouseScrolled(parentElement, mouseX, mouseY, horizontalAmount, verticalAmount);
            if (actionResult != ActionResult.PASS) {
                return actionResult;
            }
        }
        return ActionResult.PASS;
    });

    ActionResult onMouseScrolled(HandledScreen<?> screen, double mouseX, double mouseY, double horizontalAmount, double verticalAmount);
}
