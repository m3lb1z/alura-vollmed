package dev.emrx.med.api.repository;

import dev.emrx.med.api.persistence.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    @Query("""
            SELECT p.activo
            FROM Paciente p
            WHERE p.id = :idPaciente
    """)
    Boolean findActivoById(Long idPaciente);
}
