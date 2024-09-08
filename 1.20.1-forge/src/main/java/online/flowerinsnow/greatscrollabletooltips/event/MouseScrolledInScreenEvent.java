package online.flowerinsnow.greatscrollabletooltips.event;

import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;

import java.util.Objects;

@OnlyIn(Dist.CLIENT)
@Cancelable
public class MouseScrolledInScreenEvent extends Event {
    private final AbstractContainerScreen<?> screen;
    private final double mouseX;
    private final double mouseY;
    private final double amount;

    public MouseScrolledInScreenEvent(AbstractContainerScreen<?> screen, double mouseX, double mouseY, double amount) {
        this.screen = screen;
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        this.amount = amount;
    }

    public AbstractContainerScreen<?> getScreen() {
        return screen;
    }

    public double getMouseX() {
        return mouseX;
    }

    public double getMouseY() {
        return mouseY;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        MouseScrolledInScreenEvent that = (MouseScrolledInScreenEvent) object;
        return Double.compare(this.mouseX, that.mouseX) == 0 && Double.compare(this.mouseY, that.mouseY) == 0 && Double.compare(this.amount, that.amount) == 0 && Objects.equals(this.screen, that.screen);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (this.screen != null ? this.screen.hashCode() : 0);
        result = 31 * result + Double.hashCode(this.mouseX);
        result = 31 * result + Double.hashCode(this.mouseY);
        result = 31 * result + Double.hashCode(this.amount);
        return result;
    }

    @Override
    public String toString() {
        return "MouseScrolledInParentElementEvent{" +
                "screen=" + screen +
                ", mouseX=" + mouseX +
                ", mouseY=" + mouseY +
                ", amount=" + amount +
                '}';
    }
}
