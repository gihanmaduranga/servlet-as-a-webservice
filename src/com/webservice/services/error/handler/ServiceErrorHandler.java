package com.webservice.services.error.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webservice.services.ApplicationUtils;

/**
 * {@link ServiceErrorHandler} responsible for handling application exceptions
 */
@WebServlet(urlPatterns = "/serviceErrorHandler")
public class ServiceErrorHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ServiceErrorHandler() {
        super();

    }


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html; charset=utf-8");
		try (PrintWriter writer = response.getWriter()) {
			writer.write("<html><head><title>Application Error</title></head><body>");
			writer.write("<h2>Application Error Details</h2>");
			writer.write("<ul>");
			Arrays.asList(ApplicationUtils.ERROR_STATUS_CODE, ApplicationUtils.ERROR_MESSAGE)
					.forEach(attr -> writer.write("<li>" + attr + ":" + request.getAttribute(attr) + " </li>"));
			writer.write("</ul>");
			writer.write("</html></body>");
		}
	}



}
