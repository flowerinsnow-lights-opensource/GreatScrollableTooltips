package online.flowerinsnow.greatscrollabletooltips.provider;

import java.io.InputStream;
import java.nio.file.Path;

public interface ModEnvironmentProvider {
    InputStream getDefaultConfigAsStream();

    Path getConfigFile();

    void crash(Throwable throwable, String msg);
}
