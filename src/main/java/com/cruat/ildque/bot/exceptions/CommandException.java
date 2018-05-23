package com.cruat.ildque.bot.exceptions;

/**
 * This exception is thrown when a command cannot executed as expected.
 * The message in this message will be communicated to the end user.
 * @author morain
 *
 */
public class CommandException extends IldqueException{

	private static final long serialVersionUID = 1L;

	public CommandException(String msg) {
		super(msg);
	}

}
