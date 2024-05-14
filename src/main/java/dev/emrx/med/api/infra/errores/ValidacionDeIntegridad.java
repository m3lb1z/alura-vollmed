package dev.emrx.med.api.infra.errores;

public class ValidacionDeIntegridad extends RuntimeException {

    public ValidacionDeIntegridad(String message) {
        super(message);
    }
}
