package dev.emrx.med.api.domain.consulta.validacion;

import dev.emrx.med.api.domain.consulta.DatosAgendarConsulta;
import dev.emrx.med.api.model.repository.ConsultaRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteSinConsulta implements ValidadorDeConsultas {

    @Autowired
    private ConsultaRepository repository;

    @Override
    public void validar(DatosAgendarConsulta datos) {

        var primerHorario = datos.fecha().withHour(7);
        var ultimoHorario = datos.fecha().withHour(18);

        Boolean pacienteConConsulta = repository.existsByPacienteIdAndFechaBetween(datos.idPaciente(), primerHorario, ultimoHorario);

        if(pacienteConConsulta) {
            throw new ValidationException("El paciente ya tiene una consulta para ese día agendada");
        }
    }
}
