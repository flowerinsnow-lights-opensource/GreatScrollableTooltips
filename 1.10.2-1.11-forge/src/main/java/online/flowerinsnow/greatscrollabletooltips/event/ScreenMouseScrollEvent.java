package online.flowerinsnow.greatscrollabletooltips.event;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Objects;

@SideOnly(Side.CLIENT)
public class ScreenMouseScrollEvent extends Event {
    public final GuiScreen screen;
    public final int amount;

    public ScreenMouseScrollEvent(GuiScreen screen, int amount) {
        this.screen = screen;
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScreenMouseScrollEvent that = (ScreenMouseScrollEvent) o;
        return this.amount == that.amount && Objects.equals(this.screen, that.screen);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (this.screen != null ? this.screen.hashCode() : 0);
        result = 31 * result + this.amount;
        return result;
    }

    @Override
    public String toString() {
        return "ScreenMouseScrollEvent{" +
                "screen=" + this.screen +
                ", amount=" + this.amount +
                '}';
    }
}
