package dev.emrx.med.api.domain.consulta.desafio;

import dev.emrx.med.api.domain.consulta.DatosCancelamientoConsulta;

public interface ValidadorCancelamientoDeConsulta {

    void validar(DatosCancelamientoConsulta datos);
}
