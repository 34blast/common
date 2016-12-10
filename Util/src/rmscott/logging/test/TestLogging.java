/**
 * RMSCOTT Prototyping code
 */
package rmscott.logging.test;

import rmscott.logging.RMSLogger;

/**
 * @author rmscott
 *
 */
public class TestLogging {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("TestLogging.main(): entry");
		RMSLogger.debug("TestLogging", "main", "debug  message 1");
		RMSLogger.debug("TestLogging", "main", "debug  message 2");
		RMSLogger.info("TestLogging", "main", "info message");
		RMSLogger.warning("TestLogging", "main", "warning message");
		RMSLogger.error("TestLogging", "main", "error message");
		RMSLogger.exception("TestLogging", "main", "exception message", null);
		System.out.println("TestLogging.main(): exit");

	}

}
