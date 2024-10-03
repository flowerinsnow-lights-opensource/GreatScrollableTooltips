package online.flowerinsnow.greatscrollabletooltips.mixin;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(GuiScreen.class)
@SideOnly(Side.CLIENT)
public class MixinHandledScreen {

}
