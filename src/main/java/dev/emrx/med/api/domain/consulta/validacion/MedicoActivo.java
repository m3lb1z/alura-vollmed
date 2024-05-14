package dev.emrx.med.api.domain.consulta.validacion;

import dev.emrx.med.api.domain.consulta.DatosAgendarConsulta;
import dev.emrx.med.api.repository.MedicoRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicoActivo implements ValidadorDeConsultas {

    @Autowired
    private MedicoRepository repository;

    @Override
    public void validar(DatosAgendarConsulta datos) {
        if(datos.idMedico() == null)
            return;

        Boolean medicoActivo = repository.findActivoById(datos.idMedico());

        if(!medicoActivo) {
            throw new ValidationException("No permitir agendar citas con medicos inactivos en el sistema");
        }
    }

}
