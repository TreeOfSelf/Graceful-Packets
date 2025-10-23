package me.treeofself.gracefulpackets;

import me.treeofself.gracefulpackets.config.GracefulPacketsConfig;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GracefulPackets implements ModInitializer {
	public static final String MOD_ID = "graceful-packets";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		GracefulPacketsConfig.load();
		LOGGER.info("Graceful Packets mod loaded");
		LOGGER.info("Suppress Errors: {}", GracefulPacketsConfig.SUPPRESS_ERRORS);
		LOGGER.info("Log to Chat: {}", GracefulPacketsConfig.LOG_TO_CHAT);
	}
}