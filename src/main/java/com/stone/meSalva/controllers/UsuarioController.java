package com.stone.meSalva.controllers;

import com.stone.meSalva.models.Estabelecimento;
import com.stone.meSalva.models.Usuario;
import com.stone.meSalva.repositories.EstabelecimentoRepository;
import com.stone.meSalva.repositories.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/all")
    public List<Usuario> getAll() {
        List<Usuario> usuarios = this.usuarioRepository.findAll();
        return usuarios;
    }

    @GetMapping("/{cpf}")
    public Usuario findByCpf(@PathVariable("cpf") String cpf) {
        Usuario usuario = this.usuarioRepository.findByCpf(cpf);
        if (usuario == null) {
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi encontrado nenhum usuário com este CPF.");
        }
        return usuario;
    }

    @PutMapping
    public void insert(@RequestBody Usuario usuario) {
        String cpf = usuario.getCpf();
        Usuario usuarioExistente = this.usuarioRepository.findByCpf(cpf);
        if (usuarioExistente == null) {
            this.usuarioRepository.insert(usuario);
        } else {
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "Já existe um usário com este CPF.");
        }
    }

    @PostMapping
    public Usuario update(@RequestBody Usuario usuario) {
        String cpf = usuario.getCpf();
        Usuario usuarioExistente = this.usuarioRepository.findByCpf(cpf);
        if (usuarioExistente == null) {
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi encontrado nenhum usuário com este CPF.");
        } else {
            usuario.setId(usuarioExistente.getId());
            return this.usuarioRepository.save(usuario);
        }
    }

    @DeleteMapping("/{cpf}")
    public void delete(@PathVariable("cpf") String cpf) {
        Usuario usuario = this.usuarioRepository.findByCpf(cpf);
        if (usuario == null) {
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi encontrado nenhum usuário com este CPF.");
        } else {
            this.usuarioRepository.delete(usuario);
        }
    }

    public Optional<Usuario> findByUsername(@PathVariable("username") String username) {
        return this.usuarioRepository.findByUsername(username);
    }
}
