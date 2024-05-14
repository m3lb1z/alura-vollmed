package dev.emrx.med.api.model.entity;

import dev.emrx.med.api.domain.direccion.DatosDireccion;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Direccion {

    private String calle;
    private String distrito;
    private String ciudad;
    private Integer numero;
    private String complemento;

    public Direccion(String calle, String distrito, String ciudad) {
        this.calle = calle;
        this.distrito = distrito;
        this.ciudad = ciudad;
    }

    public Direccion(DatosDireccion direccion) {
        this.calle = direccion.calle();
        this.distrito = direccion.distrito();
        this.ciudad = direccion.ciudad();
        this.numero = Integer.parseInt(direccion.numero());
        this.complemento = direccion.complemento();
    }

    public Direccion actualizarDatos(DatosDireccion direccion) {
        this.calle = direccion.calle();
        this.distrito = direccion.distrito();
        this.ciudad = direccion.ciudad();
        this.numero = Integer.parseInt(direccion.numero());
        this.complemento = direccion.complemento();
        return this;
    }
}
