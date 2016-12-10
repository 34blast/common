/**
 * RMSCOTT Prototyping code
 */
package rmscott.logging;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import rmscott.config.ConfigurationConstants;
import rmscott.config.ConfigurationHelper;
import rmscott.util.StringValidator;

/**
 * RMSLogger
 * 
 * @author Richard M. Scott
 */

public final class RMSLogger {

	// Field descriptor #4 J
	public static final long TYPE_INFO = 1L;

	// Field descriptor #4 J
	public static final long TYPE_INFORMATION = 1L;

	// Field descriptor #4 J
	public static final long TYPE_WARN = 2L;

	// Field descriptor #4 J
	public static final long TYPE_WARNING = 2L;

	// Field descriptor #4 J
	public static final long TYPE_ERR = 4L;

	// Field descriptor #4 J
	public static final long TYPE_ERROR = 4L;

	/**
	 * The OFF level provides no logging messages.
	 */
	public static final String LEVEL_OFF = "OFF";

	/**
	 * The SEVERE level indicates a severe failure.
	 */
	public static final String LEVEL_SEVERE = "SEVERE";

	/**
	 * The WARNING level indicates a warning.
	 */
	public static final String LEVEL_WARNING = "WARNING"; //$NON-NLS-1$

	/**
	 * The INFO level indicates an informative message.
	 */
	public static final String LEVEL_INFO = "INFO"; //$NON-NLS-1$

	/**
	 * The CONFIG level indicates a static configuration message.
	 */
	public static final String LEVEL_CONFIG = "CONFIG"; //$NON-NLS-1$

	/**
	 * The FINE level provides tracing messages.
	 */
	public static final String LEVEL_FINE = "FINE"; //$NON-NLS-1$

	/**
	 * The FINER level provides more detailed tracing messages.
	 */
	public static final String LEVEL_FINER = "FINER"; //$NON-NLS-1$

	/**
	 * The FINEST level provides highly detailed tracing messages.
	 */
	public static final String LEVEL_FINEST = "FINEST"; //$NON-NLS-1$

	/**
	 * The ALL level provides all logging messages.
	 */
	public static final String LEVEL_ALL = "ALL"; //$NON-NLS-1$

	// define a RMSLogger for use by this class
	static String CLASS_NAME = RMSLogger.class.getName();

	public static java.util.logging.Logger logger = null;

	static {
		initialize();
	};

	/**
	 * Constructor for the class
	 */
	private RMSLogger() {
	}

	/**
	 * The purpose of loadQueryPropertiesFile is to load GBSql properties file
	 * 
	 * @return
	 */
	private static void initialize() {
		logger = java.util.logging.Logger.getLogger(CLASS_NAME);
		try {
			RMSLogger.setLevel(LEVEL_INFO);
			String levelString = ConfigurationHelper.getPropertyValue(ConfigurationConstants.RMS_LOG_LEVEL);
			if (!StringValidator.isEmpty(levelString)) {
				RMSLogger.setLevel(levelString);
			}
		} catch (Exception exc) {
			// do nothing
		}

	} // end of loadProperties

	/**
	 * Logs an exception in a non-static method.
	 * 
	 * @param pType
	 * @param pObject
	 * @param pMethodName
	 *            The method name where the message is being logged.
	 * @param pEx
	 *            The exception that was caught.
	 */
	public static final void logException(long pType, Object pObject, String pMethodName, Exception pEx) {

		StringBuffer sb = new StringBuffer();
		if (pObject != null) {
			sb.append(pObject.getClass().getName());
			sb.append(".");
		}
		if (!StringValidator.isEmpty(pMethodName)) {
			sb.append(pMethodName);
			sb.append("() : ");
		}
		sb.append("Exception occurred, ");
		if (pEx != null) {
			sb.append("Classname of exception = ");
			sb.append(pEx.getClass().getName());
			sb.append(StringValidator.EOL);
			sb.append(pEx.toString());
		} else {
			sb.append("no exception passed");
		}

		logger.log(Level.SEVERE, sb.toString());
	}

	/**
	 * Logs an exception in a non-static method.
	 * 
	 * @param pType
	 * @param pObject
	 * @param pMethodName
	 *            The method name where the message is being logged.
	 * @param pEx
	 *            The exception that was caught.
	 */
	@SuppressWarnings("rawtypes")
	public static final void logException(Class pClass, String pMethodName, Exception pEx) {

		StringBuffer sb = new StringBuffer();
		if (pClass != null) {
			sb.append(pClass.getName());
			sb.append(".");
		}
		if (!StringValidator.isEmpty(pMethodName)) {
			sb.append(pMethodName);
			sb.append("() : ");
		}
		sb.append("Exception occurred, ");
		if (pEx != null) {
			sb.append(pEx.getClass().getName());
			sb.append(StringValidator.EOL);
			sb.append(pEx.toString());
		} else {
			sb.append("no exception passed");
		}

		logger.log(Level.SEVERE, sb.toString());
	}

