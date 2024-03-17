package online.flowerinsnow.greatscrollabletooltips.listener;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import online.flowerinsnow.greatscrollabletooltips.GreatScrollableTooltips;
import online.flowerinsnow.greatscrollabletooltips.event.GuiContainerKeyTypedEvent;

@SideOnly(Side.CLIENT)
public class CursorKeyListener {
    @SubscribeEvent
    public void preGuiContainerKeyTyped(GuiContainerKeyTypedEvent.Pre event) {
        final GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();

        if (GreatScrollableTooltips.KEY_BINDING_SCROLL_UP.getKeyCode() == event.keyCode) {
            instance.setVertical(instance.getVertical() + 1);
            event.setCanceled(true);
        }
        if (GreatScrollableTooltips.KEY_BINDING_SCROLL_LEFT.getKeyCode() == event.keyCode) {
            instance.setHorizontal(instance.getHorizontal() + 1);
            event.setCanceled(true);
        }
        if (GreatScrollableTooltips.KEY_BINDING_SCROLL_DOWN.getKeyCode() == event.keyCode) {
            instance.setVertical(instance.getVertical() - 1);
            event.setCanceled(true);
        }
        if (GreatScrollableTooltips.KEY_BINDING_SCROLL_RIGHT.getKeyCode() == event.keyCode) {
            instance.setHorizontal(instance.getHorizontal() - 1);
            event.setCanceled(true);
        }
    }
}
