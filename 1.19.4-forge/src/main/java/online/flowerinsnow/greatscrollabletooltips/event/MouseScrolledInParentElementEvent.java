package online.flowerinsnow.greatscrollabletooltips.event;

import net.minecraft.client.gui.components.events.ContainerEventHandler;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;

import java.util.Objects;

@OnlyIn(Dist.CLIENT)
@Cancelable
public class MouseScrolledInParentElementEvent extends Event {
    private final ContainerEventHandler parentElement;
    private final double mouseX;
    private final double mouseY;
    private final double amount;

    public MouseScrolledInParentElementEvent(ContainerEventHandler parentElement, double mouseX, double mouseY, double amount) {
        this.parentElement = parentElement;
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        this.amount = amount;
    }

    public ContainerEventHandler getParentElement() {
        return parentElement;
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
        MouseScrolledInParentElementEvent that = (MouseScrolledInParentElementEvent) object;
        return Double.compare(this.mouseX, that.mouseX) == 0 && Double.compare(this.mouseY, that.mouseY) == 0 && Double.compare(this.amount, that.amount) == 0 && Objects.equals(this.parentElement, that.parentElement);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (this.parentElement != null ? this.parentElement.hashCode() : 0);
        result = 31 * result + Double.hashCode(this.mouseX);
        result = 31 * result + Double.hashCode(this.mouseY);
        result = 31 * result + Double.hashCode(this.amount);
        return result;
    }

    @Override
    public String toString() {
        return "MouseScrolledInParentElementEvent{" +
                "parentElement=" + parentElement +
                ", mouseX=" + mouseX +
                ", mouseY=" + mouseY +
                ", amount=" + amount +
                '}';
    }
}