	/**
	 * Logs an exception in a static method.
	 * 
	 * @param pType
	 * @param pClassName
	 * @param pMethodName
	 *            The method name where the exception is being logged.
	 * @param pEx
	 *            The exception that was caught.
	 */
	public static final void logException(long pType, String pClassName, String pMethodName, Exception pEx) {

		StringBuffer sb = new StringBuffer();
		if (!StringValidator.isEmpty(pClassName)) {
			sb.append(pClassName);
			sb.append(".");
		}
		if (!StringValidator.isEmpty(pMethodName)) {
			sb.append(pMethodName);
			sb.append("() : ");
		}
		sb.append("Exception occurred, ");
		if (pEx != null) {
			sb.append(pEx.getClass().getName());
			sb.append(StringValidator.EOL);
			sb.append(pEx.toString());
		} else {
			sb.append("no exception passed");
		}

		logger.log(Level.SEVERE, sb.toString());
	}

	/**
	 * Logs an exception in a non-static method.
	 * 
	 * @param pObject
	 * @param pMethodName
	 *            The method name where the message is being logged.
	 * @param pEx
	 *            The exception that was caught.
	 */
	public static final void logException(Object pObject, String pMethodName, Exception pEx) {

		RMSLogger.logException(RMSLogger.TYPE_ERR, pObject, pMethodName, pEx);
	}

	/**
	 * Logs an exception in a static method.
	 * 
	 * @param pClassName
	 * @param pMethodName
	 *            The method name where the exception is being logged.
	 * @param pEx
	 *            The exception that was caught.
	 */
	public static final void logException(String pClassName, String pMethodName, Exception pEx) {

		RMSLogger.logException(RMSLogger.TYPE_ERR, pClassName, pMethodName, pEx);
	}

	/**
	 * Logs information.
	 * 
	 * @param pClassName
	 * @param pMethodName
	 * @param pMessage
	 */
	public static final void debug(String pClassName, String pMethodName, String pMessage) {

		StringBuffer sb = new StringBuffer();
		if (!StringValidator.isEmpty(pClassName)) {
			sb.append(pClassName);
			sb.append(".");
		}
		if (!StringValidator.isEmpty(pMethodName)) {
			sb.append(pMethodName);
			sb.append("() : ");
		}
		if (!StringValidator.isEmpty(pMessage)) {
			sb.append(pMessage);
		}

		logger.log(Level.FINE, sb.toString());
	}

	/**
	 * Logs information.
	 * 
	 * @param pClass
	 * @param pMethodName
	 * @param pMessage
	 */
	@SuppressWarnings("rawtypes")
	public static final void info(Class pClass, String pMethodName, String pMessage) {

		StringBuffer sb = new StringBuffer();
		if (pClass != null) {
			sb.append(pClass.getName());
			sb.append(".");
		}
		if (!StringValidator.isEmpty(pMethodName)) {
			sb.append(pMethodName);
			sb.append("() : ");
		}
		if (!StringValidator.isEmpty(pMessage)) {
			sb.append(pMessage);
		}

		logger.log(Level.INFO, sb.toString());
	}

	/**
	 * Logs information.
	 * 
	 * @param pClassName
	 * @param pMethodName
	 * @param pMessage
	 */
	public static final void info(String pClassName, String pMethodName, String pMessage) {

		StringBuffer sb = new StringBuffer();
		if (!StringValidator.isEmpty(pClassName)) {
			sb.append(pClassName);
			sb.append(".");
		}
		if (!StringValidator.isEmpty(pMethodName)) {
			sb.append(pMethodName);
			sb.append("() : ");
		}
		if (!StringValidator.isEmpty(pMessage)) {
			sb.append(pMessage);
		}

		logger.log(Level.INFO, sb.toString());
	}

	/**
	 * Logs information.
	 * 
	 * @param pClassName
	 * @param pMessage
	 */
	public static final void info(String pClassName, String pMethodName, Exception pExc) {

		StringBuffer sb = new StringBuffer();
		if (!StringValidator.isEmpty(pClassName)) {
			sb.append(pClassName);
			sb.append(".");
		}
		if (!StringValidator.isEmpty(pMethodName)) {
			sb.append(pMethodName);
			sb.append("() : ");
		}
		if (pExc != null) {
			sb.append(pExc.toString());
		}

		logger.log(Level.INFO, sb.toString());
	}

	/**
	 * Logs warning.
	 * 
	 * @param pClassName
	 * @param pMethodName
	 * @param pMessage
	 */
	public static final void warning(String pClassName, String pMethodName, String pMessage) {

		StringBuffer sb = new StringBuffer();
		if (!StringValidator.isEmpty(pClassName)) {
			sb.append(pClassName);
			sb.append(".");
		}
		if (!StringValidator.isEmpty(pMethodName)) {
			sb.append(pMethodName);
			sb.append("() : ");
		}
		if (!StringValidator.isEmpty(pMessage)) {
			sb.append(pMessage);
		}

		logger.log(Level.WARNING, sb.toString());
	}

