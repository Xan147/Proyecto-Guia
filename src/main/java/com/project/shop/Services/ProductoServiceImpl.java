package com.project.shop.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.shop.Dtos.ProductoDto;
import com.project.shop.Mapper.ProductoMapper;
import com.project.shop.Models.Producto;
import com.project.shop.Repositories.ProductoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService{

    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;

    //Obtener todos los productos activos
    @Override
    public List<ProductoDto> obtenerAllActivos() {
        return productoMapper.toProductoDtoList(
            productoRepository.findByActivoTrue()
        );
    }

    //Consultar producto por nombre
    @Override
    public List<ProductoDto> obtenerPorNombre(String nombre) {
        return productoMapper.toProductoDtoList(
            productoRepository.findByNombreContainingIgnoreCaseAndActivoTrue(nombre)
        );
    }

    //Consultar producto por id
    @Override
    public ProductoDto obtenerPorId(String id) {
        Producto producto = productoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado para consultar ("+ id +")"));
        return productoMapper.toProductoDto(producto);
    }

    //Crear un nuevo producto
    @Override
    public ProductoDto crearProducto(ProductoDto productoDto) {
        if (productoRepository.existsByNombre(productoDto.getName())) {
            throw new RuntimeException("Ya existe un producto con ese nombre ("+ productoDto.getName() +")");
        }
        Producto producto = productoMapper.toProducto(productoDto);
        return productoMapper.toProductoDto(productoRepository.save(producto));
    }

    //Actualizar un producto ya existente
    @Override
    public ProductoDto actualizar(String id, ProductoDto productoDto) {
        Producto producto = productoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado para actualizar ("+ id +")"));
        productoMapper.actualizarProducto(productoDto, producto);
        return productoMapper.toProductoDto(productoRepository.save(producto));
    }

    //Eliminar un producto (cambiara su campo de Activo a False)
    @Override
    public void eliminar(String id) {
        Producto producto = productoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado para eliminar ("+ id +")"));
        producto.setActivo(false);
        productoRepository.save(producto);
    }

}
