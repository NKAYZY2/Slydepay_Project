
package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.Order;
import model.SlydepayHttpPostRequester;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		String action = request.getParameter("action");
		Order newOrder = new Order();
		PrintWriter out = response.getWriter();
		//if Statement handles submission from form element on Order.jsp Page
		if (action.equals("restPost")) {
			
			String httpPostMerchEmail = request.getParameter("merchantEmail");
			String httpPostMerchKey = request.getParameter("merchantKey");
			String httpPostOrderName = request.getParameter("orderName");
			String httpPostItem = request.getParameter("item");
			Integer httpPostQnty = Integer.parseInt(request.getParameter("quantity"));
			// data from input form on Order.jsp page stored in variables for
			// Servlet process

			// beginning of the processing of post request in servlet for REST API

			// API url
			String url = "https://app.slydepay.com/api/merchant/invoice/payoptions";

			// Creation of Order
		
			newOrder.setMerchantEmail(httpPostMerchEmail);
			newOrder.setMerchantKey(httpPostMerchKey);
			newOrder.setOrderName(httpPostOrderName);
			newOrder.setItem(httpPostItem);
			newOrder.setQuantity(httpPostQnty);

			// Creation of URL for post request
			String answer = SlydepayHttpPostRequester.ListPayOptionsUrlCreator(newOrder, url);

			System.out.println(answer);

			// Initiating post request using HTTPURLCONNECTION

			HttpResponse response2 = SlydepayHttpPostRequester.makePostRequest2(answer, newOrder);

			// Reading Json Response

			BufferedReader rd = new BufferedReader(new InputStreamReader(response2.getEntity().getContent()));

			String output = "";
			Boolean success = null;
			String newoutput;
			Object errorMessage = null;
			Object errorCode;
		

			StringBuilder sb = new StringBuilder(output);

			System.out.println("Output from Server .... \n");

			try {
				while ((output = rd.readLine()) != null) {

					sb.append(output);
				}

				System.out.println(output);
				
			} catch (Exception e) {

				e.printStackTrace();
			}

			newoutput = sb.toString();

			System.out.println(newoutput);

			JSONObject JSONRESPONSE = null;

			try {
				JSONRESPONSE = new JSONObject(newoutput);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				success = JSONRESPONSE.getBoolean("success");

				if (success == false) {

					try {
						errorMessage = JSONRESPONSE.getString("errorMessage");
						System.out.println(errorMessage);

					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					request.setAttribute("errorMessage", errorMessage);

					request.getRequestDispatcher("Error.jsp").forward(request, response);

					return;
				}

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				errorMessage = JSONRESPONSE.get("errorMessage").toString();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				errorCode = JSONRESPONSE.get("errorCode").toString();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			JSONArray JsonArrayFromApiWithResult = null;

			try {
				JsonArrayFromApiWithResult = JSONRESPONSE.getJSONArray("result");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// System.out.println(JsonArrayFromApiWithResult.toString());

			if (JsonArrayFromApiWithResult != null) {
				ArrayList<Object> payoptions = new ArrayList<Object>();
				ArrayList<Object> payoptionsNames = new ArrayList<Object>();

				for (int i = 0; i < JsonArrayFromApiWithResult.length(); i++) {

					JSONObject payoptionObject = null;
					JSONObject payoptionName = null;

					try {
						payoptionObject = (JSONObject) JsonArrayFromApiWithResult.get(i);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					String link = null;
					try {
						link = payoptionObject.get("logourl").toString();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					payoptions.add(link);

					String name = null;
					try {
						name = payoptionObject.get("name").toString();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					payoptionsNames.add(name);
				}

				System.out.println(payoptions.size());

				System.out.println("enter ... \n");

				for (int i = 0; i <= payoptions.size() - 1; i++) {

					System.out.println(payoptionsNames.get(i));
				}

				newOrder.setPayoptions(payoptions);
				newOrder.setPayoptionsNames(payoptionsNames);

				rd.close();

				request.setAttribute("LinkstoPayOptions", newOrder);

				request.getRequestDispatcher("Result.jsp").forward(request, response);
				return;

			}
		} 
		
		//The If statement below is to process input from the Results.jsp page
		//The input exists as a specific hidden value pertaining to the specific
		//form submitted.
		if(action.equals("PayOption")){
			
			
			String PayOptionSlctdFromResultPg = request.getParameter("PayOptionSlctd").toString(); 
			//this line expects an Integer value to come from input form. However,Integer value exists as object
			//Hence line above converts Integer object to string then to an Integer equivalent for processing
			
			
			System.out.println( PayOptionSlctdFromResultPg); //Debug information. 
			
			for(int i = 0;i < newOrder.getPayoptionsNames().size(); i++ ){
				
				if(PayOptionSlctdFromResultPg.equals(newOrder.getPayoptionsNames().get(i))){
					
				String successMessage = "You Have paid successfully";
					
				request.setAttribute("successMessage", successMessage);
					
				request.getRequestDispatcher("Success.jsp").forward(request, response);
					
				
	
				}
				
			}
			
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
