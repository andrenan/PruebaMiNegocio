package com.example.prueba.services;

import com.example.prueba.model.Cliente;
import com.example.prueba.model.Sucursal;
import com.example.prueba.repository.ClienteRepository;
import com.example.prueba.repository.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class ClienteService {
    private static final Logger LOG = Logger.getLogger(ClienteService.class.getName());
    @Autowired
    private ClienteRepository repo;
    @Autowired
    private SucursalRepository repoSucursal;


    public List<Cliente> listarClientesActivos( ) {
        List<Cliente> rw = null;
        rw = new ArrayList<>();
        try {
            rw = repo.findAll();

        } catch (Exception e) {
            rw = new ArrayList<>();
        }
        return rw;
    }

    public List<Cliente> listarClientesPorIdentiNombre(String parametro) {
        List<Cliente> rw = null;
        rw = new ArrayList<>();
        try {
            rw = repo.findByIdentificacionContainingOrNombresContaining(parametro,parametro);

        } catch (Exception e) {
            rw = new ArrayList<>();
        }
        return rw;
    }


    public Cliente actualizar(Cliente items) {
        Cliente su=null;
        su = new Cliente();
        try {
            Cliente c=null;
            Cliente busqueda= null;
            busqueda = repo.findByIdentificacion(items.getIdentificacion());
            if(busqueda!=null){
                items.setId(busqueda.getId());
                su=repo.save(items);
            }else{
                System.out.println("No se puede actualizar");
            }

        } catch (Exception e) {
            su = new Cliente();
        }
        return su;
    }


    public ResponseEntity<?> eliminar(Long items) {
        try {
          Cliente c= null;
          c = new Cliente();
          c= repo.findById(items).get();
          c.setEsActivo(Boolean.FALSE);
          repo.save(c);

        } catch (Exception e) {

        }
        return new ResponseEntity(HttpStatus.OK);
    }



}
