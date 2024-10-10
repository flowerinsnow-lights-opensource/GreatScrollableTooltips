package online.flowerinsnow.greatscrollabletooltips.listener;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import online.flowerinsnow.greatscrollabletooltips.GreatScrollableTooltips;
import online.flowerinsnow.greatscrollabletooltips.manager.KeyBindingManager;
import online.flowerinsnow.greatscrollabletooltips.event.PreScreenKeyPressedEvent;
import online.flowerinsnow.greatscrollabletooltips.object.ScrollSession;

@OnlyIn(Dist.CLIENT)
public class KeyScrollListener {
    @SubscribeEvent
    public void onScreenKeyPressed(PreScreenKeyPressedEvent event) {
        ScrollSession<ItemStack> session = GreatScrollableTooltips.getInstance().getScrollSession();
        int keyCode = event.getKeyCode();
        int scanCode = event.getScanCode();

        if (session.isRendering()) {
            if (KeyBindingManager.KEY_BINDING_SCROLL_UP.get().matches(keyCode, scanCode)) {
                session.addVertical(1);
            }
            if (KeyBindingManager.KEY_BINDING_SCROLL_LEFT.get().matches(keyCode, scanCode)) {
                session.addHorizontal(1);
            }
            if (KeyBindingManager.KEY_BINDING_SCROLL_DOWN.get().matches(keyCode, scanCode)) {
                session.addVertical(-1);
            }
            if (KeyBindingManager.KEY_BINDING_SCROLL_RIGHT.get().matches(keyCode, scanCode)) {
                session.addHorizontal(-1);
            }
        }
    }
}
