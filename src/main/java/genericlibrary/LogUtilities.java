package genericlibrary;

import org.apache.log4j.Logger;

public class LogUtilities {

	private LogUtilities() {
		throw new IllegalStateException("Utility class");
	}

	// Initialize Log4j logs
	private static Logger log = Logger.getLogger(LogUtilities.class.getName());

	public static void info(String message) {
		log.info(message);
	}

	public static void warn(String message) {
		log.warn(message);
	}

	public static void error(String message) {
		log.error(message);
	}

	public static void fatal(String message) {
		log.fatal(message);
	}

	public static void debug(String message) {
		log.debug(message);
	}
}
