package online.flowerinsnow.greatscrollabletooltips.screen;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractSliderButton;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import online.flowerinsnow.greatscrollabletooltips.config.Config;
import online.flowerinsnow.greatscrollabletooltips.mixin.AccessorSliderWidget;

import java.math.BigDecimal;
import java.math.RoundingMode;

@OnlyIn(Dist.CLIENT)
public class ConfigScreen extends Screen {
    private final Screen parent;
    private final Config config;
    private AbstractSliderButton sensitivitySlider;
    public ConfigScreen(Screen parent, Config config) {
        super(new TranslatableComponent("great-scrollable-tooltips.ui.title"));
        this.parent = parent;
        this.config = config;
    }

    @Override
    protected void init() {
        this.addRenderableWidget(
                new Button(
                        this.width / 2 - 100, this.height / 2 - 70,
                        200, 20,
                        new TranslatableComponent(this.config.enable ? "great-scrollable-tooltips.ui.config.enable.true" : "great-scrollable-tooltips.ui.config.enable.false"),
                        button -> {
                            if (this.config.enable) {
                                button.setMessage(new TranslatableComponent("great-scrollable-tooltips.ui.config.enable.false"));
                                this.config.enable = false;
                            } else {
                                button.setMessage(new TranslatableComponent("great-scrollable-tooltips.ui.config.enable.true"));
                                this.config.enable = true;
                            }
                        }
                )
        );

        this.addRenderableWidget(
                new Button(
                        this.width / 2 - 100, this.height / 2 - 45,
                        200, 20,
                        new TranslatableComponent(this.config.autoReset ? "great-scrollable-tooltips.ui.config.auto-reset.true" : "great-scrollable-tooltips.ui.config.auto-reset.false"),
                        button -> {
                            ConfigScreen instance = ConfigScreen.this;
                            if (instance.config.autoReset) {
                                button.setMessage(new TranslatableComponent("great-scrollable-tooltips.ui.config.auto-reset.false"));
                                instance.config.autoReset = false;
                            } else {
                                button.setMessage(new TranslatableComponent("great-scrollable-tooltips.ui.config.auto-reset.true"));
                                instance.config.autoReset = true;
                            }
                        }
                )
        );

        this.addRenderableWidget(
                this.sensitivitySlider = new AbstractSliderButton(this.width / 2 - 100, this.height / 2 - 20,
                        200, 20, new TranslatableComponent(
                        "great-scrollable-tooltips.ui.config.sensitivity",
                        this.config.sensitivity
                ), new BigDecimal(this.config.sensitivity).add(new BigDecimal(-1)).divide(new BigDecimal(99), 2, RoundingMode.UP).doubleValue()) {
                    @Override
                    protected void updateMessage() {
                        setMessage(new TranslatableComponent(
                                "great-scrollable-tooltips.ui.config.sensitivity",
                                BigDecimal.valueOf(this.value).multiply(new BigDecimal(99)).add(BigDecimal.ONE).setScale(0, RoundingMode.DOWN).intValue()
                        ));
                    }

                    @Override
                    protected void applyValue() {
                    }
                }
        );
        this.addRenderableWidget(
                new Button(
                        this.width / 2 - 100, this.height / 2 + 5,
                        200, 20,
                        new TranslatableComponent("great-scrollable-tooltips.ui.config.reload"),
                        button -> {
                            config.load();
                            Minecraft.getInstance().setScreen(new ConfigScreen(parent, config));
                        }
                )
        );
        this.addRenderableWidget(
                new Button(
                        this.width / 2 - 100, this.height / 2 + 30,
                        200, 20,
                        new TranslatableComponent("great-scrollable-tooltips.ui.config.save-and-exit"),
                        button -> {
                            ConfigScreen.this.config.sensitivity = BigDecimal.valueOf(((AccessorSliderWidget) sensitivitySlider).getValue()).multiply(new BigDecimal(99)).add(BigDecimal.ONE).setScale(0, RoundingMode.DOWN).intValue();
                            ConfigScreen.this.config.save();
                            Minecraft.getInstance().setScreen(parent);
                        }
                )
        );
        this.addRenderableWidget(
                new Button(
                        this.width / 2 - 100, this.height / 2 + 55,
                        200, 20,
                        new TranslatableComponent("great-scrollable-tooltips.ui.config.discard-and-exit"),
                        button -> Minecraft.getInstance().setScreen(ConfigScreen.this.parent)
                )
        );
    }

    @Override
    public void render(@SuppressWarnings("NullableProblems") PoseStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        Screen.drawCenteredString(matrices, this.font, new TranslatableComponent("great-scrollable-tooltips.ui.title").getVisualOrderText(), this.width / 2, 20, 0xFFFFF);
    }
}
