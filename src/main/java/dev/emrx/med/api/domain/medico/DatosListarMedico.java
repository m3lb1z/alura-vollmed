package dev.emrx.med.api.domain.medico;

import dev.emrx.med.api.persistence.Medico;

public record DatosListarMedico(
        Long id,
        String nombre,
        String especialidad,
        String documento,
        String email
) {
    public DatosListarMedico(Medico medico) {
        this(medico.getId(), medico.getNombre(), medico.getEspecialidad().name(), medico.getDocumento(), medico.getEmail());
    }
}
