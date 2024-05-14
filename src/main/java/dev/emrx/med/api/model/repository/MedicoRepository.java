package dev.emrx.med.api.model.repository;

import dev.emrx.med.api.model.entity.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {

    Page<Medico> findByActivoTrue(Pageable paginacion);

    @Query(value = """
            SELECT m.* FROM medicos m
            WHERE m.activo = 1 AND 
            m.especialidad = :especialidad AND
            m.id NOT IN(
                SELECT c.medico_id FROM consultas c
                WHERE c.fecha = :fecha
            )
            ORDER BY RAND() LIMIT 1
            """, nativeQuery = true)
    Medico seleccionarMedicoConEspecialidadEnFecha(@Param("especialidad") String especialidad,@Param("fecha") LocalDateTime fecha);

    @Query("""
            SELECT m.activo
            FROM Medico m
            WHERE m.id = :idMedico
            """)
    Boolean findActivoById(Long idMedico);
}
