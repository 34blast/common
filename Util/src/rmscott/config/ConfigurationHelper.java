/**
 * RMSCOTT Prototyping code
 */
package rmscott.config;

import java.util.Properties;

import rmscott.util.StringValidator;

/**
 * This class reads the default properties to be utilized by SOX
 * 
 * @author Richard M. Scott
 * 
 */
public final class ConfigurationHelper {

	private static String BEGIN_FILE_NAME = "rms-config_";
	private static String PROPERTIES = ".properties";
	private static String DEFAULT = "default";

	private static Properties props = null;
	private static String propertyFileName = null;

	static {
		loadPropertiesFile();
	};

	/**
	 * Constructor for the class
	 */
	private ConfigurationHelper() {
	}

	/**
	 * The purpose of loadQueryPropertiesFile is to load GBSql properties file
	 * 
	 * @return
	 */
	private static void loadPropertiesFile() {
		if (props == null) {
			props = new Properties();
		}

		try {
			propertyFileName = getPropertiesFileName();
			props.load(ConfigurationHelper.class.getClassLoader()
					.getResourceAsStream(propertyFileName));
		} catch (Exception exc) {
			System.err.println("Exception opening property file : "
					+ propertyFileName);
			System.err.println("Loading default property file");
			try {
				propertyFileName = ConfigurationHelper
						.getDefaultPropertiesFileName();
				props.load(ConfigurationHelper.class.getClassLoader()
						.getResourceAsStream(propertyFileName));
			} catch (Exception exc2) {
				System.err
						.println("Exception: Can not load default properties file");
				System.err.println(exc);
				throw new RuntimeException(exc);
			}
		}
	} // end of loadProperties

	/**
	 * The purpose of getPropertiesFileName is to get a name of server
	 * properties file
	 * 
	 * @return the name of server properties file
	 */
	public static String getServerName() {
		String serverName = System.getProperty("server.name");
		return serverName;
	}

	/**
	 * The purpose of getPropertiesFileName is to get a name of server
	 * properties file
	 * 
	 * @return the name of server properties file
	 */
	public static String getServerHostingEnv() {

		String serverHostingEnv = System.getProperty("server.hosting.env");
		return serverHostingEnv;
	}

	/**
	 * The purpose of getPropertiesFileName is to get a name of server
	 * properties file
	 * 
	 * @return the name of server properties file
	 */
	public static String getPropertiesFileName() {
		if (propertyFileName == null) {
			String serverName = ConfigurationHelper.getServerName();

			if (StringValidator.isEmpty(serverName)) {
				StringBuffer sb = new StringBuffer();
				sb.append("ConfigurationHelper.getPropertiesFileName() : ");
				sb.append("JVM Does not define -Dserver.name=xxxx,  ");
				sb.append("or server.name in customer properties of JVM");
				System.err.println(sb.toString());
				serverName = DEFAULT;
			}
			StringBuffer sb = new StringBuffer(
					ConfigurationHelper.BEGIN_FILE_NAME);
			sb.append(serverName);
			sb.append(PROPERTIES);
			propertyFileName = sb.toString();
		}
		return propertyFileName;
	}

	/**
	 * The purpose of getAttemptedPropertiesFileName is to get a name of server
	 * properties file
	 * 
	 * @return the name of server properties file
	 */
	public static String getAttemptedPropertiesFileName() {
		String serverName = ConfigurationHelper.getServerName();

		StringBuffer sb = new StringBuffer(ConfigurationHelper.BEGIN_FILE_NAME);
		sb.append(serverName);
		sb.append(PROPERTIES);
		propertyFileName = sb.toString();
		return sb.toString();
	}

	/**
	 * The purpose of getPropertiesFileName is to get a name of server
	 * properties file
	 * 
	 * @return the name of server properties file
	 */
	public static String getDefaultPropertiesFileName() {
		StringBuffer sb = new StringBuffer(ConfigurationHelper.BEGIN_FILE_NAME);
		sb.append(DEFAULT);
		sb.append(PROPERTIES);

		return sb.toString();
	}

	/**
	 * Gets a value for the given key
	 * 
	 * @param pKey
	 * @return String containing the value for the key
	 */
	public static String getPropertyValue(String pKey) {
		String property = props.getProperty(pKey);
		return property;
	}

	/**
	 * Gets a value for the given key
	 * 
	 * @param pKey
	 * @return boolean
	 */
	public static boolean getPropertyValueAsBoolean(String pKey) {
		boolean returnValue = false;
		String property = props.getProperty(pKey);
		if (!StringValidator.isEmpty(property)) {
			try {
				Boolean bool = new Boolean(property);
				returnValue = bool.booleanValue();
			} catch (Exception exc) {
				// eat it since not found
			}
		}
		return returnValue;
		
	} // end of getPropertyValueAsBoolean

	/**
	 * Gets a value for the given key
	 * 
	 * @param pKey
	 * @return int
	 */
	public static int getPropertyValueAsInt(String pKey) {
		int returnValue = 0;
		String property = props.getProperty(pKey);
		if (!StringValidator.isEmpty(property)) {
			try {
				Integer integer = new Integer(property);
				returnValue = integer.intValue();
			} catch (Exception exc) {
				// eat it since not found
			}
		}
		return returnValue;
		
	} // end of getPropertyValueAsBoolean

}
