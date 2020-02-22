package com.stone.meSalva.repositories;

import com.stone.meSalva.models.Emprestimo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmprestimoRepository extends MongoRepository<Emprestimo, String> {
    List<Emprestimo> findByCpf(String cpf);
}
