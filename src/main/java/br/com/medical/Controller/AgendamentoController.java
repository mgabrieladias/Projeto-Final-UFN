package br.com.medical.Controller;

import br.com.medical.Entity.Agendamento;
import br.com.medical.Entity.Medico;
import br.com.medical.Entity.Paciente;
import br.com.medical.Service.AgendamentoService;
import br.com.medical.Service.MedicoService;
import br.com.medical.Service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private MedicoService medicoService;

    @GetMapping
    public String listarAgendamentos(Model model) {
        List<Agendamento> agendamentos = agendamentoService.listarAgendamentos();
        model.addAttribute("agendamentos", agendamentos);
        return "agendamentos";
    }

    @GetMapping("/{id}")
    public String exibirAgendamento(@PathVariable Long id, Model model) {
        Agendamento agendamento = agendamentoService.obterAgendamentoPorId(id);
        model.addAttribute("agendamento", agendamento);
        return "agendamento-detalhes";
    }

    @GetMapping("/novo")
    public String mostrarFormularioNovoAgendamento(Model model) {
        List<Paciente> pacientes = pacienteService.listarPacientes();
        List<Medico> medicos = medicoService.listarMedicos();

        model.addAttribute("agendamento", new Agendamento());
        model.addAttribute("pacientes", pacientes);
        model.addAttribute("medicos", medicos);

        return "formulario-agendamento";
    }

    @PostMapping("/novo")
    public String salvarAgendamento(@ModelAttribute Agendamento agendamento, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<Paciente> pacientes = pacienteService.listarPacientes();
            List<Medico> medicos = medicoService.listarMedicos();
            model.addAttribute("pacientes", pacientes);
            model.addAttribute("medicos", medicos);
            return "formulario-agendamento";
        }

        agendamentoService.salvarAgendamento(agendamento);
        return "redirect:/agendamentos";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarAgendamento(@PathVariable Long id, Model model) {
        Agendamento agendamento = agendamentoService.obterAgendamentoPorId(id);
        List<Paciente> pacientes = pacienteService.listarPacientes();
        List<Medico> medicos = medicoService.listarMedicos();

        model.addAttribute("agendamento", agendamento);
        model.addAttribute("pacientes", pacientes);
        model.addAttribute("medicos", medicos);

        return "formulario-agendamento";
    }

    @PostMapping("/editar/{id}")
    public String editarAgendamento(@PathVariable Long id, @ModelAttribute Agendamento agendamento, BindingResult result, Model model) {
        agendamento.setId(id);

        if (result.hasErrors()) {
            List<Paciente> pacientes = pacienteService.listarPacientes();
            List<Medico> medicos = medicoService.listarMedicos();
            model.addAttribute("pacientes", pacientes);
            model.addAttribute("medicos", medicos);
            return "formulario-agendamento";
        }

        agendamentoService.salvarAgendamento(agendamento);
        return "redirect:/agendamentos";
    }

    @GetMapping("/excluir/{id}")
    public String excluirAgendamento(@PathVariable Long id) {
        agendamentoService.deletarAgendamento(id);
        return "redirect:/agendamentos";
    }
}