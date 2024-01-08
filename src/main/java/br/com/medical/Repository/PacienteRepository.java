package br.com.medical.Repository;

import br.com.medical.Entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    boolean existsByCpfAndIdNot(String cpf, Long id);

    boolean existsByTelefoneAndIdNot(String telefone, Long id);
}
