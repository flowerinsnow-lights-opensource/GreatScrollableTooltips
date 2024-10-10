package online.flowerinsnow.greatscrollabletooltips.listener;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import online.flowerinsnow.greatscrollabletooltips.GreatScrollableTooltips;
import online.flowerinsnow.greatscrollabletooltips.event.ScreenKeyPressedEvent;
import online.flowerinsnow.greatscrollabletooltips.manager.KeyBindingManager;
import online.flowerinsnow.greatscrollabletooltips.common.object.ScrollSession;

import java.util.Objects;

/**
 * 使用按键滚动物品
 */
@SideOnly(Side.CLIENT)
public class KeyScrollListener {
    public final GreatScrollableTooltips main;

    public KeyScrollListener(GreatScrollableTooltips main) {
        this.main = Objects.requireNonNull(main);
    }

    @SubscribeEvent
    public void onScreenKeyPress(ScreenKeyPressedEvent.Pre event) {
        ScrollSession<ItemStack> session = main.getScrollSession();

        if (session.isRendering()) {
            if (KeyBindingManager.KEY_BINDING_SCROLL_UP.get().isActiveAndMatches(event.keyCode)) {
                session.addVertical(1);
            } else if (KeyBindingManager.KEY_BINDING_SCROLL_LEFT.get().isActiveAndMatches(event.keyCode)) {
                session.addHorizontal(1);
            } else if (KeyBindingManager.KEY_BINDING_SCROLL_DOWN.get().isActiveAndMatches(event.keyCode)) {
                session.addVertical(-1);
            } else if (KeyBindingManager.KEY_BINDING_SCROLL_RIGHT.get().isActiveAndMatches(event.keyCode)) {
                session.addHorizontal(-1);
            }
        }
    }
}
