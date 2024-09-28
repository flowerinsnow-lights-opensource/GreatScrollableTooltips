package online.flowerinsnow.greatscrollabletooltips.listener;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import online.flowerinsnow.greatscrollabletooltips.GreatScrollableTooltips;
import online.flowerinsnow.greatscrollabletooltips.config.GreatScrollableTooltipsConfig;
import online.flowerinsnow.greatscrollabletooltips.object.ScrollSession;
import squeek.appleskin.api.event.TooltipOverlayEvent;

public class AppleSkinListener {
    @SubscribeEvent
    public void onTooltipOverlay(TooltipOverlayEvent.Render event) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        ScrollSession<ItemStack> session = instance.getScrollSession();
        GreatScrollableTooltipsConfig config = instance.getConfig();
        event.x += session.getHorizontal() * config.sensitivity;
        event.y += session.getVertical() * config.sensitivity;
    }
}
