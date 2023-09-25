package com.example.apirestretrofitst.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class ProductoDTO {
    private String nombre;
    private int precio;

    public String getNombre() {
        return nombre;
    }

    public int getPrecio() {
        return precio;
    }
}
