package online.flowerinsnow.greatscrollabletooltips.screen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.client.config.GuiSlider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import online.flowerinsnow.greatscrollabletooltips.GreatScrollableTooltips;
import online.flowerinsnow.greatscrollabletooltips.common.config.GreatScrollableTooltipsConfig;

import java.util.Objects;

@SideOnly(Side.CLIENT)
public class ConfigScreen extends GuiScreen {
    private final GuiScreen parent;
    private final GreatScrollableTooltipsConfig config;
    private GuiSlider sensitivitySlider;

    /*
     * DO NOT DELETE THIS METHOD
     * Forge will invoke constructor with 1 arg of <init>
     */
    public ConfigScreen(GuiScreen parent) {
        this(parent, GreatScrollableTooltips.getInstance().getConfig());
    }

    public ConfigScreen(GuiScreen parent, GreatScrollableTooltipsConfig config) {
        this.parent = Objects.requireNonNull(parent);
        this.config = Objects.requireNonNull(config);
    }

    @Override
    public void initGui() {
        this.buttonList.add(
                new GuiButton(
                        400053423,
                        this.width / 2 - 100, this.height / 2 - 70,
                        200, 20,
                        this.config.enable ? I18n.format("great-scrollable-tooltips.ui.config.enable.true") : I18n.format("great-scrollable-tooltips.ui.config.enable.false")
                )
        );

        this.buttonList.add(
                new GuiButton(
                        400053424,
                        this.width / 2 - 100, this.height / 2 - 45,
                        200, 20,
                        this.config.autoReset ? I18n.format("great-scrollable-tooltips.ui.config.auto-reset.true") : I18n.format("great-scrollable-tooltips.ui.config.auto-reset.false")
                )
        );

        this.buttonList.add(
                sensitivitySlider = new GuiSlider(
                        400053425,
                        this.width / 2 - 100, this.height / 2 - 20,
                        200, 20,
                        I18n.format("great-scrollable-tooltips.ui.config.sensitivity"), "",
                        1, 100, this.config.sensitivity, false, true
                )
        );
        this.buttonList.add(
                new GuiButton(
                        400053426,
                        this.width / 2 - 100, this.height / 2 + 5,
                        200, 20,
                        I18n.format("great-scrollable-tooltips.ui.config.reload")
                )
        );
        this.buttonList.add(
                new GuiButton(
                        400053427,
                        this.width / 2 - 100, this.height / 2 + 30,
                        200, 20,
                        I18n.format("great-scrollable-tooltips.ui.config.save-and-exit")
                )
        );
        this.buttonList.add(
                new GuiButton(
                        400053428,
                        this.width / 2 - 100, this.height / 2 + 55,
                        200, 20,
                        I18n.format("great-scrollable-tooltips.ui.config.discard-and-exit")
                )
        );
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 400053423:
                if (this.config.enable) {
                    button.displayString = I18n.format("great-scrollable-tooltips.ui.config.enable.false");
                    this.config.enable = false;
                } else {
                    button.displayString = I18n.format("great-scrollable-tooltips.ui.config.enable.true");
                    this.config.enable = true;
                }
                break;
            case 400053424:
                if (this.config.autoReset) {
                    button.displayString = I18n.format("great-scrollable-tooltips.ui.config.auto-reset.false");
                    this.config.autoReset = false;
                } else {
                    button.displayString = I18n.format("great-scrollable-tooltips.ui.config.auto-reset.true");
                    this.config.autoReset = true;
                }
                break;
            case 400053426:
                this.config.load();
                this.sensitivitySlider.setValue(this.config.sensitivity);
                break;
            case 400053427:
                this.config.sensitivity = this.sensitivitySlider.getValueInt();
                this.config.save();
                Minecraft.getMinecraft().currentScreen = parent;
                break;
            case 400053428:
                Minecraft.getMinecraft().currentScreen = parent;
                break;
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.drawCenteredString(this.fontRendererObj, I18n.format("great-scrollable-tooltips.ui.title"), this.width / 2, 20, 0xFFFFFF);
    }
}
