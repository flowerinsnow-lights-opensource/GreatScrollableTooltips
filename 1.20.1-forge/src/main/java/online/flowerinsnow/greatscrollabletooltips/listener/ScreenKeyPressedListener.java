package online.flowerinsnow.greatscrollabletooltips.listener;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import online.flowerinsnow.greatscrollabletooltips.GreatScrollableTooltips;
import online.flowerinsnow.greatscrollabletooltips.event.HandledScreenKeyPressedEvent;
import online.flowerinsnow.greatscrollabletooltips.object.ScrollSession;

@OnlyIn(Dist.CLIENT)
public class ScreenKeyPressedListener {
    @SubscribeEvent
    public void onScreenKeyPressed(HandledScreenKeyPressedEvent event) {
        ScrollSession session = GreatScrollableTooltips.getInstance().getScrollSession();
        final GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        int keyCode = event.getKeyCode();
        int scanCode = event.getScanCode();

        if (session.isRendering()) {
            if (GreatScrollableTooltips.KEY_BINDING_SCROLL_UP.get().matches(keyCode, scanCode)) {
                session.addVertical(1);
                event.setCanceled(true);
            }
            if (GreatScrollableTooltips.KEY_BINDING_SCROLL_LEFT.get().matches(keyCode, scanCode)) {
                session.addHorizontal(1);
                event.setCanceled(true);
            }
            if (GreatScrollableTooltips.KEY_BINDING_SCROLL_DOWN.get().matches(keyCode, scanCode)) {
                session.addVertical(-1);
                event.setCanceled(true);
            }
            if (GreatScrollableTooltips.KEY_BINDING_SCROLL_RIGHT.get().matches(keyCode, scanCode)) {
                session.addHorizontal(-1);
                event.setCanceled(true);
            }
        }
    }
}
