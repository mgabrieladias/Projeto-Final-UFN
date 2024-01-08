package br.com.medical.Repository;

import br.com.medical.Entity.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, Long> {


    boolean existsByCrmAndIdNot(String crm, Long id);

    boolean existsByCpfAndIdNot(String cpf, Long id);

    boolean existsByTelefoneAndIdNot(String telefone, Long id);

}
