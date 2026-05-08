package com.project.shop.Mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.project.shop.Dtos.ProductoDto;
import com.project.shop.Models.Producto;

@Component
public class ProductoMapperImpl implements ProductoMapper{
    //Dto a Modelo
    @Override
    public Producto toProducto(ProductoDto productoDto) {
        if (productoDto == null) return null;

        return Producto.builder()
            .nombre(productoDto.getName())
            .descripcion(productoDto.getDescription())
            .precio(productoDto.getPrice())
            .stock(productoDto.getExistence())
            .categoria(productoDto.getCategory())
            .activo(true)
            .build();
    }

    //Modelo a Dto
    @Override
    public ProductoDto toProductoDto(Producto producto) {
        if (producto == null) return null;

        return ProductoDto.builder()
            .id(producto.getId())
            .name(producto.getNombre())
            .description(producto.getDescripcion())
            .price(producto.getPrecio())
            .existence(producto.getStock())
            .category(producto.getCategoria())
            .build();
    }

    @Override
    public List<ProductoDto> toProductoDtoList(List<Producto> productos) {
        if (productos == null) return null;

        return productos.stream()
            .map(this::toProductoDto)
            .collect(Collectors.toList());
    }

    @Override
    public void actualizarProducto(ProductoDto productoDto, Producto producto) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto está vacío");
        }
        if (productoDto == null) {
            throw new IllegalArgumentException("El DTO está vacío");
        }

        if (productoDto.getName() != null) producto.setNombre(productoDto.getName());
        if (productoDto.getDescription() != null) producto.setDescripcion(productoDto.getDescription());
        if (productoDto.getPrice() != 0) producto.setPrecio(productoDto.getPrice()); // double no puede ser null
        if (productoDto.getExistence() != 0) producto.setStock(productoDto.getExistence());     // int no puede ser null
        if (productoDto.getCategory() != null) producto.setCategoria(productoDto.getCategory());
}

}
