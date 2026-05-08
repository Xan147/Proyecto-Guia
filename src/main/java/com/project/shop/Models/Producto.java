package com.project.shop.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "productos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Producto {
    @Id
    private String id;
    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;
    private String categoria;
    @Builder.Default
    private boolean activo = true;
}
