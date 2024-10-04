package online.flowerinsnow.greatscrollabletooltips.listener;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import online.flowerinsnow.greatscrollabletooltips.GreatScrollableTooltips;
import online.flowerinsnow.greatscrollabletooltips.event.ScreenMouseScrollEvent;
import online.flowerinsnow.greatscrollabletooltips.common.object.ScrollSession;

import java.util.Objects;

/**
 * 使用鼠标滚轮滚动物品
 */
@SideOnly(Side.CLIENT)
public class MouseScrollListener {
    public final GreatScrollableTooltips main;

    public MouseScrollListener(GreatScrollableTooltips main) {
        this.main = Objects.requireNonNull(main);
    }

    @SubscribeEvent
    public void onScreenMouseScroll(ScreenMouseScrollEvent event) {
        if (!this.main.getConfig().enable) { // 只有
            return;
        }

        ScrollSession<ItemStack> session = this.main.getScrollSession();

        if (!session.isRendering()) { // 只有渲染物品提示时才允许滚动
            return;
        }

        if (!GuiScreen.isShiftKeyDown()) {
            session.addHorizontal(event.amount);
        } else {
            session.addVertical(event.amount);
        }
    }
}
