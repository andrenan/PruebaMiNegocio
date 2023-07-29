package com.example.prueba.repository;

import com.example.prueba.model.Cliente;
import com.example.prueba.model.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SucursalRepository extends JpaRepository<Sucursal, Long> {
    public Sucursal findByClienteAndEsMatriz(Cliente cliente,Boolean esMatriz);

    public List<Sucursal> findByCliente(Cliente cliente);
}
