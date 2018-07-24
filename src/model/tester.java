package model;

import java.io.IOException;
import org.json.JSONException;
import org.json.simple.parser.ParseException;

public class tester {

	public static void main(String[] args)
			throws UnsupportedOperationException, IOException, ParseException, JSONException {
		// TODO Auto-generated method stub

//		Order newOrder = new Order();
//		newOrder.setMerchantEmail("futureleaders22@gmail.com");
//		newOrder.setMerchantKey("1529068023032");
//		String url = "https://app.slydepay.com/api/merchant/invoice/payoptions";
//		String output = "";
//		String answer = SlydepayHttpPostRequester.ListPayOptionsUrlCreator(newOrder, url);
//
//		HttpResponse response2 = SlydepayHttpPostRequester.makePostRequest2(answer, newOrder);
//
//		// Reading Json Response
//
//		BufferedReader rd = new BufferedReader(new InputStreamReader(response2.getEntity().getContent()));
//
//		StringBuilder sb = new StringBuilder(output);
//
//		System.out.println("Output from Server .... \n");
//
//		try {
//			while ((output = rd.readLine()) != null) {
//
//				
//				sb.append(output);
//				System.out.println(output);
//			}
//
//		} catch (Exception e) {
//
//		}
//		
//		String newoutput = sb.toString();
//
//		JSONObject JSONRESPONSE = new JSONObject(newoutput);
//		Object errorMessage = JSONRESPONSE.get("errorMessage").toString();
//		Object errorCode = JSONRESPONSE.get("errorCode").toString();
//		Boolean success = JSONRESPONSE.getBoolean("success");
//
//		JSONArray JsonArrayFromApiWithResult = JSONRESPONSE.getJSONArray("result");
//
//		System.out.println(JsonArrayFromApiWithResult.toString());
//
//		ArrayList<Object> payoptions = new ArrayList<Object>();
//
//		for (int i = 0; i < JsonArrayFromApiWithResult.length(); i++) {
//
//			JSONObject payoptionlink = (JSONObject) JsonArrayFromApiWithResult.get(i);
//
//			String link = payoptionlink.get("logourl").toString();
//			payoptions.add(link);
//		}
//
//		System.out.println(payoptions.size());
//
//		System.out.println("enter ... \n");
//
//		for (int i = 0; i <= payoptions.size()-1; i++) {
//
//			System.out.println(payoptions.get(i));
//		}
//
//		rd.close();

//		System.out.println("<div class="alert alert-warning">");
//		System.out.println("<div class=alert alert-warning >");
		
		System.out.println("<input type='hidden' name='PayOptionSlctd' value='" + 1 + "'> ");
		
	}
}
