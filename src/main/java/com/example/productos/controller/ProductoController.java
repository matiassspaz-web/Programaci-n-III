package com.example.productos.controller;

import com.example.productos.model.Producto;
import com.example.productos.service.ProductoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public List<Producto> listarProductos() {
        return productoService.listarProductos();
    }

    @GetMapping("/ordenar/precio")
    public List<Producto> ordenarPorPrecio() {
        return productoService.ordenarPorPrecio();
    }

    @GetMapping("/ordenar/nombre")
    public List<Producto> ordenarPorNombre() {
        return productoService.ordenarPorNombre();
    }

    @PostMapping
    public Producto crearProducto(@RequestBody Producto producto) {
        return productoService.crearProducto(producto);
    }

    @PutMapping("/{id}/precio")
    public Producto modificarPrecio(
            @PathVariable Long id,
            @RequestParam double nuevoPrecio) {

        return productoService.modificarPrecio(id, nuevoPrecio);
    }

    @DeleteMapping("/{id}")
    public String eliminarProducto(@PathVariable Long id) {

        productoService.eliminarProducto(id);

        return "Producto eliminado correctamente";
    }
}