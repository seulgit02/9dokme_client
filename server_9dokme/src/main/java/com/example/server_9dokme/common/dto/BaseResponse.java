package com.example.server_9dokme.common.dto;

import lombok.NonNull;

public interface BaseResponse {
    boolean success();
    @NonNull String message();
}