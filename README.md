Technology Used
===================
Java 8

Tomcat server 8.5

Dynamic web module 3.1

This project contains two web services which are created using Java servlet

API Specification
==================
[GET] Get weather information by city code

		Eg: http://localhost:8080/servlet-as-a-webservice/api/weather/?citycode=32723
		
Weather service endpoint accepts citycode in query parameter.Available city codes can be found at weather-sample-data.properties file.

[POST] Get time zone information by zip code

		Eg: http://localhost:8080/servlet-as-a-webservice/api/geo-location/	
		
Time zone service endpoint accepts zip code in request body.Available zip codes can be found at geolocation-sample-data.properties.

