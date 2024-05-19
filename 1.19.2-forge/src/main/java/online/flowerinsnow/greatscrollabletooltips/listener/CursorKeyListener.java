package online.flowerinsnow.greatscrollabletooltips.listener;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import online.flowerinsnow.greatscrollabletooltips.GreatScrollableTooltips;
import online.flowerinsnow.greatscrollabletooltips.event.HandledScreenKeyPressedEvent;

@OnlyIn(Dist.CLIENT)
public class CursorKeyListener {
    @SubscribeEvent
    public void onScreenKeyPressed(HandledScreenKeyPressedEvent event) {
        final GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        int keyCode = event.getKeyCode();
        int scanCode = event.getScanCode();

        if (instance.isRendering()) {
            if (GreatScrollableTooltips.KEY_BINDING_SCROLL_UP.get().matches(keyCode, scanCode)) {
                instance.setVertical(instance.getVertical() + 1);
                event.setCanceled(true);
            }
            if (GreatScrollableTooltips.KEY_BINDING_SCROLL_LEFT.get().matches(keyCode, scanCode)) {
                instance.setHorizontal(instance.getHorizontal() + 1);
                event.setCanceled(true);
            }
            if (GreatScrollableTooltips.KEY_BINDING_SCROLL_DOWN.get().matches(keyCode, scanCode)) {
                instance.setVertical(instance.getVertical() - 1);
                event.setCanceled(true);
            }
            if (GreatScrollableTooltips.KEY_BINDING_SCROLL_RIGHT.get().matches(keyCode, scanCode)) {
                instance.setHorizontal(instance.getHorizontal() - 1);
                event.setCanceled(true);
            }
        }
    }
}
