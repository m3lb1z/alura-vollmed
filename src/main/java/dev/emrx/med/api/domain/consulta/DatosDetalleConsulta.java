package dev.emrx.med.api.domain.consulta;

import dev.emrx.med.api.model.entity.Consulta;

import java.time.LocalDateTime;

public record DatosDetalleConsulta(
        Long id,
        Long idPaciente,
        Long idMedico,
        LocalDateTime fecha) {
    public DatosDetalleConsulta(Consulta consulta) {
        this(consulta.getId(), consulta.getPaciente().getId(), consulta.getMedico().getId(), consulta.getFecha());
    }

    public DatosDetalleConsulta(Long id, Long idPaciente, Long idMedico, LocalDateTime fecha) {
        this.id = id;
        this.idPaciente = idPaciente;
        this.idMedico = idMedico;
        this.fecha = fecha;
    }
}
