package com.growcontrol.plugins.gcsequencer.server.commands;

import com.poixson.commonjava.xEvents.annotations.xEvent;
import com.poixson.commonjava.xLogger.commands.xCommandEvent;
import com.poixson.commonjava.xLogger.commands.xCommandListener;


public class Commands implements xCommandListener {
	private static final String LISTENER_NAME = "gcSequencerCommands";



	@Override
	public String getName() {
		return LISTENER_NAME;
	}
	@Override
	public String toString() {
		return this.getName();
	}



	// plugin commands
	@Override
	@xEvent(
			priority=ListenerPriority.NORMAL,
//			async=false,
			filterHandled=true,
			filterCancelled=true)
	public void onCommand(final xCommandEvent event) {

	}



}
