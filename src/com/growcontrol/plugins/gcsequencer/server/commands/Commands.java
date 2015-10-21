package com.growcontrol.plugins.gcsequencer.server.commands;

import com.poixson.commonjava.xEvents.annotations.xEvent;
import com.poixson.commonjava.xLogger.commands.xCommandEvent;
import com.poixson.commonjava.xLogger.commands.xCommandListener;


public class Commands implements xCommandListener {



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
