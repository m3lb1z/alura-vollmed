package dev.emrx.med.api.domain.paciente;

import dev.emrx.med.api.domain.direccion.DatosDireccion;
import jakarta.validation.Valid;

public record DatosActualizarPaciente(
        Long id,
        String nombre,
        String email,
        @Valid
        DatosDireccion direccion
) {
}
