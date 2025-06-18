package com.apiVendaCavalos.Api_Venda_CavalosMM.Controller;

import com.apiVendaCavalos.Api_Venda_CavalosMM.Entity.CavaloEntity;
import com.apiVendaCavalos.Api_Venda_CavalosMM.Repository.CavaloRepository;
import com.apiVendaCavalos.Api_Venda_CavalosMM.Service.DocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/cavalos")
public class CavaloController {

    @Autowired
    private CavaloRepository Repository;

    @Autowired
    private DocumentoService documentoService;

    @PostMapping("/cadastrar")
    public ResponseEntity<CavaloEntity> cadastrar(@RequestBody CavaloEntity cavalo) {
        CavaloEntity salvo = Repository.save(cavalo);
        return ResponseEntity.ok(salvo);
    }

    @GetMapping("/disponiveis")
    public List<CavaloEntity> listarDisponiveis() {
        return Repository.findByVendidoFalse();
    }

    @PostMapping("/vender/{id}")
    public ResponseEntity<?> vender(@PathVariable Long id) {
        Optional<CavaloEntity> cavaloOpt = Repository.findById(id);
        if (cavaloOpt.isEmpty()) return ResponseEntity.notFound().build();

        CavaloEntity cavalo = cavaloOpt.get();
        Optional<Map<String, String>> dadosDoc = documentoService.buscarDocumentoDetalhado(cavalo.getDocumento());

        Map<String, Object> dadosVenda = new HashMap<>();
        dadosVenda.put("preco", cavalo.getPreco());
        dadosVenda.put("documento", dadosDoc.orElse(Map.of("erro", "Documento não encontrado")));
        dadosVenda.put("dados", cavalo);

        return ResponseEntity.ok(dadosVenda);
    }
    @GetMapping("/test")
    public String teste() {
        return "API funcionando!";
    }


    @PostMapping("/comprar/{id}")
    public ResponseEntity<?> comprar(@PathVariable Long id, @RequestParam String metodoPagamento) {
        Optional<CavaloEntity> cavaloOpt = Repository.findById(id);
        if (cavaloOpt.isEmpty()) return ResponseEntity.notFound().build();

        CavaloEntity cavalo = cavaloOpt.get();
        if (cavalo.isVendido()) return ResponseEntity.status(HttpStatus.CONFLICT).body("Cavalo já vendido");

        cavalo.setVendido(true);
        Repository.save(cavalo);

        BigDecimal valorTotal = cavalo.getPreco();
        BigDecimal comissao = valorTotal.multiply(BigDecimal.valueOf(0.1));
        BigDecimal valorVendedor = valorTotal.subtract(comissao);

        Map<String, Object> resultado = new HashMap<>();
        resultado.put("valorTotal", valorTotal);
        resultado.put("comissao", comissao);
        resultado.put("valorVendedor", valorVendedor);
        resultado.put("pagamento", metodoPagamento);

        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/documentos")
    public ResponseEntity<?> buscarDocumento(@RequestParam String numeroDocumento) {
        System.out.println("Recebido numeroDocumento: " + numeroDocumento);
        Optional<Map<String, String>> dadosDoc = documentoService.buscarDocumentoDetalhado(numeroDocumento);
        if (dadosDoc.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dadosDoc.get());
    }


    @GetMapping("/todos")
    public List<CavaloEntity> listarTodos() {
        return Repository.findAll();
    }
}
