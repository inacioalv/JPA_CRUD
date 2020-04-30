package com.Divsoft.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Divsoft.Entidade.Estoque;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Long> {

}
