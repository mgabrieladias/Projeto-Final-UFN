package br.com.medical.Controller;

import br.com.medical.Entity.Paciente;
import br.com.medical.Service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public String listarPacientes(Model model) {
        List<Paciente> pacientes = pacienteService.listarPacientes();
        model.addAttribute("pacientes", pacientes);

        // Adicione este log para verificar o template que está sendo resolvido
        System.out.println("Resolving template for listarPacientes: pacientes");

        return "pacientes";
    }

    @GetMapping("/{id}")
    public String exibirPaciente(@PathVariable Long id, Model model) {
        Paciente paciente = pacienteService.obterPacientePorId(id);
        model.addAttribute("paciente", paciente);
        return "paciente-detalhes";
    }

    @GetMapping("/novo")
    public String mostrarFormularioNovoPaciente(Model model) {
        model.addAttribute("paciente", new Paciente());
        return "formulario-paciente";
    }

    @PostMapping("/novo")
    public String salvarPaciente(@ModelAttribute Paciente paciente, BindingResult result) {
        if (result.hasErrors()) {
            return "formulario-paciente";
        }

        try {
            pacienteService.salvarPaciente(paciente);
        } catch (RuntimeException e) {
            result.rejectValue("cpf", null, e.getMessage());
            return "formulario-paciente";
        }

        return "redirect:/pacientes";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarPaciente(@PathVariable Long id, Model model) {
        Paciente paciente = pacienteService.obterPacientePorId(id);
        model.addAttribute("paciente", paciente);
        return "formulario-paciente";
    }

    @PostMapping("/editar/{id}")
    public String editarPaciente(@PathVariable Long id, @ModelAttribute Paciente paciente, BindingResult result) {
        paciente.setId(id);

        if (result.hasErrors()) {
            return "formulario-paciente";
        }

        try {
            pacienteService.salvarPaciente(paciente);
        } catch (RuntimeException e) {
            result.rejectValue("cpf", null, e.getMessage());
            return "formulario-paciente";
        }

        return "redirect:/pacientes";
    }

    @GetMapping("/excluir/{id}")
    public String excluirPaciente(@PathVariable Long id) {
        pacienteService.deletarPaciente(id);
        return "redirect:/pacientes";
    }
}
