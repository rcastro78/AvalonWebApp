package com.avalongroup.Inventory.repository;

import com.avalongroup.Inventory.model.Distributor;
import com.avalongroup.Inventory.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Distributor> {

    // Buscar productos de una marca con paginación
    Page<Product> findByIdBrand(Integer idBrand, Pageable pageable);

    // Buscar todos los productos de una marca
    List<Product> findByIdBrand(Integer idBrand);

    // Buscar productos de una marca con status = true y paginación
    Page<Product> findByIdBrandAndStatus(Integer idBrand, Boolean status, Pageable pageable);

    // Buscar todos los productos de una marca con status = true
    List<Product> findByIdBrandAndStatus(Integer idBrand, Boolean status);

    // Buscar un producto por idBrand y idItem
    Optional<Product> findByIdBrandAndIdItem(Integer idBrand, String idItem);

    // Buscar productos por su UPC
    Optional<Product> findByUpc(String upc);

    // Buscar productos activos (status = true)
    List<Product> findByStatus(Boolean status);

    // Buscar productos que sean veganos
    List<Product> findByVegan(Integer vegan);

    // Buscar productos basados en plantas
    List<Product> findByPlantBased(Integer plantBased);

    // Buscar productos con paginación por su nombre
    Page<Product> findByItemNameContainingIgnoreCase(String itemName, Pageable pageable);

    // Buscar productos por idUnit
    List<Product> findByIdUnit(Integer idUnit);

    // Buscar productos que tengan un nombre similar a un fragmento dado
    List<Product> findByItemNameLike(String itemName);
}
