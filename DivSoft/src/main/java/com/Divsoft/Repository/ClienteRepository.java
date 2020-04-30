package com.Divsoft.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Divsoft.Entidade.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
