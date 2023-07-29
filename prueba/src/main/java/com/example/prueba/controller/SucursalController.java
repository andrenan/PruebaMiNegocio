package com.example.prueba.controller;

import com.example.prueba.model.Cliente;
import com.example.prueba.model.Sucursal;
import com.example.prueba.services.SucursalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/sucursal")
public class SucursalController {
    @Autowired
    private SucursalService serviceSucursal;

    @GetMapping("/listarSucursales")
    public ResponseEntity<?> listarClientes() {
        return new ResponseEntity<>(serviceSucursal.listarSucursales(), HttpStatus.OK);
    }

    @GetMapping("/listarSucursalesPorCLiente/{id}")
    public ResponseEntity<?> listarSucursalesPorCLiente(@PathVariable Long id) {
        Cliente c =null;
        return new ResponseEntity<>(serviceSucursal.listarSucursalesPorCliente(id), HttpStatus.OK);
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardar(@RequestBody Sucursal items) {
        System.out.println("aqui");
        return new ResponseEntity<>(serviceSucursal.guardar(items), HttpStatus.OK);
    }

    @PostMapping("/agregarDireccion")
    public ResponseEntity<?> agregarDir(@RequestBody Sucursal items) {
        System.out.println("aqui");
        return new ResponseEntity<>(serviceSucursal.agregarDir(items), HttpStatus.OK);
    }
}
