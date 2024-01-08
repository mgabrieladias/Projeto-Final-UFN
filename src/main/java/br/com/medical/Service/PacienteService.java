package br.com.medical.Service;

import br.com.medical.Entity.Paciente;
import br.com.medical.Repository.PacienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public List<Paciente> listarPacientes() {
        return pacienteRepository.findAll();
    }

    public Paciente obterPacientePorId(Long id) {
        return pacienteRepository.findById(id).orElse(null);
    }

    @Transactional
    public Paciente salvarPaciente(@Valid Paciente paciente) {
        Long id = paciente.getId();

        // Verifica se já existe um paciente com o mesmo CPF
        if (pacienteRepository.existsByCpfAndIdNot(paciente.getCpf(), id)) {
            throw new RuntimeException("CPF já cadastrado por outro paciente");
        }

        // Verifica se já existe um paciente com o mesmo telefone
        if (pacienteRepository.existsByTelefoneAndIdNot(paciente.getTelefone(), id)) {
            throw new RuntimeException("Telefone já cadastrado por outro paciente");
        }

        return pacienteRepository.save(paciente);
    }

    public void deletarPaciente(Long id) {
        pacienteRepository.deleteById(id);
    }
}
