package com.example.apirestretrofitst.controller;

import com.example.apirestretrofitst.dto.ProductoDTO;
import com.example.apirestretrofitst.model.Producto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@RestController

@CrossOrigin
@RequestMapping("/producto")
public class ProductoController {
    private List<Producto> productos = new ArrayList<>(Arrays.asList(
            new Producto(1, "producto1", 100),
            new Producto(2, "producto2", 200),
            new Producto(3, "producto3", 300),
            new Producto(4, "producot4", 400)
    ));


    @GetMapping
    public ResponseEntity<List<Producto>> getAll() {
        return ResponseEntity.ok(productos);
    }

    @GetMapping("{id}")
    public ResponseEntity<Producto> getOne(@PathVariable ("id") int  id){
        Producto producto = findById(id);
        return ResponseEntity.ok(producto);
    }

    @PostMapping
    public ResponseEntity<Producto> create(@RequestBody ProductoDTO dto) {
        int index = productos.isEmpty()? 1 : getLastIndex() + 1;
        Producto producto = Producto.builder().id(index).nombre(dto.getNombre()).precio(dto.getPrecio()).build();
        productos.add(producto);
        return ResponseEntity.ok(producto);
    }
    
@PutMapping("{id}")
    public ResponseEntity<Producto> update(@PathVariable("id") int id, @RequestBody ProductoDTO dto){
        Producto producto = findById(id);
        producto.setNombre(dto.getNombre());
        producto.setPrecio(dto.getPrecio());
        return ResponseEntity.ok(producto);
}

    @DeleteMapping("{id}")
    public ResponseEntity<Producto> delete(@PathVariable("id") int id) {
        Producto producto = findById(id);
        productos.remove(producto);
        return ResponseEntity.ok(producto);
    }
    private int getLastIndex() {
        return productos.stream().max(Comparator.comparing(Producto::getId)).get().getId();
    }
    private Producto findById(int id) {
        return productos.stream().filter(p -> p.getId() == id).findAny().orElse(null);
    }


}
