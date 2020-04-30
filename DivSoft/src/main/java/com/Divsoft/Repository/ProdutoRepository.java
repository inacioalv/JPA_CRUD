package com.Divsoft.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.Divsoft.Entidade.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
