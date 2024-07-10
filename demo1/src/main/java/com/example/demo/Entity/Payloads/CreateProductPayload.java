package com.example.demo.Entity.Payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateProductPayload(
        @NotEmpty(message = "Название не может быть пустым")
        String name,
        @NotNull (message = "Количество не может быть пустым")
        Integer count,
        @NotNull (message = "Цена не может быть пустой")
        Double price) {
}
