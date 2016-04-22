package edu.brown.cs.finalproject.search;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class PublicFBEventsFinder {
	
	public PublicFBEventsFinder() throws Exception {
		Process p;
		File file = new File(System.getProperty("user.dir")
				+ "/lib/node_modules/facebook-events-by-location");
		p = Runtime.getRuntime().exec("npm start", null, file);

	}
	
	public void requestEvents(double latitude, double longitude, int radius, String accessToken) throws IOException {
		
		System.out.println("entered here");
		
		StringBuilder urlBuilder = new StringBuilder();
		urlBuilder.append("http://localhost:3000/events?lat=");
		urlBuilder.append(latitude);
		urlBuilder.append("&lng=");
		urlBuilder.append(longitude);
		urlBuilder.append("&distance=");
		urlBuilder.append(radius);
		urlBuilder.append("&sort=time&access_token=");
		urlBuilder.append(accessToken);
		
		System.out.println(urlBuilder.toString());
		
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		httpConn.setRequestMethod("GET");
		
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(httpConn.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		//print result
		System.out.println(response.toString());
		System.out.println("Done");
	}

}
