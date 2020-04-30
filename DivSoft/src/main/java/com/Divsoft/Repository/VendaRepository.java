package com.Divsoft.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Divsoft.Entidade.Venda;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {

}
