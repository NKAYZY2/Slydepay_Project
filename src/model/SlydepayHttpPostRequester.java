package model;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class SlydepayHttpPostRequester {

	public static String ListPayOptionsUrlCreator(Order order, String apiurl) {
		// This method takes in dyanmic data, creates the final postUrl and then
		// returns a string value of the full link
		StringBuilder Url = new StringBuilder(apiurl);
		String questionmark = "?";
		String ampersand = "&";

		String newEmailaddition = questionmark + "emailOrMobileNumber=" + order.getMerchantEmail();
		String newapikeyaddition = ampersand + "merchantKey=" + order.getMerchantKey();

		Url.append(newEmailaddition + newapikeyaddition);

		return Url.toString();
	}

	public static HttpResponse makePostRequest2(String convertedUrl, Order order) {

		// This makePostRequest method actually sends the post parameters
		// expected by the API in Raw Json, using the Apache Http client.

		String input;
		HttpResponse response = null;
		HttpClient httpClient = HttpClientBuilder.create().build();
		// Initialization of Global variables

		try {

			HttpPost request = new HttpPost(convertedUrl);

			JSONObject jsonObj = new JSONObject();
			jsonObj.put("emailOrMobileNumber", order.getMerchantEmail());
			jsonObj.put("merchantKey", order.getMerchantKey());

			System.out.println("created json object already"); // debug
																// information

			input = jsonObj.toString().toString();

			StringEntity entity = new StringEntity(input, ContentType.APPLICATION_JSON); //this aids to make a Raw Json request to the API

			request.setEntity(entity);

			response = httpClient.execute(request);

			System.out.println(response.getStatusLine().getStatusCode()); // debug
																			// information

			System.out.println("Request processed");

		} catch (Exception ex) {

			ex.printStackTrace();

		} finally {
//			httpClient.getConnectionManager().shutdown();
		}
		return response;
	}

	public static HttpURLConnection makePostRequest(String convertedPostUrl, Order order) {
		String input = "";
		HttpURLConnection conn = null;

		URL apiPostUrl = null;

		try {

			apiPostUrl = new URL(convertedPostUrl);
			conn = (HttpURLConnection) apiPostUrl.openConnection();

			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");

			conn.getResponseCode();

			// Creating JSON object to be sent to api as request for response.
			try {
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("emailOrMobileNumber", order.getMerchantEmail());
				jsonObj.put("merchantKey", order.getMerchantKey());

				System.out.println("created json object already");

				input = jsonObj.toString().toString();

				System.out.println(input);

				OutputStream os = conn.getOutputStream();
				os.write(input.getBytes());
				os.flush();

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (Exception e) {
			System.err.println("Connection cannot be created");
		}
		conn.disconnect();
		return conn;
	}


}
