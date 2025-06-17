package com.apiVendaCavalos.Api_Venda_CavalosMM.Controller;

import com.apiVendaCavalos.Api_Venda_CavalosMM.Service.WhatsAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contato")
public class ContatoController {

    @Autowired
    private WhatsAppService whatsAppService;

    @GetMapping("/whatsapp")
    public ResponseEntity<String> gerarLink(@RequestParam String telefone, @RequestParam String mensagem) {
        String link = whatsAppService.gerarLinkWhatsApp(telefone, mensagem);
        return ResponseEntity.ok(link);
    }
}
