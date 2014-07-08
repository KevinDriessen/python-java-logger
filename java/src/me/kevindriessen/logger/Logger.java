/* Copyright (c) 2014 Kevin Driessen, see included LICENSE.txt
 *
 * Basic Java logger
 *
 * This logger is a simple utility class for outputting messages with different 
 * levels of severity to the console.
 *
 * ==============================================================================
 * U S A G E   G U I D E
 * ==============================================================================
 * The setSeverity function allows you to set the severity level. The severity
 * level defines what messages should be printed out and which should not be
 * printed out. E.G. if the severity is set to 'w', short for 'WARN' only
 * messages with a severity of 'w' or 'e' get printed out.
 *
 * The first log function is the actual function that logs messages to the
 * console. The first parameter is a char value that represents the severity.
 * It's highly recommanded to use the severity constants from this module instead
 * of just filling in a char. The second parameter is the message that will be
 * printed out in the following format: [<Severity>] <Message>
 *
 * The second log function does the same as the first one, but requires only the
 * message parameter. It just calls the first log function with the 'Debug'
 * severity.
 */
package me.kevindriessen.logger;

import java.util.HashMap;
import java.util.Map;

public final class Logger {
	// Severity constants
	public static final char DEBUG = 'd';
	public static final char WARN = 'w';
	public static final char ERR = 'e';
	
	// Private variables
	private static Map<Character, Object[]> severities = new HashMap<Character, Object[]>();
	private static char currentSeverity = WARN; // Default severity is 'WARN'.
	
	static {
		severities.put('d', new Object[] {"DEBUG", 0});
		severities.put('w', new Object[] {"WARNING", 1});
		severities.put('e', new Object[] {"ERROR", 2});
	}
	
	/**
	 * Set's the current severity level for logging.
	 */
	public static void setSeverity(char newSeverity) {
		if (severities.containsKey(newSeverity)) {
			currentSeverity = newSeverity;
		} else {
			System.out.println(String.format("[LOGGER] Unknown severity \"%s\"", newSeverity));
		}
	}
	
	/**
	 * Returns true if the severity is at the same or higher level then the
	 * current severity, otherwise returns false.
	 */
	private static boolean checkSeverity(char severity) {
		return (int) severities.get(severity)[1] >= (int) severities.get(currentSeverity)[1];
	}
	
	/**
	 * Logs a message with a specified severity. If the severity level is lower
	 * then the current severity of the logger it will not be printed out.
	 */
	public static void log(char severity, String message) {
		if (checkSeverity(severity)) {
			System.out.println(String.format("[%s] %s", severities.get(severity)[0], message));
		}
	}
	
	/**
	 * Logs a message with the 'Debug' severity.
	 */
	public static void log(String message) {
		log(DEBUG, message);
	}
	
	/**
	 * Running a quick test, if the 'Debug' test does not print out it's succesfull.
	 */
	public static void main(String... args) {
	    log(DEBUG, "Debug test");
	    log(WARN, "Warning test");
	    log(ERR, "Error test");
	}

}
