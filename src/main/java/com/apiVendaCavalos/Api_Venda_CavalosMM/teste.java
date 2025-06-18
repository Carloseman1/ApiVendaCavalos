package com.apiVendaCavalos.Api_Venda_CavalosMM;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class teste {

        public static void main(String[] args) throws Exception {
            String url = "https://cavalomangalarga.com.br/animalDetalhes?id=195483";
            Document doc = Jsoup.connect(url).get();
            Elements linhas = doc.select("table tr");

            for (var linha : linhas) {
                var colunas = linha.select("td");
                if (colunas.size() >= 2) {
                    System.out.println(colunas.get(0).text() + " : " + colunas.get(1).text());
                }
            }
        }
}
