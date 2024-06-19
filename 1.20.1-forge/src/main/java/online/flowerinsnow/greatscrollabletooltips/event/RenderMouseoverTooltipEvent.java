package online.flowerinsnow.greatscrollabletooltips.event;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.Event;

import java.util.Objects;

@OnlyIn(Dist.CLIENT)
public class RenderMouseoverTooltipEvent extends Event {
    protected final AbstractContainerScreen<?> screen;

    protected RenderMouseoverTooltipEvent(AbstractContainerScreen<?> screen) {
        this.screen = screen;
    }

    public AbstractContainerScreen<?> getScreen() {
        return this.screen;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        RenderMouseoverTooltipEvent that = (RenderMouseoverTooltipEvent) object;
        return Objects.equals(this.screen, that.screen);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (this.screen != null ? this.screen.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RenderMouseoverTooltipEvent{" +
                "screen=" + screen +
                '}';
    }

    public static class Post extends RenderMouseoverTooltipEvent {
        private final GuiGraphics graphics;
        private final ItemStack stack;
        private final int x;
        private final int y;

        public Post(AbstractContainerScreen<?> screen, GuiGraphics graphics, ItemStack stack, int x, int y) {
            super(screen);
            this.graphics = graphics;
            this.stack = stack;
            this.x = x;
            this.y = y;
        }

        public GuiGraphics getGraphics() {
            return this.graphics;
        }

        public ItemStack getStack() {
            return stack;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (object == null || getClass() != object.getClass()) return false;
            if (!super.equals(object)) return false;
            Post that = (Post) object;
            return this.x == that.x && this.y == that.y && Objects.equals(this.graphics, that.graphics) && Objects.equals(this.stack, that.stack);
        }

        @Override
        public int hashCode() {
            int result = 17;
            result = 31 * result + super.hashCode();
            result = 31 * result + (this.graphics != null ? this.graphics.hashCode() : 0);
            result = 31 * result + (this.stack != null ? this.stack.hashCode() : 0);
            result = 31 * result + this.x;
            result = 31 * result + this.y;
            return result;
        }

        @Override
        public String toString() {
            return "Post{" +
                    "super=" + super.toString() +
                    ", graphics=" + graphics +
                    ", stack=" + stack +
                    ", x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    public static class Miss extends RenderMouseoverTooltipEvent {
        public Miss(AbstractContainerScreen<?> screen) {
            super(screen);
        }

        @Override
        public boolean equals(Object object) {
            return super.equals(object);
        }

        @Override
        public int hashCode() {
            int result = 17;
            result = 31 * result + super.hashCode();
            return result;
        }

        @Override
        public String toString() {
            return "Miss{" +
                    "super=" + super.toString() +
                    '}';
        }
    }
}
