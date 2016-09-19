package tn.esprit.services;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import javax.ejb.Stateful;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import tn.esprit.entities.Invoice;
import tn.esprit.services.interfaces.IInvoiceRestServiceLocal;

/**
 * Session Bean implementation class InvoiceRestService
 */
@Stateful
public class InvoiceRestService implements IInvoiceRestServiceLocal {

	private String protocol;
	private String host;
	private Integer port;
	private String address;
	private String globalUrl;

	/**
	 * Default constructor.
	 */
	public InvoiceRestService() {
		protocol = "http";
		host = "localhost";
		port = 59808;
		address = "api/InvoiceWebApi";
		// globalUrl = "http://localhost:59808/api/InvoiceWebApi";
		globalUrl = protocol + "://" + host + ":" + port + "/" + address + "/";
	}

	@Override
	public Boolean addInvoice(Invoice invoice) {

		JSONObject obj = new JSONObject();

		obj.put("InvoiceOwner", invoice.getInvoiceOwner());
		obj.put("InvoiceDescription", invoice.getInvoiceDescription());
		obj.put("InvoicePrice", invoice.getInvoicePrice());
		obj.put("StatusInventory", invoice.getInvoiceStatus());
		obj.put("Product", invoice.getProduct());
		obj.put("Quantity", invoice.getQuantity());
		obj.put("Items", invoice.getItems());

		StringWriter out = new StringWriter();

		try {
			obj.writeJSONString(out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// String jsonText = out.toString();
		// System.out.print(jsonText);

		return sendPostRequest(out.toString());
	}

	@Override
	public Boolean executeInvoice(Integer idInvoice) {
		JSONObject obj = new JSONObject();

		obj.put("StatusInventory", true);

		StringWriter out = new StringWriter();

		try {
			obj.writeJSONString(out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// String jsonText = out.toString();
		// System.out.print(jsonText);

		return sendPutRequest(out.toString(), idInvoice);
	}

	@Override
	public ArrayList<Invoice> getAllInvoices() {
		JSONParser parser = new JSONParser();
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(globalUrl);
		String response = target.request(MediaType.APPLICATION_JSON).get(
				String.class);

		ArrayList<Invoice> list = new ArrayList<Invoice>();

		try {
			Object obj = parser.parse(response);
			JSONArray array = (JSONArray) obj;

			Invoice inv = null;

			for (int i = 0; i < array.size(); i++) {
				JSONObject invoice = (JSONObject) array.get(i);
				inv = new Invoice();
				inv.setInvoiceId(Integer.parseInt(invoice.get("IdInvoice")
						.toString()));
				inv.setInvoiceOwner(invoice.get("InvoiceOwner").toString());
				inv.setInvoiceDescription(invoice.get("InvoiceDescription")
						.toString());
				inv.setInvoicePrice(Double.parseDouble(invoice.get(
						"InvoicePrice").toString()));
				inv.setInvoiceStatus((Boolean) invoice.get("StatusInventory"));
				String dateRequest = invoice.get("InvoiceDate").toString();
				if (!dateRequest.equals("")) {

					DateFormat df = new SimpleDateFormat(
							"yyyy-MM-dd'T'hh:mm:ss");
					Date startDate = new Date();

					try {
						startDate = df.parse(dateRequest);
						inv.setInvoiceDate(startDate);
					} catch (ParseException e) {
						e.getMessage();
					}
				}
				
				// HashMap
				if(invoice.get("Items") != null){
				JSONObject itemsObj = (JSONObject) invoice.get("Items");
				Iterator it = itemsObj.keySet().iterator();
				HashMap<String, Integer> items = new HashMap<>();
				while (it.hasNext()) {
					String product = (String) it.next();
					Integer quantity = Integer.parseInt(itemsObj.get(product).toString());
					items.put(product, quantity);
				}
				inv.setItems(items);
				}
				


				list.add(inv);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		return list;
	}

	@Override
	public Invoice getInvoice(int idInvoice) {
		// TODO Auto-generated method stub
		return null;
	}

	private Boolean sendPostRequest(String payload) {
		String line;
		StringBuffer jsonString = new StringBuffer();
		try {

			URL url = new URL(globalUrl);

			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();

			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("Content-Type",
					"application/json; charset=UTF-8");
			OutputStreamWriter writer = new OutputStreamWriter(
					connection.getOutputStream(), "UTF-8");
			writer.write(payload);
			writer.close();
			BufferedReader br = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			while ((line = br.readLine()) != null) {
				jsonString.append(line);
			}
			br.close();
			connection.disconnect();
			return true;
		} catch (Exception e) {
			e.getMessage();
			return false;
		}

	}

	private Boolean sendPutRequest(String payload, Integer idInvoice) {
		String line;
		StringBuffer jsonString = new StringBuffer();
		try {

			URL url = new URL(globalUrl + "/" + idInvoice);

			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();

			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestMethod("PUT");
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("Content-Type",
					"application/json; charset=UTF-8");
			OutputStreamWriter writer = new OutputStreamWriter(
					connection.getOutputStream(), "UTF-8");
			writer.write(payload);
			writer.close();
			BufferedReader br = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));

			while ((line = br.readLine()) != null) {
				jsonString.append(line);
			}
			br.close();
			connection.disconnect();
			return true;
		} catch (Exception e) {
			e.getMessage();
			return false;
		}

	}

}
