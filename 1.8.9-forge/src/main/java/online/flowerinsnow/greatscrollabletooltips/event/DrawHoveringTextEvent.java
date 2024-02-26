package online.flowerinsnow.greatscrollabletooltips.event;

import net.minecraft.client.gui.FontRenderer;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;

import java.util.List;

public class DrawHoveringTextEvent extends Event {
    public final List<String> textLines;
    public final int mouseX;
    public final int mouseY;
    public final int screenWidth;
    public final int screenHeight;
    public final int maxTextWidth;
    public final FontRenderer font;

    protected DrawHoveringTextEvent(List<String> textLines, int mouseX, int mouseY, int screenWidth, int screenHeight, int maxTextWidth, FontRenderer font) {
        this.textLines = textLines;
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.maxTextWidth = maxTextWidth;
        this.font = font;
    }

    @Cancelable
    public static class Pre extends DrawHoveringTextEvent {
        public Pre(List<String> textLines, int mouseX, int mouseY, int screenWidth, int screenHeight, int maxTextWidth, FontRenderer font) {
            super(textLines, mouseX, mouseY, screenWidth, screenHeight, maxTextWidth, font);
        }

        @Override
        public boolean isCancelable() {
            return true;
        }
    }

    public static class Post extends DrawHoveringTextEvent {
        public Post(List<String> textLines, int mouseX, int mouseY, int screenWidth, int screenHeight, int maxTextWidth, FontRenderer font) {
            super(textLines, mouseX, mouseY, screenWidth, screenHeight, maxTextWidth, font);
        }
    }
}
