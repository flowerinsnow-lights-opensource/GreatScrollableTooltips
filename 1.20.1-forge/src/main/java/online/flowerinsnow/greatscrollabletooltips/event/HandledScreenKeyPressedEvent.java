package online.flowerinsnow.greatscrollabletooltips.event;

import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;

import java.util.Objects;

@OnlyIn(Dist.CLIENT)
@Cancelable
public class HandledScreenKeyPressedEvent extends Event {
    private final AbstractContainerScreen<?> screen;
    private final int keyCode;
    private final int scanCode;
    private final int modifiers;

    public HandledScreenKeyPressedEvent(AbstractContainerScreen<?> screen, int keyCode, int scanCode, int modifiers) {
        this.screen = screen;
        this.keyCode = keyCode;
        this.scanCode = scanCode;
        this.modifiers = modifiers;
    }

    public AbstractContainerScreen<?> getScreen() {
        return this.screen;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public int getScanCode() {
        return scanCode;
    }

    public int getModifiers() {
        return modifiers;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        HandledScreenKeyPressedEvent that = (HandledScreenKeyPressedEvent) object;
        return keyCode == that.keyCode && scanCode == that.scanCode && modifiers == that.modifiers && Objects.equals(this.screen, that.screen);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (this.screen != null ? this.screen.hashCode() : 0);
        result = 31 * result + this.keyCode;
        result = 31 * result + this.scanCode;
        result = 31 * result + this.modifiers;
        return result;
    }

    @Override
    public String toString() {
        return "HandledScreenKeyPressedEvent{" +
                "screen=" + this.screen +
                ", keyCode=" + this.keyCode +
                ", scanCode=" + this.scanCode +
                ", modifiers=" + this.modifiers +
                '}';
    }
}
