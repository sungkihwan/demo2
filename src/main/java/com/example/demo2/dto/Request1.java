package com.example.demo2.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record Request1(
    @NotBlank(message = "test: 필수") String test,
    @Min(value = 1, message = "page: 페이지 번호는 1 이상이어야 합니다.") Integer t2
) {
}
