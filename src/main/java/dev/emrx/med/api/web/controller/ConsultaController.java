package dev.emrx.med.api.web.controller;

import dev.emrx.med.api.domain.consulta.DatosAgendarConsulta;
import dev.emrx.med.api.domain.consulta.DatosCancelamientoConsulta;
import dev.emrx.med.api.model.service.AgendaDeConsultaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    private AgendaDeConsultaService service;

    @PostMapping
    @Transactional
    @Operation(
            summary = "registra una consulta en la base de datos",
            description = "",
            tags = {"consultas", "post"}
    )
    public ResponseEntity agendar(@RequestBody @Valid DatosAgendarConsulta datos) {
        var response = service.agendar(datos);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    @Transactional
    @Operation(
            summary = "cancela una consulta de la agenda",
            description = "",
            tags = {"consultas", "delete"}
    )
    public ResponseEntity cancelar(@RequestBody @Valid DatosCancelamientoConsulta datos) {
        service.cancelar(datos);
        return ResponseEntity.noContent().build();
    }

}
