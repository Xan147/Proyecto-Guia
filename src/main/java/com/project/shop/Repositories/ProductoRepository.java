package com.project.shop.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.shop.Models.Productos;

public interface ProductoRepository extends MongoRepository<Productos, String> {

}
