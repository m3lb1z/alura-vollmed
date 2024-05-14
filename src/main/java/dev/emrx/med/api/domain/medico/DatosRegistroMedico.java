package dev.emrx.med.api.domain.medico;

import dev.emrx.med.api.domain.direccion.DatosDireccion;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DatosRegistroMedico(
        @NotBlank(message = "El nombre es obligatorio")
        String nombre,
        @NotBlank(message = "{email.obligatorio}")
        @Email(message = "{email.invalido}")
        String email,
        @NotBlank
        String telefono,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String documento,
        @NotNull
        Especialidad especialidad,
        @NotNull
        @Valid
        DatosDireccion direccion
) {}
