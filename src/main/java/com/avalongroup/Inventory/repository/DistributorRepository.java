package com.avalongroup.Inventory.repository;


import com.avalongroup.Inventory.model.Distributor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DistributorRepository extends JpaRepository<Distributor, Integer>, JpaSpecificationExecutor<Distributor> {


    // Buscar distribuidores por su estado (activos o inactivos)
    List<Distributor> findByStatus(Boolean status);

    // Buscar distribuidores por nombre con paginación
    Page<Distributor> findByDistributorNameContainingIgnoreCase(String distributorName, Pageable pageable);

    // Buscar un distribuidor por correo electrónico
    Optional<Distributor> findByDistributorEmail(String distributorEmail);

    // Buscar distribuidores por número de teléfono
    List<Distributor> findByDistributorPhone(String distributorPhone);

    // Buscar distribuidores por contacto
    List<Distributor> findByContact(String contact);
}
