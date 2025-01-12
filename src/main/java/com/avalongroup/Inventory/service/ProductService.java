package com.avalongroup.Inventory.service;

import com.avalongroup.Inventory.model.Product;
import com.avalongroup.Inventory.repository.BrandRepository;
import com.avalongroup.Inventory.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;

    public ProductService(ProductRepository productRepository, BrandRepository brandRepository) {
        this.productRepository = productRepository;
        this.brandRepository = brandRepository;
    }

    // Obtener productos por idBrand con paginación
    public Page<Product> getProductsByBrand(Integer idBrand, PageRequest pageRequest) {
        return productRepository.findByIdBrandAndStatus(idBrand, true, pageRequest);
    }

    // Obtener todos los productos de una marca
    public List<Product> getAllProductsByBrand(Integer idBrand) {
        return productRepository.findByIdBrandAndStatus(idBrand,true);
    }

    public Page<Product> getAllProducts( PageRequest pageRequest) {
        return productRepository.findAll(pageRequest);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Obtener un producto por idBrand y idItem
    public Optional<Product> getProductById(Integer idBrand, String idItem) {
        return productRepository.findByIdBrandAndIdItem(idBrand, idItem);
    }

    public Optional<Product> getProductByIdItem(String idItem) {
        return productRepository.findByIdItem(idItem);
    }


    // Crear un nuevo producto
    public Product createProduct(Product product) {
        // Verifica que la marca exista antes de crear el producto
        if (brandRepository.existsById(product.getIdBrand())) {
            return productRepository.save(product);
        } else {
            throw new IllegalArgumentException("La marca con id " + product.getIdBrand() + " no existe");
        }
    }

    // Actualizar un producto
    public Product updateProduct(Integer idBrand, String idItem, String itemName, String upc) {
        Product product = productRepository.findByIdBrandAndIdItem(idBrand, idItem)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));

        product.setItemName(itemName);
        product.setUpc(upc);

        return productRepository.save(product);
    }

    // Eliminar un producto (borrado lógico)
    public void deleteProduct(Integer idBrand, String idItem) {
        Product product = productRepository.findByIdBrandAndIdItem(idBrand, idItem)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
        product.setStatus(false);
        productRepository.save(product);
    }
}
