package com.avalongroup.Inventory.rest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class LoginRestController {

    // Mostrar el formulario de inicio de sesión
    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // Nombre de la plantilla HTML (login.html)
    }

    // Manejar el envío del formulario
    @PostMapping("/login")
    public String handleLogin(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            Model model) {

        // Validar credenciales (ejemplo básico)
        if ("admin".equals(username) && "password".equals(password)) {
            model.addAttribute("message", "Inicio de sesión exitoso!");
            return "menu"; // Redirige a la página de bienvenida
        } else {
            model.addAttribute("error", "Usuario o contraseña incorrectos");
            return "login"; // Regresa al formulario con mensaje de error
        }
    }
}