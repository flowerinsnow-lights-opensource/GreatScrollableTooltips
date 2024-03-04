package online.flowerinsnow.greatscrollabletooltips;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import online.flowerinsnow.greatscrollabletooltips.config.Config;
import online.flowerinsnow.greatscrollabletooltips.listener.EventTrigger;
import online.flowerinsnow.greatscrollabletooltips.listener.GreatScrollableTooltipsListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

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
    public static final String VERSION = "1.1.1";
    private static GreatScrollableTooltips instance;

    public static final Logger LOGGER = LogManager.getLogger(MODID);

    private Config config;

    private int horizontal;
    private int vertical;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        instance = this;
        config = new Config(new File(event.getModConfigurationDirectory(), MODID + ".conf"));
        config.saveDefaultConfig();
        config.load();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new GreatScrollableTooltipsListener());
        MinecraftForge.EVENT_BUS.register(new EventTrigger());
    }

    public static GreatScrollableTooltips getInstance() {
        return instance;
    }

    public Config getConfig() {
        return config;
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

    @SuppressWarnings("UnusedReturnValue")
    public GreatScrollableTooltips setVertical(int vertical) {
        this.vertical = vertical;
        return this;
    }
}
