package com.HelpPro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;

import com.HelpPro.model.Pagamento;
import com.HelpPro.service.PagamentoService;

import java.util.List;

@RestController
@RequestMapping("/pagamentos")

public class PagamentoController {

     @Autowired
    private PagamentoService pagamentoService;

    @GetMapping
    public List<Pagamento> getAllPagamentos() {
        return pagamentoService.getAllPagamentos();
    }

    @GetMapping("/{id}")
    public Pagamento getPagamentoById(@PathVariable Long id) {
        return pagamentoService.getPagamentoById(id);
    }

    @PostMapping
    public Pagamento createPagamento(@RequestBody Pagamento pagamento) {
        return pagamentoService.savePagamento(pagamento);
    }

    @PutMapping("/{id}")
    public Pagamento updatePagamento(@PathVariable Long id, @RequestBody Pagamento pagamento) {
        pagamento.setId(id);
        return pagamentoService.savePagamento(pagamento);
    }

    @DeleteMapping("/{id}")
    public void deletePagamento(@PathVariable Long id) {
        pagamentoService.deletePagamento(id);
    }
    
}
