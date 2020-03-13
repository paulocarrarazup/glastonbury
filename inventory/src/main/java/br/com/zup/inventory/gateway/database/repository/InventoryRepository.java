package br.com.zup.inventory.gateway.database.repository;

import br.com.zup.inventory.gateway.database.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, String> {

        Optional<Inventory> findByItemId(String itemId);
}
