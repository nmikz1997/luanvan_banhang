package com.luanvan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
	
	public ApiExceptionHandler() {
		super();
	}
	
	@ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage handleAllException(Exception ex, WebRequest request) {
        return new ErrorMessage(ex.getLocalizedMessage());
    }
	
//	@ExceptionHandler(IndexOutOfBoundsException.class)
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//    public ErrorMessage NotFoundException(Exception ex, WebRequest request) {
//        return new ErrorMessage("Đối tượng không tồn tại");
//    }
	
	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	protected ErrorMessage handleNotFound(Exception ex, WebRequest request){
		return new ErrorMessage("Không tìm thấy");
	}
	
	
	
}
