package online.flowerinsnow.greatscrollabletooltips.mixin;

import com.anthonyhilyard.iceberg.events.RenderTooltipExtEvent;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderTooltipEvent;
import online.flowerinsnow.greatscrollabletooltips.GreatScrollableTooltips;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import java.util.List;

@Mixin(RenderTooltipExtEvent.Post.class)
@OnlyIn(Dist.CLIENT)
public class MixinPostRenderTooltipExtEvent extends RenderTooltipEvent {
    @SuppressWarnings({"UnstableApiUsage", "DataFlowIssue"})
    protected MixinPostRenderTooltipExtEvent() {
        super(null, null, 0, 0, null, null);
    }

    //    @ModifyArgs(
//            method = "<init>(Lnet/minecraft/world/item/ItemStack;Lcom/mojang/blaze3d/vertex/PoseStack;IILnet/minecraft/client/gui/Font;IILjava/util/List;ZI)V",
//            at = @At("HEAD"),
//            remap = false
//    )
//    private static void init(Args args) {
//        GreatScrollableTooltips instance = GreatScrollableTooltips.getInstance();
//        int sensitivity = instance.getConfig().sensitivity;
//        args.set(2, (int) args.get(2) + instance.getHorizontal() * sensitivity);
//        args.set(3, (int) args.get(3) + instance.getVertical() * sensitivity);
//    }
    @Inject(
            method = "<init>(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/client/gui/GuiGraphics;IILnet/minecraft/client/gui/Font;IILjava/util/List;ZI)V",
            at = @At("RETURN"),
            remap = false
    )
    private void init(ItemStack stack, GuiGraphics graphics, int x, int y, Font font, int width, int height, List<ClientTooltipComponent> components, boolean comparison, int index, CallbackInfo ci) {
        GreatScrollableTooltips greatScrollableTooltips = GreatScrollableTooltips.getInstance();
        int sensitivity = greatScrollableTooltips.getConfig().sensitivity;
        this.x += greatScrollableTooltips.getHorizontal() * sensitivity;
        this.y += greatScrollableTooltips.getVertical() * sensitivity;
    }
}
