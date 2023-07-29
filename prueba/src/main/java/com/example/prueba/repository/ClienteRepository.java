package com.example.prueba.repository;

import com.example.prueba.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    public List<Cliente> findByIdentificacionContainingOrNombresContaining(String identificacion,String nombres);


    public Cliente findByIdentificacion(String identificacion);



}
