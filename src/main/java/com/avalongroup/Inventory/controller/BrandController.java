package com.avalongroup.Inventory.controller;

import com.avalongroup.Inventory.model.Brand;
import com.avalongroup.Inventory.service.BrandService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/brands")
public class BrandController {

    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }


    @GetMapping
    public String getBrands(Model model, @RequestParam(defaultValue = "0") int page) {
        // Obtén las marcas con paginación
        Page<Brand> brandPage = brandService.getBrands(PageRequest.of(page, 10));

        model.addAttribute("brandPage", brandPage); // El objeto con paginación
        model.addAttribute("currentPage", page);     // Página actual
        model.addAttribute("totalPages", brandPage.getTotalPages()); // Total de páginas

        return "brands"; // Nombre de la vista Thymeleaf
    }

    @GetMapping("/")
    public List<Brand> getAllBrands() {
        return brandService.getAllBrands();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Brand> getBrandById(@PathVariable Integer id) {
        return brandService.getBrandById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public String createBrand(@ModelAttribute Brand brand,
                              @RequestParam(defaultValue = "0") int page) {
        brandService.createBrand(brand);
        return "redirect:/api/brands?page=" + page;
    }

    @PostMapping("/update")
    public String updateBrand(@RequestParam("idBrand") Integer id,
                              @RequestParam("brandName") String brandName,
                              @RequestParam("brandLogoUrl") String brandLogoUrl,
                              @RequestParam(defaultValue = "0") int page) {

        // Llamada al servicio para actualizar la marca
        Brand updatedBrand = brandService.updateBrand(id, new Brand(id, brandName, brandLogoUrl));

        // Redirigir a la misma página para cargar las marcas actualizadas
        return "redirect:/api/brands?page=" + page;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable Integer id) {
        brandService.deleteBrand(id);
        return ResponseEntity.noContent().build();
    }
}

