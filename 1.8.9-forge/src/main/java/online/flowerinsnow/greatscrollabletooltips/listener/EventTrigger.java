package online.flowerinsnow.greatscrollabletooltips.listener;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import online.flowerinsnow.greatscrollabletooltips.event.ScreenCloseEvent;

public class EventTrigger {
    private GuiScreen oldScreen;
    @SubscribeEvent
    public void onClientTickEnd(TickEvent.ClientTickEvent event) {
        GuiScreen currentScreen = Minecraft.getMinecraft().currentScreen;
        if (event.phase == TickEvent.Phase.END && oldScreen != currentScreen) {
            if (oldScreen != null) {
                MinecraftForge.EVENT_BUS.post(new ScreenCloseEvent(oldScreen));
            }
            oldScreen = currentScreen;
        }
    }
}
