package com.project.shop.Services;

import java.util.List;

import com.project.shop.Dtos.ProductoDto;

public interface ProductoService {

    //Metodos de cliente
    List<ProductoDto> obtenerAllActivos();
    List<ProductoDto> obtenerPorNombre(String nombre);
    ProductoDto obtenerPorId(String id);

    //Metodos de admin
    ProductoDto crearProducto(ProductoDto productoDto);
    ProductoDto actualizar(String id, ProductoDto productoDto);
    void eliminar(String id);
}
