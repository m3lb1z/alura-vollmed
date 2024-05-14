package dev.emrx.med.api.model.entity;

import dev.emrx.med.api.domain.paciente.DatosActualizarPaciente;
import dev.emrx.med.api.domain.paciente.DatosRegistroPaciente;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "pacientes")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String telefono;
    @Column(name = "documento")
    private String documentoIdentidad;
    private Boolean activo;
    @Embedded
    private Direccion direccion;

    public Paciente(String nombre, String email, String documentoIdentidad) {
        this.nombre = nombre;
        this.email = email;
        this.documentoIdentidad = documentoIdentidad;
        this.activo = true;
    }

    public Paciente(DatosRegistroPaciente datosRegistroPaciente) {
        this.activo = true;
        this.nombre = datosRegistroPaciente.nombre();
        this.email = datosRegistroPaciente.email();
        this.telefono = datosRegistroPaciente.telefono();
        this.documentoIdentidad = datosRegistroPaciente.documentoIdentidad();
        this.direccion = new Direccion(datosRegistroPaciente.direccion());
    }

    public void actualizarDatos(DatosActualizarPaciente datosActualizarPaciente) {
        if (datosActualizarPaciente.nombre() != null)
            this.nombre = datosActualizarPaciente.nombre();
        if (datosActualizarPaciente.email() != null)
            this.email = datosActualizarPaciente.email();
        if (datosActualizarPaciente.direccion() != null)
            this.direccion.actualizarDatos(datosActualizarPaciente.direccion());
    }

    public void desactivarPaciente() {
        this.activo = false;
    }
}
