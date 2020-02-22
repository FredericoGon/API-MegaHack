package com.stone.meSalva.repositories;

import com.stone.meSalva.models.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    Usuario findByCpf(String cpf);

    Optional<Usuario> findByUsername(String username);
}
