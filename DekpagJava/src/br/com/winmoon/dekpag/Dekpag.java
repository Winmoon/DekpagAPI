package br.com.winmoon.dekpag;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;

import br.com.winmoon.dekpag.exception.DekpagException;

import com.thoughtworks.xstream.XStream;

/**
 * @author Diego M. Rodrigues
 * @date 2011/09/09
 * 
 * @description: Class that contains the methods of integration with Dekpag 
 */
public class Dekpag {
	
	private static final int _1_SEC = 1000;
	private static final int _CONNECTION_TIMEOUT = 20 * _1_SEC;
	private static final int _READ_TIMEOUT = 80 * _1_SEC;
	private static final String DEKPAG_URL = "https://localhost:8443/Dekpag/ws";

	private String apiKey;
	private String apiSecret;

	private static HttpClient httpClient;

	public Dekpag(String apiKey, String apiSecret) {
		
		// configure establishment data
		this.apiKey = apiKey;
		this.apiSecret = apiSecret;
		
		// set the http client resource and parameters
		httpClient = new HttpClient();
		httpClient.setHttpConnectionManager(new MultiThreadedHttpConnectionManager());
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(_CONNECTION_TIMEOUT);
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(_READ_TIMEOUT);
	}

	public StatusPayment getStatus(String sale, String value) throws DekpagException {
		try {
			
			// set the parameters of request
			PostMethod method = new PostMethod(DEKPAG_URL+"/payment/status.xml"); 
			method.addParameter("apiKey", apiKey);
			method.addParameter("apiSecret", apiSecret);
			method.addParameter("sale", sale);
			method.addParameter("value", value);
			
			httpClient.executeMethod(method);

			String response = method.getResponseBodyAsString(); 

			if (response != null && response.length() > 0 && response.contains("<")) {

				// map the response parameters to StatusPayment object
				XStream xStream = new XStream();

				xStream.alias("PaymentStatus", StatusPayment.class);
				xStream.aliasAttribute(StatusPayment.class, "statusCode", "status");
				xStream.aliasAttribute(StatusPayment.class, "message", "message"); 
				xStream.aliasAttribute(StatusPayment.class, "protocol", "protocol");
				xStream.aliasAttribute(StatusPayment.class, "value", "value");
				
				// convert xml string into StatusPayment object
				StatusPayment statusPayment = (StatusPayment)xStream.fromXML(response);

				return statusPayment;
			}

		} catch (Exception e) {
			throw new DekpagException(e.getMessage());
		}
		return null;
	}
	
}