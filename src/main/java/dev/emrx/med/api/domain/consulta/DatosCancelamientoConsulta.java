package dev.emrx.med.api.domain.consulta;


import dev.emrx.med.api.persistence.MotivoCancelamiento;

public record DatosCancelamientoConsulta(
        Long idConsulta,
        MotivoCancelamiento motivo
) {
}
