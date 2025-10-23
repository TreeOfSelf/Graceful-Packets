package me.treeofself.gracefulpackets.client;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.treeofself.gracefulpackets.config.GracefulPacketsConfigScreen;

public class ModMenuIntegration implements ModMenuApi {
	@Override
	public ConfigScreenFactory<?> getModConfigScreenFactory() {
		return GracefulPacketsConfigScreen::getConfigScreen;
	}
}
