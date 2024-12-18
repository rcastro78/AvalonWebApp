package com.avalongroup.Inventory.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MantenimientoController {

    // Ruta para mostrar la página de mantenimiento
    @GetMapping("/menu")
    public String mostrarMantenimiento() {
        return "menu"; // El nombre del archivo HTML con Thymeleaf
    }

    // Ruta para gestionar proveedores
    @GetMapping("/mantenimiento/providers")
    public String gestionarProveedores() {
        return "proveedores"; // Redirige a una página específica para proveedores
    }

    // Ruta para gestionar marcas
    @GetMapping("/mantenimiento/brands")
    public String gestionarMarcas() {
        return "marcas"; // Redirige a una página específica para marcas
    }

    // Ruta para gestionar productos
    @GetMapping("/mantenimiento/products")
    public String gestionarProductos() {
        return "productos"; // Redirige a una página específica para productos
    }

    // Ruta para gestionar stock
    @GetMapping("/mantenimiento/stock")
    public String gestionarStock() {
        return "stock"; // Redirige a una página específica para gestionar stock
    }

    // Ruta para generar reportes PDF
    @GetMapping("/mantenimiento/reports")
    public String generarReportes() {
        return "reportes"; // Redirige a una página específica para generar reportes
    }
}
