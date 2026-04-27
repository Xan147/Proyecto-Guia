package com.project.shop.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "productos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Productos {
    @Id
    private String id;
    private String nombreProducto;
    private String descripcion;
    private double precio;
    private int stock;

}
