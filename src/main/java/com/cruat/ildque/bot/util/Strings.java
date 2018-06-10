package com.cruat.ildque.bot.util;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class Strings {
	/**
	 * Checks to see if an a string is null or empty.
	 * @param value
	 * @return
	 */
	public static boolean isBlank(String value){
		return (value == null || value.trim().isEmpty());
	}
	/**
	 * Crack a command line.
	 * @param toProcess the command line to process.
	 * @return the command line broken into strings.
	 * An empty or null toProcess parameter results in a zero sized array.
	 */
	public static String[] translateCommandline(String toProcess) {
		return new CommandTranslator(toProcess).translate();
	}
	
	
	private static class CommandTranslator {
		enum ParseState{
			NORMAL,
			SINGLE,
			DOUBLE;
		}
		final String input;

		ArrayList<String> result = new ArrayList<>();
		
		StringTokenizer tok;
		StringBuilder curr ;
		ParseState state;
		boolean quoted;
		
		public CommandTranslator(String i) {
			tok = new StringTokenizer(isBlank(i)? "" : i, "\"' ", true);
			curr = new StringBuilder();
			state = ParseState.NORMAL;
			quoted = false;
			input = i;
		}
		
		public String[] translate() {
			return isBlank(input)? new String[0] : parse();
		}
		
		String[] parse() {
			
			while(tok.hasMoreTokens()) {
				String next = tok.nextToken();
				switch(state) {
					case SINGLE:
						handleQuote(next,"'");
						break;
					case DOUBLE:
						handleQuote(next,"\"");
						break;
					default:
						handleNoQuote(next);
						break;
				}
			}
			if(quoted || curr.length() != 0) {
				result.add(curr.toString());
			}
			validateResult();
		
			return result.toArray(new String[result.size()]);
		}
		
		void handleQuote(String token, String expect) {
			if(expect.equals(token)) {
				quoted = true;
				state = ParseState.NORMAL;
			}
			else {
				curr.append(token);
			}
		}
		
		void handleNoQuote(String token) {
			if ("\'".equals(token)) {
				state = ParseState.SINGLE;
			} else if ("\"".equals(token)) {
				state = ParseState.DOUBLE;
			} else if (" ".equals(token)) {
				if (quoted || curr.length() != 0) {
					result.add(curr.toString());
					curr.setLength(0);
				}
			} else {
				curr.append(token);
			}
			quoted = false;
		}
		
		void validateResult() {
			if(state != ParseState.NORMAL) {
				String err = "unbalance quotes in " + input;
				throw new IllegalArgumentException(err);
			}
		}
	}
	
	
	private Strings() {
		//utility class
	}
}
