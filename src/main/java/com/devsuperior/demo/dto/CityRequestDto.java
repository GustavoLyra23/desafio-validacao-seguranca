package com.devsuperior.demo.dto;

import jakarta.validation.constraints.NotBlank;

public record CityRequestDto(@NotBlank(message = "Campo requerido") String name) {
}
