package online.flowerinsnow.greatscrollabletooltips;

import net.minecraft.CrashReport;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.world.item.ItemStack;
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
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.fml.loading.FMLPaths;
import online.flowerinsnow.greatscrollabletooltips.config.GreatScrollableTooltipsConfig;
import online.flowerinsnow.greatscrollabletooltips.event.MouseScrolledInScreenEvent;
import online.flowerinsnow.greatscrollabletooltips.event.RenderTooltipEvent;
import online.flowerinsnow.greatscrollabletooltips.listener.AppleSkinListener;
import online.flowerinsnow.greatscrollabletooltips.listener.ScreenKeyPressedListener;
import online.flowerinsnow.greatscrollabletooltips.object.ScrollSession;
import online.flowerinsnow.greatscrollabletooltips.provider.ModEnvironmentProvider;
import online.flowerinsnow.greatscrollabletooltips.screen.ConfigScreen;
import org.lwjgl.glfw.GLFW;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.function.Consumer;

@Mod(GreatScrollableTooltips.MODID)
public class GreatScrollableTooltips {
    public static final String MODID = "great_scrollable_tooltips";

    private static GreatScrollableTooltips instance;

    private GreatScrollableTooltipsConfig config;

    private ScrollSession<ItemStack> scrollSession;

    public static final Lazy<KeyMapping> KEY_BINDING_SCROLL_UP = Lazy.of(() -> new KeyMapping("great-scrollable-tooltips.key-binding.scroll-up", GLFW.GLFW_KEY_UP, "great-scrollable-tooltips.key-binding.category"));
    public static final Lazy<KeyMapping> KEY_BINDING_SCROLL_LEFT = Lazy.of(() -> new KeyMapping("great-scrollable-tooltips.key-binding.scroll-left", GLFW.GLFW_KEY_LEFT, "great-scrollable-tooltips.key-binding.category"));
    public static final Lazy<KeyMapping> KEY_BINDING_SCROLL_DOWN = Lazy.of(() -> new KeyMapping("great-scrollable-tooltips.key-binding.scroll-down", GLFW.GLFW_KEY_DOWN, "great-scrollable-tooltips.key-binding.category"));
    public static final Lazy<KeyMapping> KEY_BINDING_SCROLL_RIGHT = Lazy.of(() -> new KeyMapping("great-scrollable-tooltips.key-binding.scroll-right", GLFW.GLFW_KEY_RIGHT, "great-scrollable-tooltips.key-binding.category"));

    public GreatScrollableTooltips() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::onClientSetup);
    }

    public void onClientSetup(FMLClientSetupEvent event) {
        GreatScrollableTooltips.instance = this;
        this.scrollSession = new ScrollSession<>();

        this.initConfig();
        this.initListeners();
    }

    private void initConfig() {
        this.config = new GreatScrollableTooltipsConfig(new ModEnvironmentProvider() {
            @Override
            public InputStream getDefaultConfigAsStream() {
                return GreatScrollableTooltips.class.getResourceAsStream("/config.toml");
            }

            @Override
            public Path getConfigFile() {
                return FMLPaths.CONFIGDIR.get().resolve(GreatScrollableTooltips.MODID + ".toml");
            }

            @Override
            public void crash(Throwable throwable, String msg) {
                Minecraft.crash(CrashReport.forThrowable(throwable, msg));
            }
        });
        this.config.saveDefaultConfig();
        this.config.load();

        ModLoadingContext.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class, () ->
                new ConfigScreenHandler.ConfigScreenFactory((client, parent) -> new ConfigScreen(parent, GreatScrollableTooltips.this.config))
        );
    }

    private void initListeners() {
        IEventBus eventBus = MinecraftForge.EVENT_BUS;

        // 鼠标滚动时
        eventBus.addListener((Consumer<MouseScrolledInScreenEvent>) e -> {
            Minecraft client = Minecraft.getInstance();
            if (client.screen != null && GreatScrollableTooltips.this.config.enable && GreatScrollableTooltips.this.scrollSession.isRendering()) { // 只有渲染物品提示时才允许滚动
                ScrollSession<ItemStack> session = GreatScrollableTooltips.this.scrollSession;
                if (Screen.hasShiftDown()) {
                    session.addHorizontal((int) e.getAmount());
                } else {
                    session.addVertical((int) e.getAmount());
                }
            }
        });

        // 渲染物品提示结束后
        eventBus.addListener((Consumer<RenderTooltipEvent.Post>) e -> {
            ScrollSession<ItemStack> session = GreatScrollableTooltips.this.scrollSession;
            session.setRendering(true);
            ItemStack itemStack = e.getStack();
            if (itemStack != session.getLastItemStackRendered()) { // 如果正在渲染的物品和上一次渲染的物品不是同一个
                session.setLastItemStackRendered(itemStack);

                if (GreatScrollableTooltips.this.config.autoReset) { // 自动回正
                    session.resetScroll();
                }
            }
        });

        // 本 tick 没有渲染物品提示
        eventBus.addListener((Consumer<RenderTooltipEvent.Miss>) e -> {
            ScrollSession<ItemStack> session = GreatScrollableTooltips.this.scrollSession;
            session.setRendering(false);
            session.setLastItemStackRendered(null);
            if (GreatScrollableTooltips.this.config.autoReset) { // 自动回正
                session.resetScroll();
            }
        });

        eventBus.addListener((Consumer<TickEvent.ClientTickEvent>) e -> {
            if (e.phase == TickEvent.Phase.END) {
                if (Minecraft.getInstance().screen == null) {
                    GreatScrollableTooltips.this.scrollSession.resetScroll();
                }
            }
        });

        eventBus.register(new ScreenKeyPressedListener());
        eventBus.addListener(this::initKeyBindings);

        if (FMLLoader.getLoadingModList().getModFileById("appleskin") != null) {
            eventBus.register(new AppleSkinListener());
        }
    }

    public void initKeyBindings(RegisterKeyMappingsEvent event) {
        event.register(GreatScrollableTooltips.KEY_BINDING_SCROLL_UP.get());
        event.register(GreatScrollableTooltips.KEY_BINDING_SCROLL_LEFT.get());
        event.register(GreatScrollableTooltips.KEY_BINDING_SCROLL_DOWN.get());
        event.register(GreatScrollableTooltips.KEY_BINDING_SCROLL_RIGHT.get());
    }

    public static GreatScrollableTooltips getInstance() {
        return GreatScrollableTooltips.instance;
    }

    public GreatScrollableTooltipsConfig getConfig() {
        return this.config;
    }

    public ScrollSession<ItemStack> getScrollSession() {
        return this.scrollSession;
    }
}
