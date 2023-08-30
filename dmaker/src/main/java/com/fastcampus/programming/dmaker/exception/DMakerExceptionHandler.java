package com.fastcampus.programming.dmaker.exception;

import com.fastcampus.programming.dmaker.dto.DMakerErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static com.fastcampus.programming.dmaker.exception.DMakerErrorCode.INTERNAL_SERVER_ERROR;
import static com.fastcampus.programming.dmaker.exception.DMakerErrorCode.INVALID_REQUEST;

@Slf4j
@ControllerAdvice
public class DMakerExceptionHandler {
  @ExceptionHandler(DMakerException.class)
  @ResponseBody
  public DMakerErrorResponse handleDMakerException(
      DMakerException e,
      HttpServletRequest request
  ) {
    log.error("errorCode: {}, url: {}, message: {}", e.getDMakerErrorCode(),
        request.getRequestURI(), e.getDetailMessage(), e);
    return DMakerErrorResponse.builder()
        .errorCode(e.getDMakerErrorCode())
        .errorMessage(e.getDetailMessage())
        .build();
  }

  @ExceptionHandler(value = {
      HttpRequestMethodNotSupportedException.class,
      MethodArgumentNotValidException.class
  })
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public DMakerErrorResponse handleBadRequest(
      Exception e,
      HttpServletRequest request
  ) {
    log.error("url: {}, message: {}", request.getRequestURI(), e.getMessage(), e);
    return DMakerErrorResponse.builder()
        .errorCode(INVALID_REQUEST)
        .errorMessage(INVALID_REQUEST.getMessage())
        .build();
  }


  @ExceptionHandler(Exception.class)
  @ResponseBody
  public DMakerErrorResponse handleException(
      Exception e,
      HttpServletRequest request
  ) {
    log.error("url: {}, message: {}", request.getRequestURI(), e.getMessage(), e);
    return DMakerErrorResponse.builder()
        .errorCode(INTERNAL_SERVER_ERROR)
        .errorMessage(INTERNAL_SERVER_ERROR.getMessage())
        .build();
  }
}
