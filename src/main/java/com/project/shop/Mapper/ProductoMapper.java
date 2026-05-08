package com.project.shop.Mapper;

import java.util.List;

import com.project.shop.Dtos.ProductoDto;
import com.project.shop.Models.Producto;

public interface ProductoMapper {
    Producto toProducto(ProductoDto productoDto);
    ProductoDto toProductoDto(Producto producto);
    List<ProductoDto> toProductoDtoList(List<Producto> productos);
    void actualizarProducto(ProductoDto productoDto, Producto producto);
}