	/**
	 * Logs warning with exception message.
	 * 
	 * @param pClass
	 * @param pMethodName
	 * @param pMessage
	 */
	@SuppressWarnings("rawtypes")
	public static final void warning(Class pClass, String pMethodName, String pMessage) {

		StringBuffer sb = new StringBuffer();
		if (pClass != null) {
			sb.append(pClass.getName());
			sb.append(".");
		}
		if (!StringValidator.isEmpty(pMethodName)) {
			sb.append(pMethodName);
			sb.append("() : ");
		}
		if (!StringValidator.isEmpty(pMessage)) {
			sb.append(pMessage);
		}

		logger.log(Level.WARNING, sb.toString());
	}

	/**
	 * Logs warning with exception message.
	 * 
	 * @param pClass
	 * @param pMethodName
	 * @param pMessage
	 * @param pException
	 */
	@SuppressWarnings("rawtypes")
	public static final void warning(Class pClass, String pMethodName, String pMessage, Exception pException) {

		StringBuffer sb = new StringBuffer();
		if (pClass != null) {
			sb.append(pClass.getName());
			sb.append(".");
		}
		if (!StringValidator.isEmpty(pMethodName)) {
			sb.append(pMethodName);
			sb.append("() : ");
		}
		if (!StringValidator.isEmpty(pMessage)) {
			sb.append(pMessage);
		}
		if (pException != null) {
			sb.append("Exception occurred, ");
			sb.append(pException.getClass().getName());
			sb.append(StringValidator.EOL);
			sb.append(pException.toString());
		} else {
			sb.append("no exception passed");
		}

		logger.log(Level.WARNING, sb.toString());
	}

	/**
	 * Logs error
	 * 
	 * @param pClass
	 * @param pMethodName
	 * @param pMessage
	 */
	public static final void error(String pClassName, String pMethodName, String pMessage) {

		StringBuffer sb = new StringBuffer();
		if (!StringValidator.isEmpty(pClassName)) {
			sb.append(pClassName);
			sb.append(".");
		}
		if (!StringValidator.isEmpty(pMethodName)) {
			sb.append(pMethodName);
			sb.append("() : ");
		}
		if (!StringValidator.isEmpty(pMessage)) {
			sb.append(pMessage);
		}

		logger.log(Level.SEVERE, sb.toString());
	}

	/**
	 * Logs warning with exception message.
	 * 
	 * @param pClass
	 * @param pMethodName
	 * @param pMessage
	 * @param pThrowable
	 */
	@SuppressWarnings("rawtypes")
	public static final void error(Class pClass, String pMethodName, String pMessage, Throwable pThrowable) {

		StringBuffer sb = new StringBuffer();
		if (pClass != null) {
			sb.append(pClass.getName());
			sb.append(".");
		}
		if (!StringValidator.isEmpty(pMethodName)) {
			sb.append(pMethodName);
			sb.append("() : ");
		}
		if (!StringValidator.isEmpty(pMessage)) {
			sb.append(pMessage);
		}
		if (pThrowable != null) {
			sb.append("Exception occurred, ");
			sb.append(pThrowable.getClass().getName());
			sb.append(StringValidator.EOL);
			sb.append(pThrowable);
		}

		logger.log(Level.SEVERE, sb.toString());
	}

	/**
	 * Logs warning with exception message.
	 * 
	 * @param pClassName
	 * @param pMethodName
	 * @param pMessage
	 * @param pException
	 */
	public static final void exception(String pClassName, String pMethodName, String pMessage, Exception pException) {

		logException(RMSLogger.TYPE_ERR, pClassName, pMethodName, pException);

	}

	/**
	 * Logs warning with exception message.
	 * 
	 * @param pClassName
	 * @param pMessage
	 * @param pException
	 */
	public static final void exception(String pClassName, String pMessage, Exception pException) {

		logException(RMSLogger.TYPE_ERR, pClassName, null, pException);

	}

	/**
	 * Gets the level
	 * 
	 * @return
	 */
	public static final String getLevel() {
		Level level = logger.getLevel();
		String levelString = level.toString();
		return levelString;
	}

	/**
	 * sets the level
	 * 
	 * @param pLevel
	 *            the new level for logging
	 */
	public static final void setLevel(String pLevel) {
		Level level = Level.parse(pLevel);
		logger.setLevel(level);

		// get the top Logger
		Logger topLogger = java.util.logging.Logger.getLogger("");
		if (topLogger == null) {
			return;
		}

		// Handler for console (reuse it if it already exists)
		Handler consoleHandler = null;
		// see if there is already a console handler
		for (Handler handler : topLogger.getHandlers()) {
			if (handler instanceof ConsoleHandler) {
				// found the console handler
				consoleHandler = handler;
				break;
			}
		}

		if (consoleHandler == null) {
			// there was no console handler found, create a new one
			consoleHandler = new ConsoleHandler();
			topLogger.addHandler(consoleHandler);
		}
		// set the console handler to fine:
		consoleHandler.setLevel(level);
	}

}
