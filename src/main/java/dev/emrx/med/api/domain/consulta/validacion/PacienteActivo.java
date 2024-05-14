package dev.emrx.med.api.domain.consulta.validacion;

import dev.emrx.med.api.domain.consulta.DatosAgendarConsulta;
import dev.emrx.med.api.repository.PacienteRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteActivo implements ValidadorDeConsultas {

    @Autowired
    private PacienteRepository repository;

    @Override
    public void validar(DatosAgendarConsulta datos) {
        if (datos.idPaciente() == null)
            return;

        var pacienteActivo = repository.findActivoById(datos.idPaciente());

        if(!pacienteActivo) {
            throw new ValidationException("No permitir agendar citas con pacientes inactivos en el sistema");
        }
    }

}
