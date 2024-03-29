package online.flowerinsnow.greatscrollabletooltips;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.ActionResult;
import online.flowerinsnow.greatscrollabletooltips.config.Config;
import online.flowerinsnow.greatscrollabletooltips.event.MouseScrolledInParentElementEvent;
import online.flowerinsnow.greatscrollabletooltips.event.RenderMouseoverTooltipEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Environment(EnvType.CLIENT)
public class GreatScrollableTooltips implements ClientModInitializer {
	public static final String MODID = "great-scrollable-tooltips";
    public static final Logger LOGGER = LogManager.getLogger(MODID);

	private static GreatScrollableTooltips instance;

	private Config config;

	private int vertical;
	private boolean rendering;

	@Override
	public void onInitializeClient() {
		instance = this;

		config = new Config();
		config.saveDefaultConfig();
		config.load();

		MouseScrolledInParentElementEvent.EVENT.register((parentElement, mouseX, mouseY, amount) -> {
			MinecraftClient client = MinecraftClient.getInstance();
			if (client.currentScreen != null && config.enable && rendering) {
				vertical += (int) amount;
			}
			return ActionResult.PASS;
		});

		RenderMouseoverTooltipEvent.Post.EVENT.register((screen, matrices, stack, x, y) -> {
			rendering = true;
			return ActionResult.PASS;
		});

		RenderMouseoverTooltipEvent.Miss.EVENT.register(screen -> {
			rendering = false;
			return ActionResult.PASS;
		});

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (client.currentScreen == null) {
				vertical = 0;
			}
		});
	}

	public static GreatScrollableTooltips getInstance() {
		return instance;
	}

	public Config getConfig() {
		return config;
	}

	public int getVertical() {
		return vertical;
	}

	public void setVertical(int vertical) {
		this.vertical = vertical;
	}
}