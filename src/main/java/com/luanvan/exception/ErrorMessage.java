package com.luanvan.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessage{
	private int code;
    private String message;
}
