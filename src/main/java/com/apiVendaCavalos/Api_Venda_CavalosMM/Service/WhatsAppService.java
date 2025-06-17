package com.apiVendaCavalos.Api_Venda_CavalosMM.Service;

import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class WhatsAppService {

    public String gerarLinkWhatsApp(String telefone, String mensagem) {
        return "https://wa.me/" + telefone.replaceAll("\\D", "") + "?text=" + URLEncoder.encode(mensagem, StandardCharsets.UTF_8);
    }
}

