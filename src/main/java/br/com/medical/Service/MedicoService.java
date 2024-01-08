package br.com.medical.Service;

import br.com.medical.Entity.Medico;
import br.com.medical.Repository.MedicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    public List<Medico> listarMedicos() {
        return medicoRepository.findAll();
    }

    public Medico obterMedicoPorId(Long id) {
        return medicoRepository.findById(id).orElse(null);
    }

    @Transactional
    public Medico salvarMedico(@Valid Medico medico) {
        Long id = medico.getId();

        // Verifica se já existe um médico com o mesmo CRM
        if (medicoRepository.existsByCrmAndIdNot(medico.getCrm(),id)) {
            throw new RuntimeException("CRM já cadastrado por outro médico");
        }

        // Verifica se já existe um médico com o mesmo CPF
        if (medicoRepository.existsByCpfAndIdNot(medico.getCpf(), id)) {
            throw new RuntimeException("CPF já cadastrado por outro médico");
        }

        // Verifica se já existe um médico com o mesmo telefone
        if (medicoRepository.existsByTelefoneAndIdNot(medico.getTelefone(), id)) {
            throw new RuntimeException("Telefone já cadastrado por outro médico");
        }

        return medicoRepository.save(medico);
    }

    public void deletarMedico(Long id) {
        medicoRepository.deleteById(id);
    }
}