package com.avalongroup.Inventory.controller;

import com.avalongroup.Inventory.model.Brand;
import com.avalongroup.Inventory.model.Product;
import com.avalongroup.Inventory.service.BrandService;
import com.avalongroup.Inventory.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    private final BrandService brandService;
    public ProductController(ProductService productService, BrandService brandService) {
        this.productService = productService;
        this.brandService = brandService;
    }

    // Obtener productos por idBrand con paginación
    @GetMapping("/{idBrand}/products")
    public String getProductsByBrand(Model model, @PathVariable Integer idBrand,
                                     @RequestParam(defaultValue = "0") int page) throws Exception {
        // Obtener los productos de una marca con paginación
        Page<Product> productPage = productService.getProductsByBrand(idBrand, PageRequest.of(page, 10));

        Optional<Brand> brandOptional = brandService.getBrandById(idBrand);
        // Desempaquetar el Optional
        Brand brand = brandOptional.orElseThrow(() -> new Exception("Marca no encontrada"));


        model.addAttribute("brand", brand);
        model.addAttribute("productPage", productPage);  // Objeto con paginación
        model.addAttribute("currentPage", page);          // Página actual
        model.addAttribute("totalPages", productPage.getTotalPages()); // Total de páginas

        return "products";  // Nombre de la vista Thymeleaf
    }

    // Obtener todos los productos por idBrand
    @GetMapping("/{idBrand}/products/all")
    public List<Product> getAllProductsByBrand(@PathVariable Integer idBrand) {
        return productService.getAllProductsByBrand(idBrand);
    }

    // Obtener un producto específico por su id
    @GetMapping("/{idBrand}/products/{idItem}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer idBrand,
                                                  @PathVariable String idItem) {
        return productService.getProductById(idBrand, idItem)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear un nuevo producto para una marca
    @PostMapping("/{idBrand}/products/create")
    public String createProduct(@PathVariable Integer idBrand, @ModelAttribute Product product,
                                @RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "0") Integer vegan,
                                @RequestParam(defaultValue = "0") Integer plantBased) {
        product.setIdBrand(idBrand); // Asocia la marca al producto
        product.setVegan(vegan); //Valor por defecto
        product.setPlantBased(plantBased); //Valor por defecto
        productService.createProduct(product);
        return "redirect:/api/brands/" + idBrand + "/products?page=" + page;
    }

    // Actualizar un producto
    @PostMapping("/{idBrand}/products/update")
    public String updateProduct(@PathVariable Integer idBrand, @RequestParam("idItem") String idItem,
                                @RequestParam("itemName") String itemName,
                                @RequestParam("upc") String upc,
                                @RequestParam(defaultValue = "0") int page) {
        Product updatedProduct = productService.updateProduct(idBrand, idItem, itemName, upc);
        return "redirect:/api/brands/" + idBrand + "/products?page=" + page;
    }

    // Eliminar un producto
    @DeleteMapping("/{idBrand}/products/{idItem}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer idBrand, @PathVariable String idItem) {
        productService.deleteProduct(idBrand, idItem);
        return ResponseEntity.noContent().build();
    }
}

