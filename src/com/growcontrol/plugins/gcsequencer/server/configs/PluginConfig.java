package com.growcontrol.plugins.gcsequencer.server.configs;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.growcontrol.common.gcCommonDefines;
import com.growcontrol.plugins.gcsequencer.PluginDefines;
import com.poixson.commonapp.config.xConfig;
import com.poixson.commonapp.config.xConfigException;


public class PluginConfig extends xConfig {

	public final String version;

	private volatile Map<String, SequenceConfig> sequenceConfigs = null;
	private final Object configLock = new Object();



	public PluginConfig(final Map<String, Object> datamap)
			throws xConfigException {
		super(datamap);
		this.version = this.getString(gcCommonDefines.CONFIG_VERSION);
	}



	// plugin version
	public String getVersion() {
		return this.version;
	}



	// sequence configs
	public Map<String, SequenceConfig> getDeviceConfigs()
			throws xConfigException {
		if(this.sequenceConfigs == null) {
			synchronized(this.configLock) {
				if(this.sequenceConfigs == null) {
					final List<xConfig> configsList =
						this.getConfigList(
								PluginDefines.CONFIG_SEQUENCES,
								SequenceConfig.class
						);
					final LinkedHashMap<String, SequenceConfig> sequencesMap =
							new LinkedHashMap<String, SequenceConfig>();
					for(final xConfig cfg : configsList) {
						final SequenceConfig s = (SequenceConfig) cfg;
						sequencesMap.put(s.getKey(), s);
					}
					this.sequenceConfigs = Collections.unmodifiableMap(sequencesMap);
				}
			}
		}
		return this.sequenceConfigs;
	}



}
