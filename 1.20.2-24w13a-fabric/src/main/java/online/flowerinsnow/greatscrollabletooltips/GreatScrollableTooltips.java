package online.flowerinsnow.greatscrollabletooltips;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.crash.CrashReport;
import online.flowerinsnow.greatscrollabletooltips.config.GreatScrollableTooltipsConfig;
import online.flowerinsnow.greatscrollabletooltips.event.HandledScreenKeyPressedEvent;
import online.flowerinsnow.greatscrollabletooltips.event.MouseScrolledInScreenEvent;
import online.flowerinsnow.greatscrollabletooltips.event.RenderTooltipEvent;
import online.flowerinsnow.greatscrollabletooltips.listener.ScreenKeyPressedListener;
import online.flowerinsnow.greatscrollabletooltips.object.ScrollSession;
import online.flowerinsnow.greatscrollabletooltips.provider.ModEnvironmentProvider;
import org.lwjgl.glfw.GLFW;

import java.io.InputStream;
import java.nio.file.Path;

@Environment(EnvType.CLIENT)
public class GreatScrollableTooltips implements ClientModInitializer {
	public static final String MODID = "great-scrollable-tooltips";

	private static GreatScrollableTooltips instance;

	private GreatScrollableTooltipsConfig config;

	private ScrollSession<ItemStack> scrollSession;

	public static final KeyBinding KEY_BINDING_SCROLL_UP = new KeyBinding("great-scrollable-tooltips.key-binding.scroll-up", GLFW.GLFW_KEY_UP, "great-scrollable-tooltips.key-binding.category");
	public static final KeyBinding KEY_BINDING_SCROLL_LEFT = new KeyBinding("great-scrollable-tooltips.key-binding.scroll-left", GLFW.GLFW_KEY_LEFT, "great-scrollable-tooltips.key-binding.category");
	public static final KeyBinding KEY_BINDING_SCROLL_DOWN = new KeyBinding("great-scrollable-tooltips.key-binding.scroll-down", GLFW.GLFW_KEY_DOWN, "great-scrollable-tooltips.key-binding.category");
	public static final KeyBinding KEY_BINDING_SCROLL_RIGHT = new KeyBinding("great-scrollable-tooltips.key-binding.scroll-right", GLFW.GLFW_KEY_RIGHT, "great-scrollable-tooltips.key-binding.category");

	@Override
	public void onInitializeClient() {
		GreatScrollableTooltips.instance = this;
		this.scrollSession = new ScrollSession<>();

		this.initConfig();
		this.initListeners();
		this.initKeyBindings();
	}

	private void initConfig() {
		this.config = new GreatScrollableTooltipsConfig(new ModEnvironmentProvider() {
			@Override
			public InputStream getDefaultConfigAsStream() {
				return GreatScrollableTooltips.class.getResourceAsStream("/config.toml");
			}

			@Override
			public Path getConfigFile() {
				return FabricLoader.getInstance().getConfigDir().resolve(GreatScrollableTooltips.MODID + ".toml");
			}

			@Override
			public void crash(Throwable throwable, String msg) {
				MinecraftClient.getInstance().printCrashReport(CrashReport.create(throwable, msg));
			}
		});
		this.config.saveDefaultConfig();
		this.config.load();
	}

	private void initListeners() {
		MouseScrolledInScreenEvent.EVENT.register((screen, mouseX, mouseY, horizontalAmount, verticalAmount) -> {
			MinecraftClient client = MinecraftClient.getInstance();
			GreatScrollableTooltips instance = GreatScrollableTooltips.this;
			ScrollSession<ItemStack> session = instance.getScrollSession();
			if (client.currentScreen != null && instance.config.enable && session.isRendering()) {
				if (!Screen.hasShiftDown()) {
					session.addHorizontal((int) horizontalAmount);
					session.addVertical((int) verticalAmount);
				} else {
					session.addHorizontal((int) verticalAmount);
					session.addVertical((int) horizontalAmount);
				}
			}
			return ActionResult.PASS;
		});

		RenderTooltipEvent.Post.EVENT.register((screen, context, textRenderer, itemStack, x, y) -> {
			ScrollSession<ItemStack> session = GreatScrollableTooltips.this.getScrollSession();
			session.setRendering(true);
			if (itemStack != session.getLastItemStackRendered()) {
				session.setLastItemStackRendered(itemStack);

				if (GreatScrollableTooltips.this.config.autoReset) {
					session.resetScroll();
				}
			}
			return ActionResult.PASS;
		});

		RenderTooltipEvent.Miss.EVENT.register((screen, context, textRenderer, x, y) -> {
			ScrollSession<ItemStack> session = GreatScrollableTooltips.this.getScrollSession();
			session.setRendering(false);
			session.setLastItemStackRendered(null);
			if (GreatScrollableTooltips.this.config.autoReset) {
				session.resetScroll();
			}
			return ActionResult.PASS;
		});

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (client.currentScreen == null) {
				GreatScrollableTooltips.this.getScrollSession().resetScroll();
			}
		});

		HandledScreenKeyPressedEvent.EVENT.register(new ScreenKeyPressedListener());
	}

	private void initKeyBindings() {
		KeyBindingHelper.registerKeyBinding(GreatScrollableTooltips.KEY_BINDING_SCROLL_UP);
		KeyBindingHelper.registerKeyBinding(GreatScrollableTooltips.KEY_BINDING_SCROLL_LEFT);
		KeyBindingHelper.registerKeyBinding(GreatScrollableTooltips.KEY_BINDING_SCROLL_DOWN);
		KeyBindingHelper.registerKeyBinding(GreatScrollableTooltips.KEY_BINDING_SCROLL_RIGHT);
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