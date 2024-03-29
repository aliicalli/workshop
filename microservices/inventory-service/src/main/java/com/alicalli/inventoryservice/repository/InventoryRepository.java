package com.alicalli.inventoryservice.repository;

import com.alicalli.inventoryservice.model.Inventory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
  List<Inventory> findBySkuCodeIn(List<String> skuCode);
}
