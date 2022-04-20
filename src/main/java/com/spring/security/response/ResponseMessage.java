package com.spring.security.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseMessage {

	private boolean error;
	private String message;
	private Object data;

}
