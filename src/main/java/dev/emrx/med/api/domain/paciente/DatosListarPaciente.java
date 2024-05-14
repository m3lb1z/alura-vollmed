package dev.emrx.med.api.domain.paciente;

import dev.emrx.med.api.persistence.Paciente;

public record DatosListarPaciente(
        Long id,
        String nombre,
        String email,
        String documentoIdentidad
) {
    public DatosListarPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNombre(), paciente.getEmail(), paciente.getDocumentoIdentidad());
    }
}
