package online.flowerinsnow.greatscrollabletooltips.event;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.util.ActionResult;

/**
 * 返回 FAIL 时取消事件
 */
@Environment(EnvType.CLIENT)
public interface HandledScreenKeyPressedEvent {
    Event<HandledScreenKeyPressedEvent> EVENT = EventFactory.createArrayBacked(HandledScreenKeyPressedEvent.class, listeners -> (screen, keyCode, scanCode, modifiers) -> {
        for (HandledScreenKeyPressedEvent listener : listeners) {
            ActionResult actionResult = listener.keyPressed(screen, keyCode, scanCode, modifiers);
            if (actionResult != ActionResult.PASS) {
                return actionResult;
            }
        }
        return ActionResult.PASS;
    });

    ActionResult keyPressed(HandledScreen<?> screen, int keyCode, int scanCode, int modifiers);
}
