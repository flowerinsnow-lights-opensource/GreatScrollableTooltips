package online.flowerinsnow.greatscrollabletooltips;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.ParentElement;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.util.ActionResult;
import online.flowerinsnow.greatscrollabletooltips.config.Config;
import online.flowerinsnow.greatscrollabletooltips.event.HandledScreenKeyPressedEvent;
import online.flowerinsnow.greatscrollabletooltips.event.MouseScrolledInParentElementEvent;
import online.flowerinsnow.greatscrollabletooltips.event.RenderMouseoverTooltipEvent;
import online.flowerinsnow.greatscrollabletooltips.listener.CursorKeyListener;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Environment(EnvType.CLIENT)
public class GreatScrollableTooltips implements ClientModInitializer {
	public static final String MODID = "great-scrollable-tooltips";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	private static GreatScrollableTooltips instance;

	private Config config;

	private int horizontal;
	private int vertical;
	private boolean rendering;

	public static final KeyBinding KEY_BINDING_SCROLL_UP = new KeyBinding("great-scrollable-tooltips.key-binding.scroll-up", GLFW.GLFW_KEY_UP, "great-scrollable-tooltips.key-binding.category");
	public static final KeyBinding KEY_BINDING_SCROLL_LEFT = new KeyBinding("great-scrollable-tooltips.key-binding.scroll-left", GLFW.GLFW_KEY_LEFT, "great-scrollable-tooltips.key-binding.category");
	public static final KeyBinding KEY_BINDING_SCROLL_DOWN = new KeyBinding("great-scrollable-tooltips.key-binding.scroll-down", GLFW.GLFW_KEY_DOWN, "great-scrollable-tooltips.key-binding.category");
	public static final KeyBinding KEY_BINDING_SCROLL_RIGHT = new KeyBinding("great-scrollable-tooltips.key-binding.scroll-right", GLFW.GLFW_KEY_RIGHT, "great-scrollable-tooltips.key-binding.category");

	@Override
	public void onInitializeClient() {
		GreatScrollableTooltips.instance = this;

		this.config = new Config();
		this.config.saveDefaultConfig();
		this.config.load();

		MouseScrolledInParentElementEvent.EVENT.register((ParentElement parentElement, double mouseX, double mouseY, double horizontalAmount, double verticalAmount) -> {
			MinecraftClient client = MinecraftClient.getInstance();
			if (client.currentScreen != null && this.config.enable && this.rendering) {
				if (Screen.hasShiftDown()) {
					this.horizontal += (int) verticalAmount;
					this.vertical += (int) horizontalAmount;
				} else {
					this.horizontal += (int) horizontalAmount;
					this.vertical += (int) verticalAmount;
				}
			}
			return ActionResult.PASS;
		});

		RenderMouseoverTooltipEvent.Post.EVENT.register((screen, textRenderer, itemStack, tooltip, context, x, y) -> {
			GreatScrollableTooltips.this.rendering = true;
			return ActionResult.PASS;
		});

		RenderMouseoverTooltipEvent.Miss.EVENT.register(screen -> {
			GreatScrollableTooltips.this.rendering = false;
			return ActionResult.PASS;
		});

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (client.currentScreen == null) {
				GreatScrollableTooltips.this.horizontal = 0;
				GreatScrollableTooltips.this.vertical = 0;
			}
		});

		HandledScreenKeyPressedEvent.EVENT.register(new CursorKeyListener());

		KeyBindingHelper.registerKeyBinding(GreatScrollableTooltips.KEY_BINDING_SCROLL_UP);
		KeyBindingHelper.registerKeyBinding(GreatScrollableTooltips.KEY_BINDING_SCROLL_LEFT);
		KeyBindingHelper.registerKeyBinding(GreatScrollableTooltips.KEY_BINDING_SCROLL_DOWN);
		KeyBindingHelper.registerKeyBinding(GreatScrollableTooltips.KEY_BINDING_SCROLL_RIGHT);
	}

	public static GreatScrollableTooltips getInstance() {
		return instance;
	}

	public Config getConfig() {
		return config;
	}

	public int getHorizontal() {
		return horizontal;
	}

	public void setHorizontal(int horizontal) {
		this.horizontal = horizontal;
	}

	public int getVertical() {
		return vertical;
	}

	public void setVertical(int vertical) {
		this.vertical = vertical;
	}

	public boolean isRendering() {
		return rendering;
	}

	public int getModX() {
		return this.getHorizontal() * this.getConfig().sensitivity;
	}

	public int getModY() {
		return this.getVertical() * this.getConfig().sensitivity;
	}
}