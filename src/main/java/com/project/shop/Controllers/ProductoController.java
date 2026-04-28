package com.project.shop.Controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.shop.Dtos.ProductoDto;
import com.project.shop.Services.ProductoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    //Obtener todos los productos activos
    @GetMapping
    public ResponseEntity<List<ProductoDto>> obtenerAllActivos() {
        return ResponseEntity.ok(productoService.obtenerAllActivos());
    }

    //Consultar producto por id
    @GetMapping("/id/{id}")
    public ResponseEntity<ProductoDto> obtenerPorId(@PathVariable String id) {
        return ResponseEntity.ok(productoService.obtenerPorId(id));
    }

    //Consultar producto por nombre
    @GetMapping("/{nombre}")
    public ResponseEntity<List<ProductoDto>> obtenerPorNombre(@PathVariable String nombre) {
        return ResponseEntity.ok(productoService.obtenerPorNombre(nombre));
    }

    //Crear un nuevo producto
    @PostMapping
    public ResponseEntity<ProductoDto> crearProducto(@RequestBody ProductoDto productoDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.crearProducto(productoDto));
    }

    //Actualizar un producto ya existente
    @PutMapping("/{id}")
    public ResponseEntity<ProductoDto> actualizar(@PathVariable String id, @RequestBody ProductoDto productoDto) {
        return ResponseEntity.ok(productoService.actualizar(id, productoDto));
    }

    //Eliminar un producto (desactivarlo)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
