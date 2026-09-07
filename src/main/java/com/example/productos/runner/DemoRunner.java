package com.example.productos.runner;

import com.example.productos.model.Producto;
import com.example.productos.repository.ProductoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
public class DemoRunner implements CommandLineRunner {

    private final ProductoRepository productoRepository;

    public DemoRunner(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public void run(String... args) {

        System.out.println("=========================");
        System.out.println("     DEMO DE PRODUCTOS");
        System.out.println("=========================");

        Producto mouse = new Producto(
                "Mouse Logitech",
                25000,
                10,
                "Periféricos"
        );

        Producto teclado = new Producto(
                "Teclado Mecánico",
                45000,
                5,
                "Periféricos"
        );

        Producto monitor = new Producto(
                "Monitor Valkyrie",
                180000,
                3,
                "Monitores"
        );

        productoRepository.save(mouse);
        productoRepository.save(teclado);
        productoRepository.save(monitor);

        System.out.println("\n PRODUCTOS CREADOS ");

        productoRepository.findAll()
                .forEach(System.out::println);

        System.out.println("\n ORDENADOS POR PRECIO ");

        List<Producto> productos = productoRepository.findAll();

        productos.stream()
                .sorted(Comparator.comparingDouble(Producto::getPrecio))
                .forEach(System.out::println);

        System.out.println("\n MODIFICANDO PRECIO ");

        monitor.setPrecio(160000);
        productoRepository.save(monitor);

        System.out.println("Nuevo precio del monitor: "
                + monitor.getPrecio());

        System.out.println("\n PRODUCTOS DESPUÉS DE MODIFICAR ");

        productoRepository.findAll()
                .forEach(System.out::println);

        System.out.println("\n ELIMINANDO PRODUCTO ");

        productoRepository.deleteById(mouse.getId());

        System.out.println("Mouse eliminado correctamente.");

        System.out.println("\n--- LISTADO FINAL ---");

        productoRepository.findAll()
                .forEach(System.out::println);


        System.out.println("        FIN DE LA DEMO");

    }
}