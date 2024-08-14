package online.flowerinsnow.greatscrollabletooltips;

import com.mojang.logging.LogUtils;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import online.flowerinsnow.greatscrollabletooltips.config.Config;
import online.flowerinsnow.greatscrollabletooltips.event.MouseScrolledInParentElementEvent;
import online.flowerinsnow.greatscrollabletooltips.event.RenderMouseoverTooltipEvent;
import online.flowerinsnow.greatscrollabletooltips.listener.CursorKeyListener;
import online.flowerinsnow.greatscrollabletooltips.screen.ConfigScreen;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;

import java.util.function.Consumer;

@Mod(GreatScrollableTooltips.MODID)
public class GreatScrollableTooltips {
    public static final String MODID = "great_scrollable_tooltips";
    private static final Logger LOGGER = LogUtils.getLogger();

    private static GreatScrollableTooltips instance;

    private Config config;

    private int horizontal;
    private int vertical;
    private boolean rendering;

    public static final Lazy<KeyMapping> KEY_BINDING_SCROLL_UP = Lazy.of(() -> new KeyMapping("great-scrollable-tooltips.key-binding.scroll-up", GLFW.GLFW_KEY_UP, "great-scrollable-tooltips.key-binding.category"));
    public static final Lazy<KeyMapping> KEY_BINDING_SCROLL_LEFT = Lazy.of(() -> new KeyMapping("great-scrollable-tooltips.key-binding.scroll-left", GLFW.GLFW_KEY_LEFT, "great-scrollable-tooltips.key-binding.category"));
    public static final Lazy<KeyMapping> KEY_BINDING_SCROLL_DOWN = Lazy.of(() -> new KeyMapping("great-scrollable-tooltips.key-binding.scroll-down", GLFW.GLFW_KEY_DOWN, "great-scrollable-tooltips.key-binding.category"));
    public static final Lazy<KeyMapping> KEY_BINDING_SCROLL_RIGHT = Lazy.of(() -> new KeyMapping("great-scrollable-tooltips.key-binding.scroll-right", GLFW.GLFW_KEY_RIGHT, "great-scrollable-tooltips.key-binding.category"));

    public GreatScrollableTooltips() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::onClientSetup);

        MinecraftForge.EVENT_BUS.addListener(this::onRegisterKeyMappings);
    }

    public void onClientSetup(FMLClientSetupEvent event) {
        GreatScrollableTooltips.instance = this;

        this.config = new Config();
        this.config.saveDefaultConfig();
        this.config.load();

        ModLoadingContext.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class, () ->
            new ConfigScreenHandler.ConfigScreenFactory((client, parent) -> new ConfigScreen(parent, GreatScrollableTooltips.this.config))
        );

        IEventBus eventBus = MinecraftForge.EVENT_BUS;

        eventBus.addListener((Consumer<MouseScrolledInParentElementEvent>) e -> {
            Minecraft client = Minecraft.getInstance();
            if (client.screen != null && GreatScrollableTooltips.this.config.enable && GreatScrollableTooltips.this.rendering) {
                if (Screen.hasShiftDown()) {
                    GreatScrollableTooltips.this.horizontal += (int) e.getAmount();
                } else {
                    GreatScrollableTooltips.this.vertical += (int) e.getAmount();
                }
            }
        });

        eventBus.addListener((Consumer<RenderMouseoverTooltipEvent.Post>) e ->
                GreatScrollableTooltips.this.rendering = true
        );

        eventBus.addListener((Consumer<RenderMouseoverTooltipEvent.Miss>) e ->
                GreatScrollableTooltips.this.rendering = false
        );

        eventBus.addListener((Consumer<TickEvent.ClientTickEvent>) e -> {
            if (e.phase == TickEvent.Phase.END) {
                if (Minecraft.getInstance().screen == null) {
                    GreatScrollableTooltips.this.horizontal = 0;
                    GreatScrollableTooltips.this.vertical = 0;
                }
            }
        });

        eventBus.register(new CursorKeyListener());
    }

    public void onRegisterKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(KEY_BINDING_SCROLL_UP.get());
        event.register(KEY_BINDING_SCROLL_LEFT.get());
        event.register(KEY_BINDING_SCROLL_DOWN.get());
        event.register(KEY_BINDING_SCROLL_RIGHT.get());
    }

    public static GreatScrollableTooltips getInstance() {
        return GreatScrollableTooltips.instance;
    }

    public Config getConfig() {
        return this.config;
    }

    public int getHorizontal() {
        return this.horizontal;
    }

    public void setHorizontal(int horizontal) {
        this.horizontal = horizontal;
    }

    public int getVertical() {
        return this.vertical;
    }

    public void setVertical(int vertical) {
        this.vertical = vertical;
    }

    public boolean isRendering() {
        return this.rendering;
    }
}
