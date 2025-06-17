package com.apiVendaCavalos.Api_Venda_CavalosMM.Service;

import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

@Service
public class DocumentoService {

    public Optional<Map<String, String>> buscarDocumentoDetalhado(String numeroDocumento) {
        try {
            String buscaUrl = "https://cavalomangalarga.com.br/animal?tipo=A&padrao=" + URLEncoder.encode(numeroDocumento, StandardCharsets.UTF_8);
            Document busca = Jsoup.connect(buscaUrl).get();
            Elements link = busca.select("a[href*='/animalDetalhes?id=']");

            if (link.isEmpty()) return Optional.empty();

            String detalheHref = link.attr("href");
            String detalheUrl = "https://cavalomangalarga.com.br" + detalheHref;
            Document detalhe = Jsoup.connect(detalheUrl).get();

            Elements linhas = detalhe.select("table tr");
            Map<String, String> dados = new LinkedHashMap<>();

            for (var linha : linhas) {
                var colunas = linha.select("td");
                if (colunas.size() >= 2) {
                    dados.put(colunas.get(0).text(), colunas.get(1).text());
                }
            }
            dados.put("documentoUrl", detalheUrl);
            return Optional.of(dados);

        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
