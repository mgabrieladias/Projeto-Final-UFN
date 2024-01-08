package br.com.medical.Repository;

import br.com.medical.Entity.Agendamento;
import br.com.medical.Entity.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    boolean existsByDataHoraAndMedicoAndIdNot(LocalDateTime dataHora, Medico medico, Long id);
}
