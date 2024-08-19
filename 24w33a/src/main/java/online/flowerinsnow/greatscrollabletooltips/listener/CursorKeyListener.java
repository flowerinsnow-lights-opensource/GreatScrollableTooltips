package online.flowerinsnow.greatscrollabletooltips.listener;

import net.minecraft.util.ActionResult;
import online.flowerinsnow.greatscrollabletooltips.GreatScrollableTooltips;
import online.flowerinsnow.greatscrollabletooltips.event.HandledScreenKeyPressedEvent;

public class CursorKeyListener implements HandledScreenKeyPressedEvent {
    @Override
    public ActionResult keyPressed(int keyCode, int scanCode, int modifiers) {
        final GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();

        if (instance.isRendering()) {
            if (GreatScrollableTooltips.KEY_BINDING_SCROLL_UP.matchesKey(keyCode, scanCode)) {
                instance.setVertical(instance.getVertical() + 1);
                return ActionResult.SUCCESS;
            }
            if (GreatScrollableTooltips.KEY_BINDING_SCROLL_LEFT.matchesKey(keyCode, scanCode)) {
                instance.setHorizontal(instance.getHorizontal() + 1);
                return ActionResult.SUCCESS;
            }
            if (GreatScrollableTooltips.KEY_BINDING_SCROLL_DOWN.matchesKey(keyCode, scanCode)) {
                instance.setVertical(instance.getVertical() - 1);
                return ActionResult.SUCCESS;
            }
            if (GreatScrollableTooltips.KEY_BINDING_SCROLL_RIGHT.matchesKey(keyCode, scanCode)) {
                instance.setHorizontal(instance.getHorizontal() - 1);
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.PASS;
    }
}
