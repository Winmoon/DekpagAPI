package br.com.winmoon.dekpag.test;

import java.math.BigDecimal;

import br.com.winmoon.dekpag.Dekpag;
import br.com.winmoon.dekpag.StatusPayment;
import br.com.winmoon.dekpag.exception.DekpagException;

public class Test {

	public static void main(String[] args) throws DekpagException {
		
		String apiKey = "3d3979adc7b1cea1";
		String apiSecret = "75803014c32af2a9";
		
		String sale = "46487943211";
		BigDecimal value = new BigDecimal("4495.45");
		
		Dekpag dekpag = new Dekpag(apiKey, apiSecret);
		
		StatusPayment status = dekpag.getStatus(sale, value);
		
		System.out.println("");
		System.out.println("#############################################");
		System.out.println("Status of sale " + sale + " / " + value + ":");
		System.out.println("Code:     " + status.getStatusCode());
		System.out.println("Status:   " + status.getStatus());
		System.out.println("Message:  " + status.getMessage());
		System.out.println("Protocol: " + status.getProtocol());
		System.out.println("Value:    " + status.getValue());
		System.out.println("#############################################");
		

	}

}
