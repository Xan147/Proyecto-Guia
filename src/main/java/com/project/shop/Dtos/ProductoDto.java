package com.project.shop.Dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductoDto {
    private String id;
    private String name;
    private String description;
    private double price;
    private int existence; //El stock
    private String category;
}
