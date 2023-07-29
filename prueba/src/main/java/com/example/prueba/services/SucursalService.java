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
public class SucursalService {
    private static final Logger LOG = Logger.getLogger(SucursalService.class.getName());

    @Autowired
    private SucursalRepository repoSucursal;

    @Autowired
    private ClienteRepository repoCliente;


    public Sucursal obtenerSucursal(Cliente parametro) {
        System.out.println(parametro);
        Sucursal rw = null;
        rw = new Sucursal();
        try {
            rw = repoSucursal.findByClienteAndEsMatriz(parametro,true);
            System.out.println(rw);
        }catch (Exception e){
            rw = new Sucursal();
        }
        return rw;
    }

    public List<Sucursal> listarSucursales( ) {
        List<Sucursal> rw = null;
        rw = new ArrayList<>();
        try {
            rw = repoSucursal.findAll();

        } catch (Exception e) {
            rw = new ArrayList<>();
        }
        return rw;
    }

    public List<Sucursal> listarSucursalesPorCliente(Long cli) {
        List<Sucursal> rw = null;
        rw = new ArrayList<>();
        try {
            Cliente c = new Cliente();
            c = repoCliente.findById(cli).get();
            rw = repoSucursal.findByCliente(c);

        } catch (Exception e) {
            rw = new ArrayList<>();
        }
        return rw;
    }

    public Cliente guardar(Sucursal items) {
        Sucursal su=null;
        su = new Sucursal();
        try {
            Cliente c=null;
            Cliente busqueda= null;
            busqueda = repoCliente.findByIdentificacion(items.getCliente().getIdentificacion());
            if(busqueda!=null){
                System.out.println("No se puede crear, duplicidad de identificaciones");
            }else{
                c= new Cliente();
                c = repoCliente.save(items.getCliente());
                items.setCliente(c);
                su=repoSucursal.save(items);
            }

        } catch (Exception e) {
            su = new Sucursal();
        }
        return su.getCliente();
    }


    public ResponseEntity<?> agregarDir(Sucursal items) {
        try {
            Sucursal s = new Sucursal();
            s= repoSucursal.findByClienteAndEsMatriz(items.getCliente(), true);
            if(s!=null){
                repoSucursal.save(items);

            }else{
                System.out.println("No existe matriz");
            }

        } catch (Exception e) {

        }
        return new ResponseEntity(HttpStatus.OK);
    }



}
