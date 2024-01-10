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

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotBlank(message = "O CPF é obrigatório")
    @Pattern(regexp = "\\d{11}", message = "O CPF precisa conter 11 dígitos")
    @Column(unique=true)
    private String cpf;

    @NotBlank(message = "O telefone é obrigatório")
    @Pattern(regexp = "\\d{10,11}", message = "O Telefone precisa conter 11 dígitos")
    @Column(unique=true)
    private String telefone;


    @NotBlank(message = "Informe o seu peso")
    private String peso;


    @NotBlank(message = "Informe a sua altura")
    private String altura;


    private String tomaMedicamento;





}