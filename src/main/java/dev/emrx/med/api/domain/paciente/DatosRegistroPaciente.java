package dev.emrx.med.api.domain.paciente;

import dev.emrx.med.api.domain.direccion.DatosDireccion;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DatosRegistroPaciente(
        @NotBlank(message = "El nombre es obligatorio")
        String nombre,
        @NotBlank(message = "El email es obligatorio")
        @Email(message = "El formato de email es inválido")
        String email,
        @NotBlank
        String telefono,
        @NotBlank
        @Pattern(regexp = "\\d{4,8}")
        String documentoIdentidad,
        @NotNull
        @Valid
        DatosDireccion direccion
) {}
