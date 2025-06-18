package com.apiVendaCavalos.Api_Venda_CavalosMM.Controller;

import com.apiVendaCavalos.Api_Venda_CavalosMM.Entity.ClienteEntity;
import com.apiVendaCavalos.Api_Venda_CavalosMM.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping("/cadastrar")
    public ResponseEntity<ClienteEntity> cadastrarCliente(@RequestBody ClienteEntity cliente) {
        ClienteEntity salvo = clienteRepository.save(cliente);
        return ResponseEntity.ok(salvo);
    }

    @GetMapping("/todos")
    public List<ClienteEntity> listarTodosClientes() {
        return clienteRepository.findAll();
    }
}
