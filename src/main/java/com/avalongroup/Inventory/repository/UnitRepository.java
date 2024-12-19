package com.avalongroup.Inventory.repository;

import com.avalongroup.Inventory.model.Unit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Integer> {

    // Devuelve todas las unidades
    List<Unit> findAll();

    // Otros métodos previamente definidos
    List<Unit> findByUnitNameContainingIgnoreCase(String unitName);
    List<Unit> findByCreatedAtAfter(LocalDateTime createdAt);
    List<Unit> findByUpdatedAtAfter(LocalDateTime updatedAt);
    List<Unit> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<Unit> findByUpdatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
    Optional<Unit> findByUnitName(String unitName);
    Page<Unit> findAll(Pageable pageable);
}
