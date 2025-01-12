package com.avalongroup.Inventory.controller;

import com.avalongroup.Inventory.model.Distributor;
import com.avalongroup.Inventory.model.Product;
import com.avalongroup.Inventory.model.ProductDistributorBrand;
import com.avalongroup.Inventory.service.DistributorService;
import com.avalongroup.Inventory.service.ProductDistributorBrandService;
import com.avalongroup.Inventory.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/product-distributor-brands")
public class ProductDistributorBrandController {

    @Autowired
    private ProductDistributorBrandService service;
    @Autowired
    private DistributorService distributorService;
    @Autowired
    private ProductService productService;

    // Obtener todos los registros
    @GetMapping
    public List<ProductDistributorBrand> getAllProducts() {
        return service.getAll();
    }

    // Buscar por idBrand
    @GetMapping("/brand/{idBrand}")
    public List<ProductDistributorBrand> getByIdBrand(@PathVariable Integer idBrand) {
        return service.getByIdBrand(idBrand);
    }

    // Buscar por idDistributor
    @GetMapping("/distributor/{idDistributor}")
    public List<ProductDistributorBrand> getByIdDistributor(@PathVariable Integer idDistributor) {
        return service.getByIdDistributor(idDistributor);
    }

    // Mostrar todos los productos de un distribuidor
    @GetMapping("/distributor/{idDistributor}/products")
    public String getAllProductsByDistributor(@PathVariable Integer idDistributor, Model model) {
        //return service.getAllProductsByDistributor(idDistributor);
        List<ProductDistributorBrand> productDistributorBrands = service.getAllProductsByDistributor(idDistributor);
        Optional<Distributor> distributor = distributorService.getDistributorById(idDistributor);
        if (distributor.isPresent()) {
            model.addAttribute("distributor", distributor.get()); // Pasa el objeto Product directamente
        } else {
            // Manejar el caso donde el producto no existe
            model.addAttribute("error", "Producto no encontrado");
        }
        model.addAttribute("distributors", productDistributorBrands);
        return "distributorProducts";
    }

    // Mostrar un producto con todos sus distribuidores
    @GetMapping("/product/{mfg}/distributors")
    public String getProductWithAllDistributors(@PathVariable String mfg, Model model) {
        // Obtener la lista de distribuidores asociados al producto
        List<ProductDistributorBrand> distributor = service.getProductWithAllDistributors(mfg);
        List<Distributor> distributors = distributorService.getAllDistributors();

        Optional<Product> productOptional = productService.getProductByIdItem(mfg);
        if (productOptional.isPresent()) {
            model.addAttribute("product", productOptional.get()); // Pasa el objeto Product directamente
        } else {
            // Manejar el caso donde el producto no existe
            model.addAttribute("error", "Producto no encontrado");
        }
        model.addAttribute("mfg", mfg);
        model.addAttribute("distributor", distributor);
        model.addAttribute("distributors", distributors);
        return "productDistributors";
    }
}