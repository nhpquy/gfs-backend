package com.gfs.domain.handler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.gfs.domain.exception.ConnectionException;
import com.gfs.domain.exception.ServiceException;
import com.gfs.domain.model.ErrorResponseObject;
import com.gfs.domain.utils.GsonSingleton;
import com.gfs.domain.utils.ServiceExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.security.InvalidKeyException;

@RestControllerAdvice()
public class ServiceExceptionHandler {
    private static final String TAG = ServiceExceptionHandler.class.getName();

    private static HttpHeaders header;

    static {
        header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
    }

    @ExceptionHandler({ServiceException.class})
    protected Object handleServiceException(HttpServletRequest req, HttpServletResponse res, final ServiceException ex) throws IOException {
        HttpStatus httpStatus = ex.getHttpStatus();
        String response = ex.generateStringResponse();
        return new ResponseEntity<>(response, header, httpStatus);
    }

    @ExceptionHandler({AccessDeniedException.class})
    protected Object handleAccessDeniedException(HttpServletRequest req, HttpServletResponse res) throws IOException {
        // Return HTML response
        String viewName = String.format("error_pages/page_%s.jsp", 401);
        ModelAndView mav = new ModelAndView();
        mav.addObject("description", "Access denied");
        mav.setViewName(viewName);
        return mav;
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpServletRequest request, HttpMessageNotReadableException e) {
        ServiceException serviceException;
        if (e.getCause() instanceof InvalidFormatException) {
            InvalidFormatException exception = (InvalidFormatException) e.getCause();
            serviceException = ServiceExceptionUtils.invalidValue(exception.getValue().toString());
        } else {
            serviceException = ServiceExceptionUtils.invalidJSON();
        }
        HttpStatus httpStatus = serviceException.getHttpStatus();
        return new ResponseEntity<>(serviceException.generateStringResponse(), header, httpStatus);
    }

    @ExceptionHandler({Exception.class})
    protected ResponseEntity<Object> handleRuntimeException(final Exception ex) {
        ex.printStackTrace();
        return new ResponseEntity<>(ServiceExceptionUtils.internalServerError().generateStringResponse(), header, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({MissingServletRequestParameterException.class})
    protected ResponseEntity<Object> handleRuntimeException(final MissingServletRequestParameterException ex) {
        ServiceException exception = ServiceExceptionUtils.missingParam(ex.getParameterName());
        exception.setDescription(ex.getMessage());
        return new ResponseEntity<>(exception.generateStringResponse(), header, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    protected Object methodNotSupported(HttpServletRequest request) {
        return new ResponseEntity<>(ServiceExceptionUtils.methodIsNotAllowed().generateStringResponse(), header, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    protected ResponseEntity<Object> handleConstraintViolationException(final ConstraintViolationException ex) {
        String message = ex.getConstraintViolations().iterator().next().getMessage();
        ErrorResponseObject responseObject;
        HttpStatus httpStatus;
        try {
            responseObject = GsonSingleton.getInstance().fromJson(message, ErrorResponseObject.class);
            String errorCode = responseObject.getError();
            String httpCode = errorCode.substring(0, 3);
            httpStatus = HttpStatus.valueOf(Integer.valueOf(httpCode));
        } catch (Exception e) {
            ServiceException serviceException = ServiceExceptionUtils.invalidParam(message);
            responseObject = serviceException.generateResponse();
            httpStatus = serviceException.getHttpStatus();
        }

        return new ResponseEntity<>(responseObject, header, httpStatus);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    protected Object handleMethodArgumentNotValid(HttpServletRequest request, MethodArgumentNotValidException e) {
        if (e.getBindingResult().getAllErrors().get(0) instanceof FieldError) {
            FieldError fieldError = (FieldError) e.getBindingResult().getAllErrors().get(0);
            String error_message = String.format("'%s', value '%s' is rejected, %s", fieldError.getField(), fieldError.getRejectedValue(), fieldError.getDefaultMessage());
            ServiceException exception = ServiceExceptionUtils.invalidParam(error_message);
            return new ResponseEntity<>(exception.generateResponse(), header, exception.getHttpStatus());
        }
        e.printStackTrace();
        return getResponseObject();
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    protected Object handleMethodArgumentTypeMismatchException(HttpServletRequest request, MethodArgumentTypeMismatchException e) {
        String error_message = String.format("Invalid param '%s', value '%s' is rejected", e.getName(), e.getValue());
        ServiceException exception = ServiceExceptionUtils.invalidParam(error_message);
        return new ResponseEntity<>(exception.generateResponse(), header, exception.getHttpStatus());
    }

    @ExceptionHandler({MissingRequestHeaderException.class})
    protected Object handleMissingRequestHeaderException(HttpServletRequest request, MissingRequestHeaderException e) {
        ServiceException exception = new ServiceException("401000", e.getMessage(), HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(exception.generateStringResponse(), header, exception.getHttpStatus());
    }

    @ExceptionHandler({InvalidKeyException.class})
    protected Object handleInvalidKeyException(HttpServletRequest request, InvalidKeyException e) {
        ServiceException exception = ServiceExceptionUtils.invalidProcessInCourse(e.getMessage());
        return new ResponseEntity<>(exception.generateStringResponse(), header, exception.getHttpStatus());
    }

    @ExceptionHandler({ConnectionException.class})
    protected Object handleConnectionException(HttpServletRequest request, ConnectionException e) {
        ServiceException exception = new ServiceException("500000", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(exception.generateStringResponse(), header, exception.getHttpStatus());
    }

    private ResponseEntity<ErrorResponseObject> getResponseObject() {
        ServiceException exception = ServiceExceptionUtils.invalidJSON();
        ErrorResponseObject response = exception.generateResponse();
        HttpStatus httpStatus = exception.getHttpStatus();
        return new ResponseEntity<>(response, header, httpStatus);
    }
}
