package com.electronwill.nightconfig.core.file;

import com.electronwill.nightconfig.core.Config;
import com.electronwill.nightconfig.core.ConfigFormat;
import com.electronwill.nightconfig.core.io.ParsingMode;
import com.electronwill.nightconfig.core.io.WritingMode;

import java.nio.charset.Charset;
import java.nio.file.Path;

/**
 * Builder for {@link FileConfig}.
 * <p>
 * The default settings are:
 * <ul>
 * <li>Charset: UTF-8 - change it with {@link #charset(Charset)}</li>
 * <li>WritingMode: REPLACE - change it with {@link #writingMode(WritingMode)}</li>
 * <li>ParsingMode: REPLACE - change it with {@link #parsingMode(ParsingMode)}</li>
 * <li>FileNotFoundAction: CREATE_EMPTY - change it with {@link #onFileNotFound(FileNotFoundAction)}</li>
 * <li>Asynchronous writing, ie config.save() returns quickly and operates in the background -
 * change it with {@link #sync()}</li>
 * <li>Not autosaved - change it with {@link #autosave()}</li>
 * <li>Not autoreloaded - change it with {@link #autoreload()}</li>
 * </ul>
 * <p>
 * To create a new builder, use {@link FileConfig#builder(Path)}.
 *
 * @author TheElectronWill
 */
public class FileConfigBuilder extends GenericBuilder<Config, FileConfig> {
	FileConfigBuilder(Path file, ConfigFormat<? extends Config> format) {
		super(file, format);
	}
}
