package com.example.prueba.controller;

import com.example.prueba.model.Cliente;
import com.example.prueba.model.Sucursal;
import com.example.prueba.repository.ClienteRepository;
import com.example.prueba.services.ClienteService;
import com.example.prueba.services.SucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @Autowired
    private SucursalService serviceSucursal;

    @GetMapping("/listarClientes")
    public ResponseEntity<?> listarClientes() {
        return new ResponseEntity<>(service.listarClientesActivos(), HttpStatus.OK);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {

        System.out.println(id);
        List<Cliente> usuario = service.listarClientesPorIdentiNombre(id);

        if(usuario != null){
            for(Cliente u: usuario){
                System.out.println(u.getId());
                Sucursal s= serviceSucursal.obtenerSucursal(u);
                if(s!=null){
                    u.setDireccionmatriz(s.getProvincia()+"-"+s.getCiudad()+"-"+s.getDireccion());
                }

            }
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        }
        else{
            usuario = new ArrayList<>();
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        }
    }

    @PostMapping("/actualizar")
    public ResponseEntity<?> guardar(@RequestBody Cliente items) {
        System.out.println("aqui");
        return new ResponseEntity<>(service.actualizar(items), HttpStatus.OK);
    }

    @GetMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {


            return new ResponseEntity<>(service.eliminar(id),HttpStatus.OK);

    }
}
