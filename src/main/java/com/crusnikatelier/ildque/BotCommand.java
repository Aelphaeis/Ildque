package com.crusnikatelier.ildque;

import org.apache.commons.cli.OptionGroup;

public interface BotCommand {
	String getName();
	OptionGroup getOptions();
	void execute(String[] argv);
}
