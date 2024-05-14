package dev.emrx.med.api.web.controller;

import dev.emrx.med.api.domain.medico.DatosActualizarMedico;
import dev.emrx.med.api.domain.medico.DatosRegistroMedico;
import dev.emrx.med.api.domain.medico.DatosListarMedico;
import dev.emrx.med.api.domain.medico.DatosRespuestaMedico;
import dev.emrx.med.api.model.entity.Medico;
import dev.emrx.med.api.model.repository.MedicoRepository;
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
@RequestMapping("/medicos")
@SecurityRequirement(name = "bearer-key")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @Operation(summary = "Registrar un nuevo médico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Médico registrado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    @PostMapping
    public ResponseEntity<DatosRespuestaMedico> registrarMedico(@RequestBody @Valid DatosRegistroMedico datosRegistroMedico,
                                          UriComponentsBuilder uriComponentsBuilder) {
        System.out.println("Medico registrado");
        Medico nuevo = medicoRepository.save(new Medico(datosRegistroMedico));
        // Return 201 Created
        // URL donde encontrar al medico
        DatosRespuestaMedico datosRespuestaMedico = new DatosRespuestaMedico(nuevo);
        URI url = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(nuevo.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaMedico);
    }

    @Operation(summary = "Listar todos los médicos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "Médicos no encontrados")
    })
    @GetMapping
    public ResponseEntity<Page<DatosListarMedico>> listarMedicos(@PageableDefault(size = 3, sort = "nombre") Pageable paginacion) {
//        return medicoRepository.findAll(paginacion).map(DatosListarMedico::new);
        return ResponseEntity.ok(medicoRepository.findByActivoTrue(paginacion).map(DatosListarMedico::new));
    }

    @Operation(summary = "Actualizar los datos de un médico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Médico actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @ApiResponse(responseCode = "404", description = "Médico no encontrado")
    })
    @PutMapping
    @Transactional
    public ResponseEntity actualizar(@RequestBody DatosActualizarMedico datosActualizarMedico) {
        Medico medico = medicoRepository.getReferenceById(datosActualizarMedico.id());
        medico.actualizarDatos(datosActualizarMedico);
//        medicoRepository.save(medico);
        return ResponseEntity.ok(new DatosRespuestaMedico(medico));
    }

    // delete logico
    @Operation(summary = "Eliminar un médico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Médico eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Médico no encontrado")
    })
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarMedico(@PathVariable Long id) {
        Medico medico = medicoRepository.getReferenceById(id);
//        medicoRepository.delete(medico); // delete en base de datos
        medico.desactivarMedico();
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Obtener los datos de un médico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "404", description = "Médico no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaMedico> obtenerDatosMedico(@PathVariable Long id) {
        Medico medico = medicoRepository.getReferenceById(id);
        var datosMedicos = new DatosRespuestaMedico(medico);
        return ResponseEntity.ok(datosMedicos);
    }
}
