package online.flowerinsnow.greatscrollabletooltips.listener;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import online.flowerinsnow.greatscrollabletooltips.event.ClientTickEndEvent;
import online.flowerinsnow.greatscrollabletooltips.event.ScreenCloseEvent;

@SideOnly(Side.CLIENT)
public class EventTriggerListener {
    @SubscribeEvent
    public void onClientTickEnd(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            MinecraftForge.EVENT_BUS.post(new ClientTickEndEvent());
        }
    }

    private GuiScreen oldScreen;
    @SubscribeEvent
    public void onScreenClose(ClientTickEndEvent event) {
        GuiScreen currentScreen = Minecraft.getMinecraft().currentScreen;
        if (this.oldScreen != currentScreen) {
            if (this.oldScreen != null) {
                MinecraftForge.EVENT_BUS.post(new ScreenCloseEvent(this.oldScreen));
            }
            this.oldScreen = currentScreen;
        }
    }
}
