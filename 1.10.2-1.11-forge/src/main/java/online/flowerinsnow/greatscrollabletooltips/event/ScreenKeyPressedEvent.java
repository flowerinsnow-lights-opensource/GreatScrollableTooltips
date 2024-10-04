package online.flowerinsnow.greatscrollabletooltips.event;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Objects;

@SideOnly(Side.CLIENT)
public class ScreenKeyPressedEvent extends Event {
    public final GuiScreen screen;
    public final int typedChar;
    public final int keyCode;

    protected ScreenKeyPressedEvent(GuiScreen screen, int typedChar, int keyCode) {
        this.screen = screen;
        this.typedChar = typedChar;
        this.keyCode = keyCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScreenKeyPressedEvent that = (ScreenKeyPressedEvent) o;
        return this.typedChar == that.typedChar && this.keyCode == that.keyCode && Objects.equals(this.screen, that.screen);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + super.hashCode();
        result = 31 * result + (this.screen != null ? this.screen.hashCode() : 0);
        result = 31 * result + this.typedChar;
        result = 31 * result + this.keyCode;
        return result;
    }

    @Override
    public String toString() {
        return "ScreenKeyPressedEvent{" +
                "screen=" + screen +
                ", typedChar=" + typedChar +
                ", keyCode=" + keyCode +
                '}';
    }

    @SideOnly(Side.CLIENT)
    @Cancelable
    public static class Pre extends ScreenKeyPressedEvent {
        public Pre(GuiScreen screen, int typedChar, int keyCode) {
            super(screen, typedChar, keyCode);
        }

        @Override
        public boolean isCancelable() {
            return true;
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
            return "Pre{" +
                    "super=" + super.toString() +
                    "}";
        }
    }

    @SideOnly(Side.CLIENT)
    public static class Post extends ScreenKeyPressedEvent {
        public Post(GuiScreen screen, int typedChar, int keyCode) {
            super(screen, typedChar, keyCode);
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
                    "}";
        }
    }
}
