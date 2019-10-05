package com.webservice.services;

import java.io.IOException;
import java.util.Optional;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webservice.services.error.handler.ApplicationException;

/**
 * {@link=WeatherServiceServlet}} contains services related to the weather
 * service. Basically it returns weather information by city code. It is a GET
 * http request and accept cityCode as query parameter.
 * 
 * @author GGamage
 *
 */
@WebServlet(urlPatterns = "/api/weather/*")
public class WeatherServiceServlet extends HttpServlet {

	private static final long serialVersionUID = 2930660501911892007L;

	private final static Logger LOG = AppLogger.getApplicationLogger(WeatherServiceServlet.class.getName());
	
	private static final String sampleDataFilePath = "weather-sample-data.properties";

	private static final String CITY_CODE_QUERY_PARAM = "citycode";
	static Properties WEATHER_DATA_PROPS;
	static {
		WEATHER_DATA_PROPS = ApplicationUtils.loadDataFile(sampleDataFilePath)
				.orElseThrow(ExceptionInInitializerError::new);
	}

    public WeatherServiceServlet() {
        super();

    }


	/**
	 * Returns weather information by city code
	 * 
	 * @param cityCode
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			String cityCode = Optional.of(request.getParameter(CITY_CODE_QUERY_PARAM))
					.orElseThrow(IllegalArgumentException::new);
			String weatherInfoByCodeId = Optional.of(WEATHER_DATA_PROPS.getProperty(cityCode))
					.orElseThrow(RuntimeException::new);
			response.getWriter().println(weatherInfoByCodeId);

		} catch (Exception applicationEx) {
			LOG.log(Level.SEVERE, "Error on an application argument: " + applicationEx.getMessage());
			throw new ApplicationException("Error on an application argument", applicationEx);
		}

	}


}
