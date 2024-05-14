package dev.emrx.med.api.model.entity;

import dev.emrx.med.api.domain.medico.DatosActualizarMedico;
import dev.emrx.med.api.domain.medico.DatosRegistroMedico;
import dev.emrx.med.api.domain.medico.Especialidad;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "medicos")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String telefono;
    private String documento;
    private Boolean activo;
    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;
    @Embedded
    private Direccion direccion;

    public Medico(String nombre, String email, String documento, Especialidad especialidad) {
        this.nombre = nombre;
        this.email = email;
        this.documento = documento;
        this.especialidad = especialidad;
        this.activo = true;
    }

    public Medico(DatosRegistroMedico datosRegistroMedico) {
        this.activo = true;
        this.nombre = datosRegistroMedico.nombre();
        this.email = datosRegistroMedico.email();
        this.telefono = datosRegistroMedico.telefono();
        this.documento = datosRegistroMedico.documento();
        this.especialidad = datosRegistroMedico.especialidad();
        this.direccion = new Direccion(datosRegistroMedico.direccion());
    }

    public void actualizarDatos(DatosActualizarMedico datosActualizarMedico) {
        if (datosActualizarMedico.nombre() != null)
            this.nombre = datosActualizarMedico.nombre();
        if (datosActualizarMedico.documento() != null)
            this.documento = datosActualizarMedico.documento();
        if (datosActualizarMedico.direccion() != null)
            this.direccion = direccion.actualizarDatos(datosActualizarMedico.direccion());
    }

    public void desactivarMedico() {
        this.activo = false;
    }
}
