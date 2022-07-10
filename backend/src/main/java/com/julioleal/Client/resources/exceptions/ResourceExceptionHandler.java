package com.julioleal.client.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.julioleal.client.services.exceptions.ResourceNotFoundExeption;

@ControllerAdvice
public class ResourceExceptionHandler {

	
	@ExceptionHandler(ResourceNotFoundExeption.class)
	public ResponseEntity<StandartError> entityNotFound(ResourceNotFoundExeption e, HttpServletRequest request){
		StandartError err = new StandartError();
		HttpStatus status = HttpStatus.NOT_FOUND;
		err.setTimeStamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Resource nor found");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
}
