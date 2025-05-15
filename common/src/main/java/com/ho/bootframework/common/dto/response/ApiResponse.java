package com.ho.bootframework.common.dto.response;

public record ApiResponse<T>(boolean success, T data, String errorMessage) {
  public static <T> ApiResponse<T> success(T data) {
    return new ApiResponse<>(true, data, null);
  }

  public static <T> ApiResponse<T> failure(String errorMessage) {
    return new ApiResponse<>(false, null, errorMessage);
  }
}