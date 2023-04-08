package br.com.apicaixa.ApiCaixa.domain.cobranca;

import br.com.apicaixa.ApiCaixa.domain.pagamento.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CobrancaRepository extends JpaRepository<Cobranca, Long> {
}
