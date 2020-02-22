package com.stone.meSalva.repositories;

import com.stone.meSalva.models.Estabelecimento;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstabelecimentoRepository extends MongoRepository<Estabelecimento, String> {
    Estabelecimento findByCnpj(String cnpj);
}
