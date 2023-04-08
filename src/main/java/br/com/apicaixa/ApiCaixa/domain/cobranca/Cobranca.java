package br.com.apicaixa.ApiCaixa.domain.cobranca;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "cobrancas")
@Entity(name = "Cobranca")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cobranca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String linhaDigitavel;

    private LocalDate dataVencimento;

    private Double valor;

}
