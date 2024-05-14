package dev.emrx.med.api.domain.medico;

import dev.emrx.med.api.domain.direccion.DatosDireccion;
import dev.emrx.med.api.model.entity.Medico;

public record DatosRespuestaMedico(
        Long id,
        String nombre,
        String email,
        String telefono,
        String documento,
        DatosDireccion direccion
) {
    public DatosRespuestaMedico(Medico medico) {
        this(medico.getId(), medico.getNombre(), medico.getEmail(), medico.getTelefono(), medico.getDocumento(), new DatosDireccion(medico.getDireccion()));
    }
}
