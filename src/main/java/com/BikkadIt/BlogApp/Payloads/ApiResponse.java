package com.BikkadIt.BlogApp.Payloads;

public class ApiResponse {
	
	private String messages;
	
	private boolean success;

	public String getMessages() {
		return messages;
	}

	public void setMessages(String messages) {
		this.messages = messages;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public ApiResponse(String messages, boolean success) {
		super();
		this.messages = messages;
		this.success = success;
	}
	
	
	

}
