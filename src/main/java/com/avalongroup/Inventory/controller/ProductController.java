package com.avalongroup.Inventory.controller;

import com.avalongroup.Inventory.model.Brand;
import com.avalongroup.Inventory.model.Product;
import com.avalongroup.Inventory.service.BrandService;
import com.avalongroup.Inventory.service.ProductService;
import com.avalongroup.Inventory.service.UnitService;
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

    //Si necesito datos de varias tablas o vistas las llamo via los servicios,
    //luego se pasa al constructor y a la funcion de la pagina via model.addAttribute(nombre, servicio.funcion)

    private final ProductService productService;
    private final BrandService brandService;
    private final UnitService unitService;
    public ProductController(ProductService productService, BrandService brandService,
                             UnitService unitService) {
        this.productService = productService;
        this.brandService = brandService;
        this.unitService = unitService;
    }

    // Obtener productos por idBrand con paginación
    @GetMapping("/{idBrand}/products")
    public String getProductsByBrand(Model model, @PathVariable Integer idBrand,
                                     @RequestParam(defaultValue = "0") int page) throws Exception {
        // Obtener los productos de una marca con paginación
        Page<Product> productPage = productService.getProductsByBrand(idBrand, PageRequest.of(page, 5));

        Optional<Brand> brandOptional = brandService.getBrandById(idBrand);
        // Desempaquetar el Optional
        Brand brand = brandOptional.orElseThrow(() -> new Exception("Marca no encontrada"));


        model.addAttribute("brand", brand);
        model.addAttribute("productPage", productPage);  // Objeto con paginación
        model.addAttribute("currentPage", page);          // Página actual
        model.addAttribute("totalPages", productPage.getTotalPages()); // Total de páginas

        return "products";  // Nombre de la vista Thymeleaf
    }


    @GetMapping("/all")
    public String getProducts(Model model,
                              @RequestParam(defaultValue = "0") int page) throws Exception {
        // Obtener los productos de una marca con paginación
        Page<Product> productPage = productService.getAllProducts(PageRequest.of(page, 5));

        model.addAttribute("productPage", productPage);  // Objeto con paginación
        model.addAttribute("currentPage", page);          // Página actual
        model.addAttribute("totalPages", productPage.getTotalPages()); // Total de páginas

        return "productsAll";  // Nombre de la vista Thymeleaf
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
                                @RequestParam(defaultValue = "0") Integer plantBased,
                                Model model) {

        model.addAttribute("units",unitService.getAllUnits());
        product.setIdBrand(idBrand); // Asocia la marca al producto
        product.setVegan(vegan); //Valor por defecto
        product.setPlantBased(plantBased); //Valor por defecto
        productService.createProduct(product);
        return "redirect:/api/products/" + idBrand + "/products?page=" + page;
    }

    // Actualizar un producto
    @PostMapping("/{idBrand}/products/update")
    public String updateProduct(@PathVariable Integer idBrand, @RequestParam("idItem") String idItem,
                                @RequestParam("itemName") String itemName,
                                @RequestParam("upc") String upc,
                                @RequestParam(defaultValue = "0") int page) {

        productService.updateProduct(idBrand, idItem, itemName, upc);
        return "redirect:/api/products/" + idBrand + "/products?page=" + page;
    }

    // Eliminar un producto
    @DeleteMapping("/{idBrand}/products/{idItem}")
    public String deleteProduct(@PathVariable Integer idBrand, @PathVariable String idItem) {
        productService.deleteProduct(idBrand, idItem);
        //return ResponseEntity.noContent().build();
        return "redirect:/api/brands/" + idBrand + "/products?page=0";
    }
}

