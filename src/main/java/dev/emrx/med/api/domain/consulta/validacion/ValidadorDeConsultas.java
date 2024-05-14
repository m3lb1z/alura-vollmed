package dev.emrx.med.api.domain.consulta.validacion;

import dev.emrx.med.api.domain.consulta.DatosAgendarConsulta;

public interface ValidadorDeConsultas {

    void validar(DatosAgendarConsulta datos);

}
