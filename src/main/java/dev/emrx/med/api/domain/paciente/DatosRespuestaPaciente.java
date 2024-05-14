package dev.emrx.med.api.domain.paciente;

import dev.emrx.med.api.domain.direccion.DatosDireccion;
import dev.emrx.med.api.persistence.Paciente;

public record DatosRespuestaPaciente(
        Long id,
        String nombre,
        String email,
        String documentoIdentidad,
        DatosDireccion direccion
) {
    public DatosRespuestaPaciente(Paciente paciente) {
        this(paciente.getId(),
                paciente.getNombre(),
                paciente.getEmail(),
                paciente.getDocumentoIdentidad(),
                new DatosDireccion(paciente.getDireccion()));
    }
}
