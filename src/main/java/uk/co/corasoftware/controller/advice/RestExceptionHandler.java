package uk.co.corasoftware.controller.advice;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import io.jsonwebtoken.MalformedJwtException;
import uk.co.corasoftware.exception.InvalidSecurityTokenException;
import uk.co.corasoftware.exception.MissingSecurityTokenException;
import uk.co.corasoftware.model.ApiError;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger LOG = LoggerFactory.getLogger(RestExceptionHandler.class);

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		final List<String> errors = new ArrayList<>();
		for(FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.add(error.getField() + ": " + error.getDefaultMessage());
		}
		for(ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
		}

		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
		return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
	}

	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	@ExceptionHandler({ InvalidSecurityTokenException.class, MalformedJwtException.class })
	public ResponseEntity<Object> handleInvalidSecurityTokenException(Exception ex) {
		ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED, ex.getMessage(), "Invalid Security Token");
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	@ExceptionHandler({ MissingSecurityTokenException.class })
	public ResponseEntity<Object> handleMissingSecurityTokenException(MissingSecurityTokenException ex) {
		ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED, ex.getMessage(), "Missing Security Token");
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		LOG.debug("Method [{}] is not supported for this request. Supported methods are {}", ex.getMethod(),
				ex.getSupportedHttpMethods());
		ApiError apiError = new ApiError(HttpStatus.METHOD_NOT_ALLOWED, ex.getLocalizedMessage(), ""); //TODO externalise
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		LOG.debug("Media type [{}] is not supported. Supported media types are {}", ex.getContentType(),
				ex.getSupportedMediaTypes());
		ApiError apiError = new ApiError(HttpStatus.UNSUPPORTED_MEDIA_TYPE, ex.getLocalizedMessage(), ""); //TODO externalise
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
		ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), "error occurred");
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(),
				String.format("%s parameter is missing", ex.getParameterName()));
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getLocalizedMessage(),
				String.format("No handler found for %s %s", ex.getHttpMethod(), ex.getRequestURL()));
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}
}
