package br.com.winmoon.dekpag;


/**
 * @author Diego M. Rodrigues
 * @date 2011/09/09
 * 
 * @description: The payment status response mapper 
 */
public class StatusPayment {

	private Integer statusCode;
	private String message;
	private String protocol;
	private String value;

	public Status getStatus() {
		return Status.get(statusCode);
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public enum Status {

		UNKNOWNERROR(100, "Unknown error"), 
		COMPLETED(0, "Transaction completed"), 
		COMPLETED_VALUE_BIGGER(1, "Transaction completed with a bigger value"), 
		COMPLETED_VALUE_LESS(2, "Transaction completed with a less value"), 
		IN_PROGRESS(3, "Transaction in progress"), 
		WAITING_BANK_CONFIRM(4, "Waiting bank confirm"), 
		CANCELED(5, "Transaction canceled"), 
		EXPIRED(6, "Transaction expired"), 
		TRANSACTION_NOT_FOUND(10, "Transaction not found"), 
		API_SECRET_INVALID(11, "API Secret is invalid"), 
		ESTABLISHMENT_NOT_FOUND(12, "Establishment not found"), 
		VALUE_INVALID(13, "Value is invalid");

		private Integer code;
		private String description;

		Status(Integer code, String description) {
			this.code = code;
			this.description = description;
		}

		public Integer getCode() {
			return code;
		}

		public String getDescription() {
			return description;
		}

		public static Status get(Integer code) {
			for (Status status : Status.values()) {
				if (status.getCode().equals(code))
					return status;
			}
			return null;
		}
	
		public boolean isError() {
			if (this.getCode() >= 10)
				return true;
			return false;
		}
	
	
	}

}
