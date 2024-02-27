package online.flowerinsnow.greatscrollabletooltips.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;

import java.util.List;

public interface RenderMouseoverTooltipEvent {
    interface Post {
        Event<Post> EVENT = EventFactory.createArrayBacked(Post.class, listeners -> (handledScreen, textRenderer, itemStack, tooltip, context, x, y) -> {
            for (Post listener : listeners) {
                ActionResult actionResult = listener.startDrawMouseoverTooltip(handledScreen, textRenderer, itemStack, tooltip, context, x, y);
                if (actionResult != ActionResult.PASS) {
                    return actionResult;
                }
            }
            return ActionResult.PASS;
        });

        ActionResult startDrawMouseoverTooltip(HandledScreen<?> screen, TextRenderer textRenderer, ItemStack itemStack, List<Text> tooltip, DrawContext context, int x, int y);
    }

    interface Miss {
        Event<Miss> EVENT = EventFactory.createArrayBacked(Miss.class, listeners -> screen -> {
            for (Miss listener : listeners) {
                ActionResult actionResult = listener.onMiss(screen);
                if (actionResult != ActionResult.PASS) {
                    return actionResult;
                }
            }
            return ActionResult.PASS;
        });

        ActionResult onMiss(HandledScreen<?> screen);
    }
}
