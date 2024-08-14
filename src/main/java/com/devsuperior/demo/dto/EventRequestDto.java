package com.devsuperior.demo.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record EventRequestDto(@NotBlank(message = "Campo requerido") String name,
                              @NotBlank(message = "Campo requerido") @URL String url
        ,@FutureOrPresent(message = "A data do evento não pode ser passada") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
                              @NotNull(message = "Campo requerido")
                              @Positive(message = "Campo requerido") Long cityId) {
}
