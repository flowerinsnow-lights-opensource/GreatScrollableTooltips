package online.flowerinsnow.greatscrollabletooltips.event;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;

@OnlyIn(Dist.CLIENT)
@Cancelable
public class HandledScreenKeyPressedEvent extends Event {
    private final int keyCode;
    private final int scanCode;
    private final int modifiers;

    public HandledScreenKeyPressedEvent(int keyCode, int scanCode, int modifiers) {
        this.keyCode = keyCode;
        this.scanCode = scanCode;
        this.modifiers = modifiers;
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
        return keyCode == that.keyCode && scanCode == that.scanCode && modifiers == that.modifiers;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + this.keyCode;
        result = 31 * result + this.scanCode;
        result = 31 * result + this.modifiers;
        return result;
    }

    @Override
    public String toString() {
        return "HandledScreenKeyPressedEvent{" +
                "keyCode=" + keyCode +
                ", scanCode=" + scanCode +
                ", modifiers=" + modifiers +
                '}';
    }
}
