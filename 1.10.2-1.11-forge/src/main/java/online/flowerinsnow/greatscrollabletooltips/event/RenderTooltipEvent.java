package online.flowerinsnow.greatscrollabletooltips.event;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Slot;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Objects;

@SideOnly(Side.CLIENT)
public class RenderTooltipEvent extends Event {
    public final GuiContainer screen;

    protected RenderTooltipEvent(GuiContainer screen) {
        this.screen = screen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RenderTooltipEvent that = (RenderTooltipEvent) o;
        return Objects.equals(this.screen, that.screen);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (screen != null ? screen.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RenderTooltipEvent{" +
                "screen=" + screen +
                '}';
    }

    @SideOnly(Side.CLIENT)
    public static class Pre extends RenderTooltipEvent {
        public final Slot slot;

        public Pre(GuiContainer screen, Slot slot) {
            super(screen);
            this.slot = slot;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;
            Pre pre = (Pre) o;
            return Objects.equals(this.slot, pre.slot);
        }

        @Override
        public int hashCode() {
            int result = 17;
            result = 31 * result + super.hashCode();
            result = 31 * result + (this.slot != null ? slot.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "Pre{" +
                    "super=" + super.toString() +
                    ", slot=" + slot +
                    '}';
        }
    }

    @SideOnly(Side.CLIENT)
    public static class Miss extends RenderTooltipEvent {
        public Miss(GuiContainer screen) {
            super(screen);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            return super.equals(o);
        }

        @Override
        public int hashCode() {
            int result = 17;
            result = 31 * result + super.hashCode();
            return result;
        }

        @Override
        public String toString() {
            return "Post{" +
                    "super=" + super.toString() +
                    '}';
        }
    }
}
