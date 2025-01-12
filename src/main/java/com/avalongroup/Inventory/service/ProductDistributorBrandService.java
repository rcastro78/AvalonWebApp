package com.avalongroup.Inventory.service;

import com.avalongroup.Inventory.model.ProductDistributorBrand;
import com.avalongroup.Inventory.repository.ProductDistributorBrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDistributorBrandService {

    @Autowired
    private ProductDistributorBrandRepository repository;

    // Obtener todos los registros
    public List<ProductDistributorBrand> getAll() {
        return repository.findAll();
    }

    // Buscar por idBrand
    public List<ProductDistributorBrand> getByIdBrand(Integer idBrand) {
        return repository.findByIdBrand(idBrand);
    }

    // Buscar por idDistributor
    public List<ProductDistributorBrand> getByIdDistributor(Integer idDistributor) {
        return repository.findByIdDistributor(idDistributor);
    }

    // Mostrar todos los productos de un distribuidor
    public List<ProductDistributorBrand> getAllProductsByDistributor(Integer idDistributor) {
        return repository.findAllByIdDistributor(idDistributor);
    }

    // Mostrar un producto con todos sus distribuidores
    public List<ProductDistributorBrand> getProductWithAllDistributors(String mfg) {
        return repository.findAllBymfg(mfg);
    }
}