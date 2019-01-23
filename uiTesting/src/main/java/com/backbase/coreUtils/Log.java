package com.backbase.coreUtils;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.SimpleLayout;
public class Log {

	//Initialize Log4j logs
	private static String log4jPropertiesFilePath = "./src/main/resources/log4j.properties";

	static {
		try {
			initLog4j();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void initLog4j() throws Exception {
		try {
			PropertyConfigurator.configure(log4jPropertiesFilePath);
		} catch (Exception e) {
			throw new Exception("Error configuring log4j: " + e.getMessage());
		}
	}


	private static Logger Log = Logger.getLogger(Log.class.getName());


	// This is to print log for the beginning of the test case, as we usually run so many test cases as a test suite

	public static void startTestCase(String sTestCaseName){

		Log.info("****************************************************************************************");

		Log.info("****************************************************************************************");

		Log.info("$$$$$$$$$$$$$$$$$$$$$                 "+sTestCaseName+ "       $$$$$$$$$$$$$$$$$$$$$$$$$");

		Log.info("****************************************************************************************");

		Log.info("****************************************************************************************");

	}

	//This is to print log for the ending of the test case

	public static void endTestCase(String sTestCaseName){

		Log.info("XXXXXXXXXXXXXXXXXXXXXXX             "+"-E---N---D-"+"             XXXXXXXXXXXXXXXXXXXXXX");

		Log.info("X");

		Log.info("X");

		Log.info("X");

		Log.info("X");

	}

	// Need to create these methods, so that they can be called  

	public static void info(String message) {

		Log.info(message);

	}

	public static void warn(String message) {

		Log.warn(message);

	}

	public static void error(String message) {

		Log.error(message);

	}

	public static void fatal(String message) {

		Log.fatal(message);

	}

	public static void debug(String message) {

		Log.debug(message);

	}

}