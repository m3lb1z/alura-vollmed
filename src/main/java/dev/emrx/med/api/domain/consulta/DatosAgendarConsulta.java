package dev.emrx.med.api.domain.consulta;

import dev.emrx.med.api.domain.medico.Especialidad;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosAgendarConsulta(
        Long id,
        @NotNull
        Long idPaciente,
        Long idMedico,
        Especialidad especialidad,
        @NotNull
        @Future
        LocalDateTime fecha
) {
}
