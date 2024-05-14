package dev.emrx.med.api.domain.direccion;

import dev.emrx.med.api.model.entity.Direccion;
import jakarta.validation.constraints.NotBlank;

public record DatosDireccion(
        @NotBlank
        String calle,
        @NotBlank
        String distrito,
        @NotBlank
        String ciudad,
        String numero,
        String complemento
) {
        public DatosDireccion(Direccion direccion) {
                this(direccion.getCalle(), direccion.getDistrito(), direccion.getCiudad(), direccion.getNumero().toString(), direccion.getComplemento());
        }
}
