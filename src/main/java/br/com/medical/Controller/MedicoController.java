package br.com.medical.Controller;

import br.com.medical.Entity.Medico;
import br.com.medical.Service.MedicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @GetMapping
    public String listarMedicos(Model model) {
        List<Medico> medicos = medicoService.listarMedicos();
        model.addAttribute("medicos", medicos);
        return "medicos";
    }

    @GetMapping("/{id}")
    public String exibirMedico(@PathVariable Long id, Model model) {
        Medico medico = medicoService.obterMedicoPorId(id);
        model.addAttribute("medico", medico);
        return "medico-detalhes";
    }

    @GetMapping("/novo")
    public String mostrarFormularioNovoMedico(Model model) {
        model.addAttribute("medico", new Medico());
        return "formulario-medico";
    }

    @PostMapping("/novo")
    public String salvarMedico(@Valid @ModelAttribute Medico medico, BindingResult result) {
        if (result.hasErrors()) {
            return "formulario-medico";
        }

        try {
            medicoService.salvarMedico(medico);
        } catch (RuntimeException e) {
            result.rejectValue("crm", null, e.getMessage());
            return "formulario-medico";
        }

        return "redirect:/medicos";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarMedico(@Valid @PathVariable Long id, Model model) {
        Medico medico = medicoService.obterMedicoPorId(id);
        model.addAttribute("medico", medico);
        return "formulario-medico";
    }

    @PostMapping("/editar/{id}")
    public String editarMedico(@Valid @PathVariable Long id, @ModelAttribute Medico medico, BindingResult result) {
        medico.setId(id);

        if (result.hasErrors()) {
            return "formulario-medico";
        }

        try {
            medicoService.salvarMedico(medico);
        } catch (RuntimeException e) {
            result.rejectValue("crm", null, e.getMessage()); // Adiciona erro ao campo CRM
            return "formulario-medico";
        }

        return "redirect:/medicos";
    }

    @GetMapping("/excluir/{id}")
    public String excluirMedico(@PathVariable Long id) {
        medicoService.deletarMedico(id);
        return "redirect:/medicos";
    }
}