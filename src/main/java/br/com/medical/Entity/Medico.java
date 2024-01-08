package br.com.medical.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome e obrigatorio")
    private String nome;

    @NotBlank(message = "A especialidade e obrigatoria")
    private String especialidade;

    @NotBlank(message = "O CPF e obrigatorio")
    @Pattern(regexp = "\\d{11}", message = "CPF inválido")
    @Column(unique=true)
    private String cpf;

    @NotBlank(message = "O telefone e obrigatorio")
    @Pattern(regexp = "\\d{10,11}", message = "Telefone invalido")
    @Column(unique=true)
    private String telefone;

    @NotBlank(message = "O CRM e obrigatorio")
    @Pattern(regexp = "\\d{6}", message = "CRM invalido")
    @Column(unique=true)
    private String crm;

}