package com.avalongroup.Inventory.service;
import com.avalongroup.Inventory.model.Unit;
import com.avalongroup.Inventory.repository.UnitRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnitService {

    private final UnitRepository unitRepository;

    public UnitService(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    // Obtener todas las unidades con paginación
    public Page<Unit> getUnits(Pageable pageable) {
        return unitRepository.findAll(pageable);
    }

    // Obtener todas las unidades
    public List<Unit> getAllUnits() {
        return unitRepository.findAll();
    }

    // Obtener una unidad por ID
    public Optional<Unit> getUnitById(Integer id) {
        return unitRepository.findById(id);
    }

    // Crear una nueva unidad
    public Unit createUnit(Unit unit) {
        return unitRepository.save(unit);
    }

    // Actualizar una unidad existente
    public Unit updateUnit(Integer id, Unit unitDetails) {
        Unit unit = unitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Unit not found"));

        unit.setUnitName(unitDetails.getUnitName());

        return unitRepository.save(unit);
    }

    // Eliminar una unidad por ID
    public void deleteUnit(Integer id) {
        Unit unit = unitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Unit not found"));
        unitRepository.delete(unit);
    }
}

