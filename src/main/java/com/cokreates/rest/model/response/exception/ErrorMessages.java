package com.cokreates.rest.model.response.exception;

public enum ErrorMessages {

	MISSING_REQUIRED_FIELD("Missing Required Field."),
	RECORD_ALREADY_EXISTS("Record Already Exists."),
	INTERNAL_SERVER_ERROR("Internal Server Error."),
	NO_RECORD_FOUND("No Record Found."),
	AUTHENTICATION_FAILED("Authentication Failed."),
	COULD_NOT_UPDATE_RECORD("Could Not Update Record."),
	COULD_NOT_DELETE_RECORD("Could Not Delete Record."),
	EMAIL_ADDRESS_NOT_VERIFIED("Email Address Not Verified.");

	private String errorMessage;

	ErrorMessages(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
