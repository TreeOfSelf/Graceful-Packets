package me.treeofself.gracefulpackets.config;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GracefulPacketsConfig {
	public static boolean SUPPRESS_ERRORS = true;
	public static boolean LOG_TO_CHAT = false;
	private static final String CONFIG_FILE = "graceful-packets.conf";

	public static void load() {
		Path configPath = Paths.get(CONFIG_FILE);
		if (Files.exists(configPath)) {
			try {
				String content = Files.readString(configPath);
				for (String line : content.split("\n")) {
					line = line.trim();
					if (line.isEmpty() || line.startsWith("#")) {
						continue;
					}
					if (line.startsWith("suppress_errors=")) {
						SUPPRESS_ERRORS = Boolean.parseBoolean(line.substring(16));
					} else if (line.startsWith("log_to_chat=")) {
						LOG_TO_CHAT = Boolean.parseBoolean(line.substring(12));
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			save();
		}
	}

	public static void save() {
		try {
			String content = "# Graceful Packets Configuration\n" +
					"# suppress_errors: Suppress packet decode errors (true/false)\n" +
					"# log_to_chat: Log suppressed errors to in-game chat (true/false)\n\n" +
					"suppress_errors=" + SUPPRESS_ERRORS + "\n" +
					"log_to_chat=" + LOG_TO_CHAT + "\n";
			Files.writeString(Paths.get(CONFIG_FILE), content);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
