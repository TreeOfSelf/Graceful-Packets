package me.treeofself.gracefulpackets.config;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class GracefulPacketsConfigScreen {
	public static Screen getConfigScreen(Screen parent) {
		ConfigBuilder builder = ConfigBuilder.create()
			.setParentScreen(parent)
			.setTitle(Text.literal("Graceful Packets"))
			.setSavingRunnable(GracefulPacketsConfig::save);

		ConfigCategory general = builder.getOrCreateCategory(Text.literal("Settings"));
		ConfigEntryBuilder entryBuilder = builder.getEntryBuilder();

		general.addEntry(entryBuilder.startBooleanToggle(
				Text.literal("Suppress Errors"),
				GracefulPacketsConfig.SUPPRESS_ERRORS
			)
			.setDefaultValue(true)
			.setSaveConsumer(value -> GracefulPacketsConfig.SUPPRESS_ERRORS = value)
			.setTooltip(Text.literal("Suppress packet and registry errors instead of crashing"))
			.build());

		general.addEntry(entryBuilder.startBooleanToggle(
				Text.literal("Log Errors to Chat"),
				GracefulPacketsConfig.LOG_TO_CHAT
			)
			.setDefaultValue(false)
			.setSaveConsumer(value -> GracefulPacketsConfig.LOG_TO_CHAT = value)
			.setTooltip(Text.literal("Display suppressed errors in in-game chat (always logged to console)"))
			.build());

		return builder.build();
	}
}
