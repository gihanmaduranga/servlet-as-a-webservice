package com.webservice.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.webservice.services.error.handler.ApplicationException;

/**
 * @author GGamage
 * 
 *         {@link ApplicationUtils} provides utility methods relates to service
 *         GGamage
 *
 */
public class ApplicationUtils {
	private final static Logger LOG = AppLogger.getApplicationLogger(ApplicationUtils.class.getName());
	public final static String ERROR_STATUS_CODE = "javax.servlet.error.status_code";
	public final static String ERROR_MESSAGE = "javax.servlet.error.message";

	/**
	 * Load weather sample data file
	 * 
	 * @param dataFileName
	 * @return Properties file object
	 */
	public static Optional<Properties> loadDataFile(String dataFileName) {


		try (InputStream input = ApplicationUtils.class.getResourceAsStream(dataFileName)) {

			Properties propertyFile = new Properties();

			if (Objects.isNull(input)) {
				LOG.info("InputStream is null");
				throw new IllegalArgumentException("InputStream cannot be empty");
			}

			propertyFile.load(input);

			return Optional.of(propertyFile);

		} catch (IOException parserEx) {
			LOG.log(Level.SEVERE, "Error occurred while reading the property file");
			throw new ApplicationException("Error occurred while reading the property file", parserEx);
		}
	}
}


