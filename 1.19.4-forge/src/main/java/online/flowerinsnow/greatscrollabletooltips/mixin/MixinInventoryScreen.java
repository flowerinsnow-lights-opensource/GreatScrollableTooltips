package online.flowerinsnow.greatscrollabletooltips.mixin;

import net.minecraft.client.gui.screens.inventory.EffectRenderingInventoryScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import online.flowerinsnow.greatscrollabletooltips.event.MouseScrolledInParentElementEvent;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(InventoryScreen.class)
@OnlyIn(Dist.CLIENT)
public abstract class MixinInventoryScreen extends EffectRenderingInventoryScreen<InventoryMenu> {
    public MixinInventoryScreen() {
        //noinspection DataFlowIssue
        super(null, null, null);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        MouseScrolledInParentElementEvent event = new MouseScrolledInParentElementEvent(this, mouseX, mouseY, amount);
        if (MinecraftForge.EVENT_BUS.post(event)) {
            return false;
        }
        return super.mouseScrolled(mouseX, mouseY, amount);
    }
}
