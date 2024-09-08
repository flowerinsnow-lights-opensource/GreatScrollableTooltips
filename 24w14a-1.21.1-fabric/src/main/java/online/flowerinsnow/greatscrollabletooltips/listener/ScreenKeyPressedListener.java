package online.flowerinsnow.greatscrollabletooltips.listener;

import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import online.flowerinsnow.greatscrollabletooltips.GreatScrollableTooltips;
import online.flowerinsnow.greatscrollabletooltips.event.HandledScreenKeyPressedEvent;
import online.flowerinsnow.greatscrollabletooltips.object.ScrollSession;

public class ScreenKeyPressedListener implements HandledScreenKeyPressedEvent {
    @Override
    public ActionResult keyPressed(HandledScreen<?> screen, int keyCode, int scanCode, int modifiers) {
        final GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        ScrollSession<ItemStack> session = instance.getScrollSession();

        if (session.isRendering()) {
            if (GreatScrollableTooltips.KEY_BINDING_SCROLL_UP.matchesKey(keyCode, scanCode)) {
                session.addVertical(1);
                return ActionResult.FAIL;
            }
            if (GreatScrollableTooltips.KEY_BINDING_SCROLL_LEFT.matchesKey(keyCode, scanCode)) {
                session.addHorizontal(1);
                return ActionResult.FAIL;
            }
            if (GreatScrollableTooltips.KEY_BINDING_SCROLL_DOWN.matchesKey(keyCode, scanCode)) {
                session.addVertical(-1);
                return ActionResult.FAIL;
            }
            if (GreatScrollableTooltips.KEY_BINDING_SCROLL_RIGHT.matchesKey(keyCode, scanCode)) {
                session.addHorizontal(-1);
                return ActionResult.FAIL;
            }
        }
        return ActionResult.PASS;
    }
}
