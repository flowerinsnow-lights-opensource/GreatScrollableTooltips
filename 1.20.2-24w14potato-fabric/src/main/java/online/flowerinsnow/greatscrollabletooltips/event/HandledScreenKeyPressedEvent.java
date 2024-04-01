package online.flowerinsnow.greatscrollabletooltips.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.util.ActionResult;

/**
 * 返回 Success 或 Fail 时取消事件
 */
public interface HandledScreenKeyPressedEvent {
    Event<HandledScreenKeyPressedEvent> EVENT = EventFactory.createArrayBacked(HandledScreenKeyPressedEvent.class, listeners -> (keyCode, scanCode, modifiers) -> {
        for (HandledScreenKeyPressedEvent listener : listeners) {
            ActionResult actionResult = listener.keyPressed(keyCode, scanCode, modifiers);
            if (actionResult != ActionResult.PASS) {
                return actionResult;
            }
        }
        return ActionResult.PASS;
    });

    ActionResult keyPressed(int keyCode, int scanCode, int modifiers);
}
