package dev.emrx.med.api.service;

import dev.emrx.med.api.domain.consulta.DatosAgendarConsulta;
import dev.emrx.med.api.domain.consulta.DatosCancelamientoConsulta;
import dev.emrx.med.api.domain.consulta.DatosDetalleConsulta;
import dev.emrx.med.api.domain.consulta.desafio.ValidadorCancelamientoDeConsulta;
import dev.emrx.med.api.domain.consulta.validacion.ValidadorDeConsultas;
import dev.emrx.med.api.infra.errores.ValidacionDeIntegridad;
import dev.emrx.med.api.persistence.Consulta;
import dev.emrx.med.api.persistence.Medico;
import dev.emrx.med.api.repository.ConsultaRepository;
import dev.emrx.med.api.repository.MedicoRepository;
import dev.emrx.med.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AgendaDeConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private List<ValidadorDeConsultas> validadores;
    @Autowired
    private List<ValidadorCancelamientoDeConsulta> validadoresCancelamiento;

    public DatosDetalleConsulta agendar(DatosAgendarConsulta datos) {

        if(!pacienteRepository.findById(datos.idPaciente()).isPresent()) {
            throw new ValidacionDeIntegridad("este id para el paciente no fue encontrado");
        }

        if(datos.idMedico() != null && !medicoRepository.existsById(datos.idMedico())) {
            throw new ValidacionDeIntegridad("este id para el medico no fue encontrado");
        }

        // validaciones
        validadores.forEach(validador -> validador.validar(datos));

        var paciente = pacienteRepository.findById(datos.idPaciente()).orElse(null);
        var medico = seleccionarMedico(datos);
        if(medico == null) {
            throw new ValidacionDeIntegridad("no existen medicos disponibles para este horario y la especialidad seleccionada");
        }

        var consulta = new Consulta(null, medico,  paciente, datos.fecha(),null);

        consultaRepository.save(consulta);
        return new DatosDetalleConsulta(consulta);
    }

    public void cancelar(DatosCancelamientoConsulta datos) {
        if (!consultaRepository.existsById(datos.idConsulta())) {
            throw new ValidacionDeIntegridad("El id de la consulta informado no existe.");
        }

        validadoresCancelamiento.forEach(validador -> validador.validar(datos));

        var consulta = consultaRepository.getReferenceById(datos.idConsulta());
        consulta.cancelar(datos.motivo());
    }

    private Medico seleccionarMedico(DatosAgendarConsulta datos) {
        if(datos.idMedico() != null) {
            return medicoRepository.getReferenceById(datos.idMedico());
        }
        if(datos.especialidad() == null) {
            throw new ValidacionDeIntegridad("debe seleccionarse una especialidad para el medico");
        }

        return medicoRepository.seleccionarMedicoConEspecialidadEnFecha(datos.especialidad().name(), datos.fecha());
    }

}
