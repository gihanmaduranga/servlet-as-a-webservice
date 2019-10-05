package com.webservice.services;

import java.io.IOException;
import java.util.Optional;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webservice.services.error.handler.ApplicationException;

/**
 * {@link=GeoLocationServiceServlet}} contains services related to the geo
 * location service. Basically it returns time zone information by zip code. It
 * is a POST http request and accept zip code in request body.
 * 
 * @author GGamage
 *
 */
@WebServlet(urlPatterns = "/api/geo-location/*")
public class GeoLocationServiceServlet extends HttpServlet {
	private static final long serialVersionUID = -2386136085572293406L;

	private final static Logger LOG = AppLogger.getApplicationLogger(GeoLocationServiceServlet.class.getName());

	private static final String sampleDataFileName = "geolocation-sample-data.properties";
	static Properties GEO_LOCATION_DATA_PROPS;
	static {
		
		GEO_LOCATION_DATA_PROPS = ApplicationUtils.loadDataFile(sampleDataFileName)
		  .orElseThrow(ExceptionInInitializerError::new);
		 
	}

    public GeoLocationServiceServlet() {
        super();

    }


	/**
	 * Returns weather information by city code
	 * 
	 * @param cityCode
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			String zipCode = request.getHeader("zipcode");
			if (zipCode == "" || zipCode == null) {
				zipCode = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
			}
			String timeZoneByZipCode = Optional.of(GEO_LOCATION_DATA_PROPS.getProperty(zipCode))
					.orElseThrow(RuntimeException::new);

			response.getWriter().println(timeZoneByZipCode);

		} catch (Exception applicationEx) {
			LOG.log(Level.SEVERE, "Error on an application argument: " + applicationEx.getMessage());
			throw new ApplicationException("Error on an application argument", applicationEx);
		}

	}



}
