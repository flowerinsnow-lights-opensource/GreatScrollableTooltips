package online.flowerinsnow.greatscrollabletooltips.listener;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import online.flowerinsnow.greatscrollabletooltips.GreatScrollableTooltips;
import online.flowerinsnow.greatscrollabletooltips.event.RenderTooltipEvent;
import online.flowerinsnow.greatscrollabletooltips.common.object.ScrollSession;
import online.flowerinsnow.greatscrollabletooltips.event.ScreenCloseEvent;

import java.util.Objects;

/**
 * 监听几个需要重置滚动的情况
 */
@SideOnly(Side.CLIENT)
public class ScrollingResetListener {
    public final GreatScrollableTooltips main;

    public ScrollingResetListener(GreatScrollableTooltips main) {
        this.main = Objects.requireNonNull(main);
    }

    @SubscribeEvent
    public void preRenderTooltip(RenderTooltipEvent.Pre event) {
        ScrollSession<ItemStack> session = this.main.getScrollSession();
        session.setRendering(true);
        ItemStack itemStack = event.slot.getStack();
        if (itemStack != session.getLastItemStackRendered()) { // 如果正在渲染的物品和上一次渲染的物品不是同一个
            session.setLastItemStackRendered(itemStack);
            if (this.main.getConfig().autoReset) { // 仅当自动回正开启时自动回正
                session.resetScroll();
            }
        }
    }

    @SubscribeEvent
    public void missRenderTooltip(RenderTooltipEvent.Miss event) {
        ScrollSession<ItemStack> session = this.main.getScrollSession();
        session.setLastItemStackRendered(null);

        if (this.main.getConfig().autoReset) { // 仅当自动回正开启时自动回正
            session.resetScroll();
        }
    }

    @SubscribeEvent
    public void onScreenClose(ScreenCloseEvent event) {
        ScrollSession<ItemStack> session = this.main.getScrollSession();
        session.setLastItemStackRendered(null);
        session.setRendering(false);
        session.resetScroll();
    }
}
