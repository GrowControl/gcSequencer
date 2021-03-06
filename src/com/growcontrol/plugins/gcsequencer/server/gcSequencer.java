package com.growcontrol.plugins.gcsequencer.server;

import com.growcontrol.api.serverapi.plugins.apiServerPlugin;
import com.growcontrol.plugins.gcsequencer.PluginDefines;
import com.growcontrol.plugins.gcsequencer.server.commands.Commands;
import com.growcontrol.plugins.gcsequencer.server.configs.PluginConfig;
import com.poixson.commonapp.config.xConfig;
import com.poixson.commonapp.config.xConfigException;
import com.poixson.commonjava.xLogger.xLog;


public class gcSequencer extends apiServerPlugin {
	public static final String LOG_NAME = "gcSequencer";

	private volatile PluginConfig config = null;



	@Override
	protected void onEnable() {
		// load config
		try {
			this.config = (PluginConfig) xConfig.Load(
					getLogger(),
					PluginDefines.CONFIG_PATH,
					PluginDefines.CONFIG_FILE,
					PluginConfig.class,
					gcSequencer.class
			);
		} catch (xConfigException e) {
			this.log().trace(e);
			this.config = null;
		}
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



	// logger
	private static volatile xLog _log = null;
	public static xLog getLogger() {
		if(_log == null)
			_log = xLog.getRoot(LOG_NAME);
		return _log;
	}
	public static xLog getLogger(final String name) {
		return getLogger().get(name);
	}



}
