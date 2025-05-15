package com.ho.bootframework.common.exception;

import java.time.LocalDateTime;

public record ErrorResponse(
  String message,
  int status,
  LocalDateTime timestamp
) {
  public static ErrorResponse of(ErrorCode errorCode) {
    return new ErrorResponse(
      errorCode.getMessage(),
      errorCode.getStatus().value(),
      LocalDateTime.now()
    );
  }

  public static ErrorResponse of(ErrorCode errorCode, String detailMessage) {
    return new ErrorResponse(
      detailMessage,
      errorCode.getStatus().value(),
      LocalDateTime.now()
    );
  }
}