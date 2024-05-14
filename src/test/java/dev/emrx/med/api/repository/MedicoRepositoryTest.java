package dev.emrx.med.api.repository;

import dev.emrx.med.api.domain.medico.Especialidad;
import dev.emrx.med.api.model.entity.Consulta;
import dev.emrx.med.api.model.entity.Medico;
import dev.emrx.med.api.model.entity.Paciente;
import dev.emrx.med.api.model.repository.MedicoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("deberia retornar nulo cuando el medico se encuentre en consulta con otro paciente en ese horario")
    void seleccionarMedicoConEspecialidadEnFechaUno() {
        // given
        var proximoLunes10H = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        var medico = registrarMedico("Juan", "jose@gmail.com", "564872", Especialidad.CARDIOLOGIA);
        var paciente = registrarPaciente("Antonio", "antonio@gmail.com", "784612");
        registrarConsulta(medico, paciente, proximoLunes10H);

        // when
        var medicoLibre = medicoRepository.seleccionarMedicoConEspecialidadEnFecha(Especialidad.CARDIOLOGIA.name(), proximoLunes10H);

        // then
        assertThat(medicoLibre).isNull();

    }

    @Test
    @DisplayName("deberia retornar un medico cuando realice la consulta en la base de datos para ese horario")
    void seleccionarMedicoConEspecialidadEnFechaDos() {
        // given
        var proximoLunes10H = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        var medico = registrarMedico("Juan", "jose@gmail.com", "564872", Especialidad.CARDIOLOGIA);

        // when
        var medicoLibre = medicoRepository.seleccionarMedicoConEspecialidadEnFecha(Especialidad.CARDIOLOGIA.name(), proximoLunes10H);

        // then
        assertThat(medicoLibre).isEqualTo(medico);
    }

    private Paciente registrarPaciente(String nombre, String email, String documento) {
        var paciente = new Paciente(nombre, email, documento);
        em.persist(paciente);
        return paciente;
    }

    private Medico registrarMedico(String nombre, String email, String documento, Especialidad especialidad) {
        var medico = new Medico(nombre, email, documento, especialidad);
        em.persist(medico);
        return medico;
    }

    private void registrarConsulta(Medico medico, Paciente paciente, LocalDateTime fecha) {
        em.persist(new Consulta(null, medico, paciente, fecha, null));
    }

    @Test
    void findActivoById() {

    }


}