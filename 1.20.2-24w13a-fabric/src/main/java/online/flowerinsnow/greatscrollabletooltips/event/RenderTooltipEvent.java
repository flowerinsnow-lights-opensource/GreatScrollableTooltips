package online.flowerinsnow.greatscrollabletooltips.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;

public interface RenderTooltipEvent {
    interface Post {
        Event<Post> EVENT = EventFactory.createArrayBacked(Post.class, listeners -> (screen, context, textRenderer, itemStack, x, y) -> {
            for (Post listener : listeners) {
                ActionResult actionResult = listener.postRenderTooltip(screen, context, textRenderer, itemStack, x, y);
                if (actionResult != ActionResult.PASS) {
                    return actionResult;
                }
            }
            return ActionResult.PASS;
        });

        ActionResult postRenderTooltip(HandledScreen<?> screen, DrawContext context, TextRenderer textRenderer, ItemStack itemStack, int x, int y);
    }

    interface Miss {
        Event<Miss> EVENT = EventFactory.createArrayBacked(Miss.class, listeners -> (screen, context, textRenderer, x, y) -> {
            for (Miss listener : listeners) {
                ActionResult actionResult = listener.missRenderTooltip(screen, context, textRenderer, x, y);
                if (actionResult != ActionResult.PASS) {
                    return actionResult;
                }
            }
            return ActionResult.PASS;
        });

        ActionResult missRenderTooltip(HandledScreen<?> screen, DrawContext context, TextRenderer textRenderer, int x, int y);
    }
}
