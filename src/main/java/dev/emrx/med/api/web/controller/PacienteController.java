package dev.emrx.med.api.web.controller;

import dev.emrx.med.api.domain.paciente.DatosActualizarPaciente;
import dev.emrx.med.api.domain.paciente.DatosRegistroPaciente;
import dev.emrx.med.api.domain.paciente.DatosListarPaciente;
import dev.emrx.med.api.domain.paciente.DatosRespuestaPaciente;
import dev.emrx.med.api.model.entity.Paciente;
import dev.emrx.med.api.model.repository.PacienteRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/pacientes")
@SecurityRequirement(name = "bearer-key")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Operation(summary = "Registrar un nuevo paciente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Paciente registrado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    @PostMapping
    public ResponseEntity registrarPaciente(@RequestBody @Valid DatosRegistroPaciente datosRegistroPaciente, UriComponentsBuilder uriComponentsBuilder) {
        System.out.println("Paciente registrado");
        Paciente nuevo = pacienteRepository.save(new Paciente(datosRegistroPaciente));
        URI url = uriComponentsBuilder.path("/pacientes/{id}").buildAndExpand(nuevo.getId()).toUri();
        return ResponseEntity.created(url).body(new DatosListarPaciente(nuevo));
    }

    @Operation(summary = "Listar todos los pacientes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "Pacientes no encontrados")
    })
    @GetMapping
    public ResponseEntity<Page<DatosListarPaciente>> listarPaciente(@PageableDefault(page = 0, size = 10, sort = {"nombre"}) Pageable paginacion) {
        return ResponseEntity.ok(pacienteRepository.findAll(paginacion).map(DatosListarPaciente::new));
    }

    @Operation(summary = "Actualizar los datos de un paciente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paciente actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @ApiResponse(responseCode = "404", description = "Paciente no encontrado")
    })
    @PutMapping
    @Transactional
    public ResponseEntity actualizarPaciente(@RequestBody @Valid DatosActualizarPaciente datosActualizarPaciente) {
        System.out.println("Paciente actualizado");
        Paciente paciente = pacienteRepository.getReferenceById(datosActualizarPaciente.id());
        paciente.actualizarDatos(datosActualizarPaciente);
        return ResponseEntity.ok(new DatosRespuestaPaciente(paciente));
    }

    @Operation(summary = "Eliminar un paciente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Paciente eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Paciente no encontrado")
    })
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarPaciente(@PathVariable Long id) {
        Paciente paciente = pacienteRepository.getReferenceById(id);
        paciente.desactivarPaciente();
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Obtener los datos de un paciente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "Paciente no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaPaciente> obtenerDatosPaciente(@PathVariable Long id) {
        Paciente paciente = pacienteRepository.getReferenceById(id);
        return ResponseEntity.ok(new DatosRespuestaPaciente(paciente));
    }

}
