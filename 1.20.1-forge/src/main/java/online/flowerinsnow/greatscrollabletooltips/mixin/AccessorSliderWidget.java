package online.flowerinsnow.greatscrollabletooltips.mixin;

import net.minecraft.client.gui.components.AbstractSliderButton;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(AbstractSliderButton.class)
@OnlyIn(Dist.CLIENT)
public interface AccessorSliderWidget {
    @Accessor("value")
    double getValue();
}
