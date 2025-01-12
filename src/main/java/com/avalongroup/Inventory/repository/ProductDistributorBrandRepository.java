package com.avalongroup.Inventory.repository;

import com.avalongroup.Inventory.model.ProductDistributorBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDistributorBrandRepository extends JpaRepository<ProductDistributorBrand, String> {

    // Buscar por idBrand
    List<ProductDistributorBrand> findByIdBrand(Integer idBrand);

    // Buscar por idDistributor
    List<ProductDistributorBrand> findByIdDistributor(Integer idDistributor);

    // Mostrar todos los productos de un distribuidor
    List<ProductDistributorBrand> findAllByIdDistributor(Integer idDistributor);

    // Mostrar el producto con todos sus distribuidores
    List<ProductDistributorBrand> findAllBymfg(String mfg);
}
