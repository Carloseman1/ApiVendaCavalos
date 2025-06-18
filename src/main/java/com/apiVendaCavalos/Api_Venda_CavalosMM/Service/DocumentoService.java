package com.apiVendaCavalos.Api_Venda_CavalosMM.Service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;


@Service
public class DocumentoService {

    public Optional<Map<String, String>> buscarDocumentoDetalhado(String numeroDocumento) {
        try {
            String detalheUrl = "https://cavalomangalarga.com.br/animalDetalhes?id=" + URLEncoder.encode(numeroDocumento, StandardCharsets.UTF_8);


            Document doc = Jsoup.connect(detalheUrl).get();
            System.out.println(doc.html());

            Elements linhas = doc.select("table.info-table tr");

            Map<String, String> dados = new LinkedHashMap<>();

            for (var linha : linhas) {
                var colunas = linha.select("table.info-table tr");
                System.out.println("Colunas= " + colunas);
                if (colunas.size() >= 2) {
                    String chave = colunas.get(0).text().trim();
                    String valor = colunas.get(1).text().trim();
                    dados.put(chave, valor);
                }
            }

            dados.put("documentoUrl", detalheUrl);
            dados.put("Linhas",linhas.text());
            System.out.println(dados.put("documentoUrl", detalheUrl));



            return Optional.of(dados);

        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
