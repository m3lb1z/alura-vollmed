package dev.emrx.med.api.domain.consulta.validacion;

import dev.emrx.med.api.domain.consulta.DatosAgendarConsulta;
import dev.emrx.med.api.model.repository.ConsultaRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicoConConsulta implements ValidadorDeConsultas {

    @Autowired
    private ConsultaRepository repository;

    @Override
    public void validar(DatosAgendarConsulta datos) {
        if(datos.idMedico() == null)
            return;

        Boolean medicoConConsulta = repository.existsByMedicoIdAndFecha(datos.idMedico(), datos.fecha());

        if(medicoConConsulta) {
            throw new ValidationException("Este medico ya tiene una consulta para ese horario");
        }

    }

}
