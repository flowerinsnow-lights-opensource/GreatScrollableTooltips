package online.flowerinsnow.greatscrollabletooltips;

import net.minecraft.client.Minecraft;
import net.minecraft.crash.CrashReport;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventBus;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import online.flowerinsnow.greatscrollabletooltips.common.config.GreatScrollableTooltipsConfig;
import online.flowerinsnow.greatscrollabletooltips.listener.EventTriggerListener;
import online.flowerinsnow.greatscrollabletooltips.listener.KeyScrollListener;
import online.flowerinsnow.greatscrollabletooltips.listener.MouseScrollListener;
import online.flowerinsnow.greatscrollabletooltips.listener.ScrollingResetListener;
import online.flowerinsnow.greatscrollabletooltips.manager.KeyBindingManager;
import online.flowerinsnow.greatscrollabletooltips.common.object.ScrollSession;
import online.flowerinsnow.greatscrollabletooltips.common.provider.ModEnvironmentProvider;

import java.io.InputStream;
import java.nio.file.Path;

@Mod(
        modid = GreatScrollableTooltips.MODID,
        name = GreatScrollableTooltips.NAME,
        version = GreatScrollableTooltips.VERSION,
        clientSideOnly = true,
        guiFactory = "online.flowerinsnow.greatscrollabletooltips.screen.GreatScrollableTooltipsGuiFactory"
)
@SideOnly(Side.CLIENT)
public class GreatScrollableTooltips {
    public static final String MODID = "great-scrollable-tooltips";
    public static final String NAME = "Great Scrollable Tooltips";
    public static final String VERSION = "3.1.0";

    private static GreatScrollableTooltips instance;

    private GreatScrollableTooltipsConfig config;

    private ScrollSession<ItemStack> scrollSession;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        GreatScrollableTooltips.instance = this;
        this.scrollSession = new ScrollSession<>();
        this.initConfig(event.getModConfigurationDirectory().toPath());
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        KeyBindingManager.registerAll();
        this.initListeners();
    }

    private void initConfig(Path modConfigurationDirectory) {
        this.config = new GreatScrollableTooltipsConfig(
                new ModEnvironmentProvider() {
                    @Override
                    public InputStream getDefaultConfigAsStream() {
                        return GreatScrollableTooltips.class.getResourceAsStream("/config.toml");
                    }

                    @Override
                    public Path getConfigFile() {
                        return modConfigurationDirectory.resolve(MODID + ".toml");
                    }

                    @Override
                    public void crash(Throwable throwable, String msg) {
                        Minecraft.getMinecraft().crashed(CrashReport.makeCrashReport(throwable, msg));
                    }
                }
        );
        this.config.saveDefaultConfig();
        this.config.load();
    }

    private void initListeners() {
        EventBus eventBus = MinecraftForge.EVENT_BUS;
        eventBus.register(new EventTriggerListener());
        eventBus.register(new MouseScrollListener(this));
        eventBus.register(new KeyScrollListener(this));
        eventBus.register(new ScrollingResetListener(this));
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
