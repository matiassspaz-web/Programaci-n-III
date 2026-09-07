package com.example.productos.service;

import com.example.productos.model.Producto;
import com.example.productos.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public Producto crearProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }
    public Producto buscarPorId(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    public List<Producto> ordenarPorPrecio() {
        return productoRepository.findAll()
                .stream()
                .sorted(Comparator.comparingDouble(Producto::getPrecio))
                .toList();
    }

    public List<Producto> ordenarPorNombre() {
        return productoRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Producto::getNombre))
                .toList();
    }

    public Producto modificarPrecio(Long id, double nuevoPrecio) {

        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        producto.setPrecio(nuevoPrecio);

        return productoRepository.save(producto);
    }

    public void eliminarProducto(Long id) {

        if (!productoRepository.existsById(id)) {
            throw new RuntimeException("Producto no encontrado");
        }

        productoRepository.deleteById(id);
    }
}