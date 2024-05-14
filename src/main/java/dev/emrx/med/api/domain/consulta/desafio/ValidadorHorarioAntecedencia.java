package dev.emrx.med.api.domain.consulta.desafio;

import dev.emrx.med.api.domain.consulta.DatosCancelamientoConsulta;
import dev.emrx.med.api.persistence.Consulta;
import dev.emrx.med.api.repository.ConsultaRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioAntecedencia implements ValidadorCancelamientoDeConsulta {

    @Autowired
    private ConsultaRepository repository;

    @Override
    public void validar(DatosCancelamientoConsulta datos) {
        Consulta consulta = repository.getReferenceById(datos.idConsulta());
        var ahora = LocalDateTime.now();
        var diferenciaEnHoras = Duration.between(ahora, consulta.getFecha()).toHours();

        if (diferenciaEnHoras < 24) {
            throw new ValidationException("Consulta solamente puede ser cancelada con al menos 24 horas de antecedencia.");
        }
    }
}
