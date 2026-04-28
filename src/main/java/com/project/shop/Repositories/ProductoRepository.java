package com.project.shop.Repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.shop.Models.Producto;

public interface ProductoRepository extends MongoRepository<Producto, String> {
    List<Producto> findByActivoTrue();
    List<Producto> findByNombreContainingIgnoreCaseAndActivoTrue(String nombre);

    boolean existsByNombre(String nombre);
}
