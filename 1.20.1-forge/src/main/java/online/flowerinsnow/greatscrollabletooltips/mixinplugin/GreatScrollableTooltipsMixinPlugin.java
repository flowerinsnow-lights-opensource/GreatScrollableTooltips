package online.flowerinsnow.greatscrollabletooltips.mixinplugin;

import net.minecraftforge.fml.loading.FMLLoader;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

public class GreatScrollableTooltipsMixinPlugin implements IMixinConfigPlugin {
    @Override
    public void onLoad(String mixinPackage) {
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        // LegendaryTooltips
        if ("online.flowerinsnow.greatscrollabletooltips.mixin.legendarytooltips.MixinLegendaryTooltips".equals(mixinClassName) || "online.flowerinsnow.greatscrollabletooltips.mixin.legendarytooltips.MixinItemModelComponent".equals(mixinClassName)) {
            return FMLLoader.getLoadingModList().getModFileById("legendarytooltips") != null;
        // Apotheosis
        } else if ("online.flowerinsnow.greatscrollabletooltips.mixin.apotheosis.MixinSocketTooltipRenderer".equals(mixinClassName)) {
            return FMLLoader.getLoadingModList().getModFileById("apotheosis") != null;
        }
        return true;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }
}
