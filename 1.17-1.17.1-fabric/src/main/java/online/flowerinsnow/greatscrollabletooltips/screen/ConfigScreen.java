package online.flowerinsnow.greatscrollabletooltips.screen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.TranslatableText;
import online.flowerinsnow.greatscrollabletooltips.config.Config;
import online.flowerinsnow.greatscrollabletooltips.mixin.AccessorSliderWidget;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Environment(EnvType.CLIENT)
public class ConfigScreen extends Screen {
    private final Screen parent;
    private final Config config;
    private SliderWidget sensitivitySlider;
    public ConfigScreen(Screen parent, Config config) {
        super(new TranslatableText("great-scrollable-tooltips.ui.title"));
        this.parent = parent;
        this.config = config;
    }

    @Override
    protected void init() {
        this.addDrawableChild(
                new ButtonWidget(
                        this.width / 2 - 100, this.height / 2 - 60,
                        200, 20,
                        new TranslatableText(config.enable ? "great-scrollable-tooltips.ui.config.enable.true" : "great-scrollable-tooltips.ui.config.enable.false"),
                        button -> {
                            if (config.enable) {
                                button.setMessage(new TranslatableText("great-scrollable-tooltips.ui.config.enable.false"));
                                config.enable = false;
                            } else {
                                button.setMessage(new TranslatableText("great-scrollable-tooltips.ui.config.enable.true"));
                                config.enable = true;
                            }
                        }
                )
        );
        this.addDrawableChild(
                sensitivitySlider = new SliderWidget(this.width / 2 - 100, this.height / 2 - 35,
                200, 20, new TranslatableText(
                        "great-scrollable-tooltips.ui.config.sensitivity",
                        config.sensitivity
                ), new BigDecimal(config.sensitivity).add(new BigDecimal(-1)).divide(new BigDecimal(99), 2, RoundingMode.DOWN).doubleValue()) {
                    @Override
                    protected void updateMessage() {
                        setMessage(new TranslatableText(
                                "great-scrollable-tooltips.ui.config.sensitivity",
                                BigDecimal.valueOf(value).multiply(new BigDecimal(99)).add(BigDecimal.ONE).setScale(0, RoundingMode.DOWN).intValue()
                        ));
                    }

                    @Override
                    protected void applyValue() {
                    }
                }
        );
        this.addDrawableChild(
                new ButtonWidget(
                        this.width / 2 - 100, this.height / 2 - 10,
                        200, 20,
                        new TranslatableText("great-scrollable-tooltips.ui.config.reload"),
                        button -> {
                            config.load();
                            MinecraftClient.getInstance().currentScreen = new ConfigScreen(parent, config);
                        }
                )
        );
        this.addDrawableChild(
                new ButtonWidget(
                        this.width / 2 - 100, this.height / 2 + 15,
                        200, 20,
                        new TranslatableText("great-scrollable-tooltips.ui.config.save-and-exit"),
                        button -> {
                            config.sensitivity = BigDecimal.valueOf(((AccessorSliderWidget) sensitivitySlider).getValue()).multiply(new BigDecimal(99)).add(BigDecimal.ONE).setScale(0, RoundingMode.DOWN).intValue();
                            config.save();
                            MinecraftClient.getInstance().currentScreen = parent;
                        }
                )
        );
        this.addDrawableChild(
                new ButtonWidget(
                        this.width / 2 - 100, this.height / 2 + 40,
                        200, 20,
                        new TranslatableText("great-scrollable-tooltips.ui.config.discard-and-exit"),
                        button -> MinecraftClient.getInstance().currentScreen = parent
                )
        );
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        DrawableHelper.drawCenteredTextWithShadow(matrices, this.textRenderer, new TranslatableText("great-scrollable-tooltips.ui.title").asOrderedText(), this.width / 2, 20, 0xFFFFFF);
    }
}
