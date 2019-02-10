package com.tavisca.travel.core.hotelrecordfetch.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class HotelRecordExceptionHandler extends ResponseEntityExceptionHandler {


	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(final MissingServletRequestParameterException ex, final HttpHeaders headers, final HttpStatus status,
			final WebRequest request) {

		HotelRecordExceptionResponse exceptionResponse = new HotelRecordExceptionResponse(new Date(), "Validation Error", ex.getMessage());

		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HotelRecordNotFoundException.class)
	public ResponseEntity<Object> handleUderNotFoundException(final HotelRecordNotFoundException ex, final WebRequest request) {
		HotelRecordExceptionResponse exceptionResponse = new HotelRecordExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));

		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.NOT_FOUND);

	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status,
			final WebRequest request) {
		HotelRecordExceptionResponse exceptionResponse = new HotelRecordExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex, final HttpHeaders headers, final HttpStatus status,
			final WebRequest request) {
		HotelRecordExceptionResponse exceptionResponse = new HotelRecordExceptionResponse(new Date(), ex.getMessage().split(";")[0], request.getDescription(false));
		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

}
