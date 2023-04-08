package br.com.apicaixa.ApiCaixa.controller;

import br.com.apicaixa.ApiCaixa.domain.cobranca.Cobranca;
import br.com.apicaixa.ApiCaixa.domain.cobranca.CobrancaRepository;
import br.com.apicaixa.ApiCaixa.domain.pagamento.Pagamento;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;


@RestController
@RequestMapping("/cobrancas")
public class CobrancaController {

    @Autowired
    private CobrancaRepository cobrancaRepository;

    @Autowired
    private ResourceLoader resourceLoader;

    @Operation(
            summary = "Obtem a remessa, faz a leitura dos dados e salva-os no banco",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Remessa lida com sucesso!"),
                    @ApiResponse(responseCode = "400", description = "Remessa Invalida")
            }
    )
    @GetMapping
    public ResponseEntity<String> lerArquivo(@RequestParam("nomeArquivo") String nomeArquivo) throws IOException {
        Resource resource = resourceLoader.getResource("classpath:" + nomeArquivo);
//        File arquivo = resource.getFile();
        try (BufferedReader br = new BufferedReader(new FileReader(resource.getFile()))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dadosLinha = linha.split(",");
                Cobranca cobranca = new Cobranca();
                cobranca.setNome(dadosLinha[0]);
                cobranca.setDataVencimento(LocalDate.parse(dadosLinha[2]));
                cobranca.setValor(Double.valueOf(dadosLinha[3]));
                cobrancaRepository.save(cobranca);
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao ler o arquivo");
        }
        return ResponseEntity.ok("Arquivo lido e entidades salvas com sucesso!");
    }

}
