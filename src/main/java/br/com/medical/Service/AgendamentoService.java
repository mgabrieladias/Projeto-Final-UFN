package br.com.medical.Service;

import br.com.medical.Entity.Agendamento;
import br.com.medical.Repository.AgendamentoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    public List<Agendamento> listarAgendamentos() {
        return agendamentoRepository.findAll();
    }

    public Agendamento obterAgendamentoPorId(Long id) {
        return agendamentoRepository.findById(id).orElse(null);
    }

    @Transactional
    public Agendamento salvarAgendamento(@Valid Agendamento agendamento) {
        Long id = agendamento.getId();

        // Verifica se já existe um agendamento com a mesma data e médico
        if (agendamentoRepository.existsByDataHoraAndMedicoAndIdNot(agendamento.getDataHora(), agendamento.getMedico(), id)) {
            throw new RuntimeException("Já existe um agendamento para a mesma data e médico");
        }

        return agendamentoRepository.save(agendamento);
    }

    @Transactional
    public Agendamento editarAgendamento(@Valid Agendamento agendamento) {
        Long id = agendamento.getId();

        // Verifica se já existe um agendamento com a mesma data e médico
        if (agendamentoRepository.existsByDataHoraAndMedicoAndIdNot(agendamento.getDataHora(), agendamento.getMedico(), id)) {
            throw new RuntimeException("Já existe um agendamento para a mesma data e médico");
        }

        // Verifica se o agendamento com o ID especificado existe
        Agendamento agendamentoExistente = agendamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));

        // Atualiza os dados do agendamento existente
        agendamentoExistente.setPaciente(agendamento.getPaciente());
        agendamentoExistente.setMedico(agendamento.getMedico());
        agendamentoExistente.setDataHora(agendamento.getDataHora());

        return agendamentoRepository.save(agendamentoExistente);
    }

    public void deletarAgendamento(Long id) {
        agendamentoRepository.deleteById(id);
    }
}
