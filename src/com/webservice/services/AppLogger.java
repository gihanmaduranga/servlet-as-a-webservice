


package com.webservice.services;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import com.webservice.services.error.handler.ApplicationException;

/**
 * GGamage
 * 
 * {@link AppLogger} Responsible for handling application logs
 *
 */

public class AppLogger {

	static FileHandler fileHandler;

	public static Logger getApplicationLogger(String classType) {
		Logger logger = Logger.getLogger(classType);
		

			try {
			Path logFilePath = Paths.get(System.getProperty("java.io.tmpdir")).resolve("application-logs.log");
			System.out.println("APPLICATION LOG FILE PATH LOCATION:" + logFilePath);
			if (!logFilePath.toFile().exists())
				logFilePath.toFile().createNewFile();

			fileHandler = new FileHandler(logFilePath.toString());
				
				fileHandler.setFormatter(new SimpleFormatter());
				logger.addHandler(fileHandler);
			} catch (SecurityException | IOException loggerEx) {

				throw new ApplicationException(loggerEx.getMessage(), loggerEx);
			}
		return logger;
	}

}


