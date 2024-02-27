package online.flowerinsnow.greatscrollabletooltips.event;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.common.eventhandler.Event;

import java.util.Objects;

public class ScreenCloseEvent extends Event {
    public final GuiScreen screen;

    public ScreenCloseEvent(GuiScreen screen) {
        this.screen = Objects.requireNonNull(screen);
    }
}
