package online.flowerinsnow.greatscrollabletooltips.listener;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import online.flowerinsnow.greatscrollabletooltips.GreatScrollableTooltips;
import online.flowerinsnow.greatscrollabletooltips.event.PreScreenMouseScrollEvent;
import online.flowerinsnow.greatscrollabletooltips.object.ScrollSession;

import java.util.Objects;

public record MouseScrollListener(GreatScrollableTooltips main) {
    public MouseScrollListener(GreatScrollableTooltips main) {
        this.main = Objects.requireNonNull(main);
    }

    @SubscribeEvent
    public void onScreenMouseScroll(PreScreenMouseScrollEvent event) {
        if (!this.main.getConfig().enable) { // 只有启用时
            return;
        }

        ScrollSession<ItemStack> session = this.main.getScrollSession();

        if (!session.isRendering()) { // 只有渲染物品提示时才允许滚动
            return;
        }

        if (!Screen.hasShiftDown()) {
            session.addVertical((int) event.getAmount());
        } else {
            session.addHorizontal((int) event.getAmount());
        }
    }
}
