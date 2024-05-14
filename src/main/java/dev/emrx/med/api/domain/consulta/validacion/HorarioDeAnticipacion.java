package dev.emrx.med.api.domain.consulta.validacion;


import dev.emrx.med.api.domain.consulta.DatosAgendarConsulta;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class HorarioDeAnticipacion implements ValidadorDeConsultas {

    @Override
    public void validar(DatosAgendarConsulta datos) {
        var ahora = LocalDateTime.now();
        var horaDeConsulta = datos.fecha();

        var diferenciaDe30Minutos = Duration.between(ahora, horaDeConsulta).toMinutes() < 30;

        if(diferenciaDe30Minutos) {
            throw new ValidationException("Las consultas deben programarse al menos 30 minutos de anticipación");
        }
    }

}
