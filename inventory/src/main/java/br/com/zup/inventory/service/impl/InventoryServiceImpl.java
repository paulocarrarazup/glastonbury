package br.com.zup.inventory.service.impl;

import br.com.zup.inventory.event.model.OrderRepresentation;
import br.com.zup.inventory.gateway.database.entity.Inventory;
import br.com.zup.inventory.gateway.database.repository.InventoryRepository;
import br.com.zup.inventory.service.InventoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryServiceImpl implements InventoryService {

    private InventoryRepository inventoryRepository;

    public InventoryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public void checkAvailableTickets(final OrderRepresentation orderRepresentation) {

        orderRepresentation.getItems().forEach((itemKey, itemValue) -> {
            final Optional<Inventory> item = this.inventoryRepository.findByItemId(itemKey);

            if(item.isPresent()) {
                final Inventory itemInventory = item.get();

                if(itemValue <= itemInventory.getQuantity()) {
                    //TODO: publish event do payments and remove from itemInventory selected quantity
                    itemInventory.setQuantity(itemInventory.getQuantity() - itemValue);
                    this.inventoryRepository.save(itemInventory);
                } else {
                    //TODO: publish event to order
                }
            }
        });
    }

    private void decreaseFromInventory() {
    }
}
