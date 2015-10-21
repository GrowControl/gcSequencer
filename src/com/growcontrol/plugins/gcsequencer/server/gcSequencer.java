package com.growcontrol.plugins.gcsequencer.server;

import com.growcontrol.api.serverapi.plugins.apiServerPlugin;
import com.growcontrol.plugins.gcsequencer.PluginDefines;
import com.growcontrol.plugins.gcsequencer.server.commands.Commands;
import com.growcontrol.plugins.gcsequencer.server.configs.PluginConfig;
import com.poixson.commonapp.config.xConfigLoader;
import com.poixson.commonjava.xLogger.xLog;


public class gcSequencer extends apiServerPlugin {
	public static final String LOG_NAME = "gcSequencer";

	private volatile PluginConfig config = null;



	@Override
	protected void onEnable() {
		// load config
		this.config = (PluginConfig) xConfigLoader.Load(
				PluginDefines.CONFIG_PATH,
				PluginDefines.CONFIG_FILE,
				PluginConfig.class,
				gcSequencer.class
		);
		if(this.config == null) {
			this.fail("Failed to load "+PluginDefines.CONFIG_FILE);
			return;
		}
		if(this.config.isFromResource())
			xLog.getRoot(LOG_NAME).warning("Created default "+PluginDefines.CONFIG_FILE);
		// register listeners
		this.register(new Commands());
	}



	@Override
	protected void onDisable() {
		this.unregister(Commands.class);
	}



}
