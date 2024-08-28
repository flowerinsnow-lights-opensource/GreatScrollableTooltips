package online.flowerinsnow.greatscrollabletooltips.mixin;

import com.anthonyhilyard.iceberg.events.RenderTooltipExtEvent;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderTooltipEvent;
import online.flowerinsnow.greatscrollabletooltips.GreatScrollableTooltips;
import online.flowerinsnow.greatscrollabletooltips.object.ScrollSession;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(RenderTooltipExtEvent.Post.class)
@OnlyIn(Dist.CLIENT)
public class MixinPostRenderTooltipExtEvent extends RenderTooltipEvent {
    @SuppressWarnings({"UnstableApiUsage", "DataFlowIssue"})
    protected MixinPostRenderTooltipExtEvent() {
        super(null, null, 0, 0, null, null);
    }

    @Inject(
            method = "<init>(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/client/gui/GuiGraphics;IILnet/minecraft/client/gui/Font;IILjava/util/List;ZI)V",
            at = @At("RETURN"),
            remap = false
    )
    private void init(ItemStack stack, GuiGraphics graphics, int x, int y, Font font, int width, int height, List<ClientTooltipComponent> components, boolean comparison, int index, CallbackInfo ci) {
        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
        ScrollSession session = instance.getScrollSession();
        int sensitivity = instance.getConfig().sensitivity;
        this.x += session.getHorizontal() * sensitivity;
        this.y += session.getVertical() * sensitivity;
    }
}
