package dev.emrx.med.api.domain.consulta.validacion;

import dev.emrx.med.api.domain.consulta.DatosAgendarConsulta;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class HorarioDeFuncionamientoClinica implements ValidadorDeConsultas {

    @Override
    public void validar(DatosAgendarConsulta datos) {
        var domingo = DayOfWeek.SATURDAY.equals(datos.fecha());
        var antesDeApertura = datos.fecha().getHour() < 7;
        var despuesDeCierre = datos.fecha().getHour() > 19;

        if(domingo || antesDeApertura || despuesDeCierre) {
            throw new ValidationException("El horario de la clinica es de lunes a sabado de 07:00 a 19:00 horas");
        }
    }

}
