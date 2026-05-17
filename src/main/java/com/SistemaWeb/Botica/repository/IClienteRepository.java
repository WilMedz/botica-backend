package com.SistemaWeb.Botica.repository;

import com.SistemaWeb.Botica.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClienteRepository extends JpaRepository<Cliente, Integer> {

}