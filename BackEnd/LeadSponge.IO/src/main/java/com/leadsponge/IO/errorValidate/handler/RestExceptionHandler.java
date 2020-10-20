package com.leadsponge.IO.errorValidate.handler;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.leadsponge.IO.errorValidate.ErrorDetails;
import com.leadsponge.IO.errorValidate.ResourceBadRequestException;
import com.leadsponge.IO.errorValidate.ResourceNotFoundException;
import com.leadsponge.IO.errorValidate.ValidationErrorDetails;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handlerResourceNotFoundException(ResourceNotFoundException rnfException) {
		ErrorDetails rnfDetails = ErrorDetails.Builder.newBuilder()
				.timestamp(new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(Calendar.getInstance().getTime())).status(HttpStatus.NOT_FOUND.value()).title("Recurso não encontrado.")
				.detail(rnfException.getMessage()).developerMessage(rnfException.getClass().getName()).build();
		return new ResponseEntity<>(rnfDetails, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(ResourceBadRequestException.class)
	public ResponseEntity<?> handlerResourceBadRequestException(ResourceBadRequestException rbrException) {
		ErrorDetails rnfDetails = ErrorDetails.Builder.newBuilder()
				.timestamp(new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(Calendar.getInstance().getTime())).status(HttpStatus.BAD_REQUEST.value()).title("Erro nos campos.")
				.detail(rbrException.getMessage()).developerMessage(rbrException.getClass().getName()).build();
		return new ResponseEntity<>(rnfDetails, HttpStatus.BAD_REQUEST);
	}

	@Override // @ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException manvException,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<FieldError> fieldErrors = manvException.getBindingResult().getFieldErrors();
		String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(","));
		String fieldMessages = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(","));
		ValidationErrorDetails rnfDetails = ValidationErrorDetails.Builder.newBuilder().timestamp(new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(Calendar.getInstance().getTime()))
				.status(HttpStatus.BAD_REQUEST.value()).title("Erro nos campos.")
				.detail(manvException.getMessage()).developerMessage(manvException.getClass().getName()).field(fields)
				.fieldMessage(fieldMessages).build();
		return new ResponseEntity<>(rnfDetails, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ErrorDetails errorDetails = ErrorDetails.Builder.newBuilder().timestamp(new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(Calendar.getInstance().getTime()))
				.status(HttpStatus.BAD_REQUEST.value()).title("Internal Exception").detail(ex.getMessage())
				.developerMessage(ex.getClass().getName()).build();
		return new ResponseEntity<>(errorDetails, headers, status);
	}
}
