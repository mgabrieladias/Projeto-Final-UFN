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

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotBlank(message = "A especialidade é obrigatória")
    private String especialidade;

    @NotBlank(message = "O CPF é obrigatório")
    @Pattern(regexp = "\\d{11}", message = "O CPF precisa conter 11 dígitos")
    @Column(unique=true)
    private String cpf;

    @NotBlank(message = "O telefone é obrigatório")
    @Pattern(regexp = "\\d{10,11}", message = "O Telefone precisa conter 11 dígitos")
    @Column(unique=true)
    private String telefone;

    @NotBlank(message = "O CRM é obrigatório")
    @Pattern(regexp = "\\d{6}", message = "O CRM precisa conter 6 dígitos")
    @Column(unique=true)
    private String crm;

}