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
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome e obrigatorio")
    private String nome;

    @NotBlank(message = "O CPF e obrigatorio")
    @Pattern(regexp = "\\d{11}", message = "CPF inválido")
    @Column(unique=true)
    private String cpf;

    @NotBlank(message = "O telefone e obrigatorio")
    @Pattern(regexp = "\\d{10,11}", message = "Telefone invalido")
    @Column(unique=true)
    private String telefone;


    @NotBlank(message = "Informe seu peso")
    private String peso;


    @NotBlank(message = "Informe sua altura")
    private String altura;


    private String tomaMedicamento;





}
