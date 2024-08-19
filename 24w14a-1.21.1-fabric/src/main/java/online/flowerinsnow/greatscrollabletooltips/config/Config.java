package online.flowerinsnow.greatscrollabletooltips.config;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.crash.CrashReport;
import online.flowerinsnow.fnml4j.api.node.ObjectNode;
import online.flowerinsnow.fnml4j.api.node.StringNode;
import online.flowerinsnow.fnml4j.api.parser.present.FNML4JPresentParser;
import online.flowerinsnow.fnml4j.core.FNML4J;
import online.flowerinsnow.greatscrollabletooltips.GreatScrollableTooltips;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.function.BiConsumer;

@Environment(EnvType.CLIENT)
public class Config {
    private ObjectNode rootNode;

    public boolean enable;
    public int sensitivity;
    public boolean autoReset;

    public void saveDefaultConfig() {
        BiConsumer<Throwable, String> crashFunction = crashFunction();
        Path configFile = getConfigFile();
        File file = configFile.toFile();
        if (!file.exists()) {
            try {
                //noinspection ResultOfMethodCallIgnored
                file.createNewFile();
                int len;
                byte[] bytes = new byte[1024];
                try (InputStream in = GreatScrollableTooltips.class.getResourceAsStream("/config.conf")) {
                    try (FileOutputStream fos = new FileOutputStream(file)) {
                        //noinspection DataFlowIssue
                        while ((len = in.read(bytes)) != -1) {
                            fos.write(bytes, 0, len);
                        }
                    }
                }
            } catch (IOException e) {
                crashFunction.accept(e, "Failed to save default config.");
            }
        }
    }

    public void load() {
        BiConsumer<Throwable, String> crashFunction = crashFunction();
        try {
            this.rootNode = FNML4J.parse(getConfigFile(), StandardCharsets.UTF_8);
            this.enable = Boolean.parseBoolean(this.rootNode.getChildNode("enable", FNML4JPresentParser.STRING, StringNode.class));
            this.sensitivity = this.rootNode.getChildNodeNotNull("sensitivity", FNML4JPresentParser.INT, StringNode.class);
            this.autoReset = Boolean.parseBoolean(this.rootNode.getChildNode("auto_reset", FNML4JPresentParser.STRING, StringNode.class));
        } catch (IOException e) {
            crashFunction.accept(e, "Failed to load config.");
        }
    }

    public void save() {
        BiConsumer<Throwable, String> crashFunction = crashFunction();
        try (FileWriter fw = new FileWriter(getConfigFile().toFile(), StandardCharsets.UTF_8)) {
            this.rootNode.set("enable", new StringNode(Boolean.toString(this.enable)));
            this.rootNode.set("sensitivity", new StringNode(Integer.toString(this.sensitivity)));
            this.rootNode.set("auto_reset", new StringNode(Boolean.toString(this.autoReset)));
            this.rootNode.writeRoot(0, fw);
        } catch (IOException e) {
            crashFunction.accept(e, "Failed to save config.");
        }
    }

    public Path getConfigFile() {
        return FabricLoader.getInstance().getConfigDir().resolve(GreatScrollableTooltips.MODID + ".conf");
    }

    private BiConsumer<Throwable, String> crashFunction() {
        return (e, msg) -> MinecraftClient.getInstance().printCrashReport(CrashReport.create(e, msg));
    }
}
