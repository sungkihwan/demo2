package com.example.demo2.dto;

public record Response<T>(String message, Boolean success, T data) {
}
