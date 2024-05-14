package dev.emrx.med.api.domain.consulta;


import dev.emrx.med.api.model.entity.MotivoCancelamiento;

public record DatosCancelamientoConsulta(
        Long idConsulta,
        MotivoCancelamiento motivo
) {
}
