package com.stone.meSalva.controllers;

import com.stone.meSalva.models.Emprestimo;
import com.stone.meSalva.repositories.EmprestimoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {
    private EmprestimoRepository emprestimoRepository;

    public EmprestimoController(EmprestimoRepository emprestimoRepository) {
        this.emprestimoRepository = emprestimoRepository;
    }

    @GetMapping("/all")
    public List<Emprestimo> getAll() {
        List<Emprestimo> emprestimos = this.emprestimoRepository.findAll();
        return emprestimos;
    }

    @PutMapping
    public void insert(@RequestBody Emprestimo emprestimo) {
        this.emprestimoRepository.insert(emprestimo);
    }

    @PostMapping
    public void update(@RequestBody Emprestimo emprestimo) {
        this.emprestimoRepository.save(emprestimo);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        this.emprestimoRepository.deleteById(id);
    }

    @GetMapping("/findbycpf/{cpf}")
    public List<Emprestimo> findByCpf(@PathVariable("cpf") String cpf) {
        List<Emprestimo> emprestimos = this.emprestimoRepository.findByCpf(cpf);
        if (emprestimos.isEmpty()) {
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi encontrado nenhum emprestimo com este CPF.");
        } else {
            return emprestimos;
        }
    }
}
