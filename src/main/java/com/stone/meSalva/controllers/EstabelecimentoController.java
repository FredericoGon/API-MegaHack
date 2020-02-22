package com.stone.meSalva.controllers;

import com.stone.meSalva.models.Emprestimo;
import com.stone.meSalva.models.Estabelecimento;
import com.stone.meSalva.models.Usuario;
import com.stone.meSalva.repositories.EmprestimoRepository;
import com.stone.meSalva.repositories.EstabelecimentoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/estabelecimentos")
public class EstabelecimentoController {
    private EstabelecimentoRepository estabelecimentoRepository;

    public EstabelecimentoController(EstabelecimentoRepository estabelecimentoRepository) {
        this.estabelecimentoRepository = estabelecimentoRepository;
    }

    @GetMapping("/all")
    public List<Estabelecimento> getAll() {
        List<Estabelecimento> estabelecimentos = this.estabelecimentoRepository.findAll();
        return estabelecimentos;
    }

    @PutMapping
    public void insert(@RequestBody Estabelecimento estabelecimento) {
        String cnpj = estabelecimento.getCnpj();
        Estabelecimento estabelecimentoExistente = this.estabelecimentoRepository.findByCnpj(cnpj);
        if (estabelecimentoExistente == null) {
            this.estabelecimentoRepository.insert(estabelecimento);
        } else {
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "Já existe um estabelecimento com este CNPJ.");
        }
    }

    @GetMapping("/{cnpj}")
    public Estabelecimento findByCnpj(@PathVariable("cnpj") String cnpj) {
        Estabelecimento estabelecimento = this.estabelecimentoRepository.findByCnpj(cnpj);
        if (estabelecimento == null) {
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi encontrado nenhum estabelecimento com este CNPJ.");
        }
        return estabelecimento;
    }

    @PostMapping
    public Estabelecimento update(@RequestBody Estabelecimento estabelecimento) {
        String cnpj = estabelecimento.getCnpj();
        Estabelecimento estabelecimentoExistente = this.estabelecimentoRepository.findByCnpj(cnpj);
        if (estabelecimentoExistente == null) {
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi encontrado nenhum estabelecimento com este CNPJ.");
        } else {
            estabelecimento.setId(estabelecimentoExistente.getId());
            return this.estabelecimentoRepository.save(estabelecimento);
        }
    }

    @DeleteMapping("/{cnpj}")
    public void delete(@PathVariable("cnpj") String cnpj) {
        Estabelecimento estabelecimento = this.estabelecimentoRepository.findByCnpj(cnpj);
        if (estabelecimento == null) {
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi encontrado nenhum estabelecimento com este CNPJ.");
        } else {
            this.estabelecimentoRepository.delete(estabelecimento);
        }
    }
}
