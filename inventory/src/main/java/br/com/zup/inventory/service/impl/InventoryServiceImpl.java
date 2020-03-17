package br.com.zup.inventory.service.impl;

import br.com.zup.inventory.enumeration.OrderStatus;
import br.com.zup.inventory.event.inventory.publisher.InventoryCreatedEventPublisher;
import br.com.zup.inventory.event.inventory.publisher.InventoryRejectedEventPublisher;
import br.com.zup.inventory.event.order.model.OrderRepresentation;
import br.com.zup.inventory.exception.OrderRejectedException;
import br.com.zup.inventory.gateway.database.entity.Inventory;
import br.com.zup.inventory.gateway.database.repository.InventoryRepository;
import br.com.zup.inventory.service.InventoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class InventoryServiceImpl implements InventoryService {

    private InventoryRepository inventoryRepository;
    private InventoryCreatedEventPublisher inventoryCreatedEventPublisher;
    private InventoryRejectedEventPublisher inventoryRejectedEventPublisher;

    public InventoryServiceImpl(InventoryRepository inventoryRepository, InventoryCreatedEventPublisher inventoryCreatedEventPublisher, InventoryRejectedEventPublisher inventoryRejectedEventPublisher) {
        this.inventoryRepository = inventoryRepository;
        this.inventoryCreatedEventPublisher = inventoryCreatedEventPublisher;
        this.inventoryRejectedEventPublisher = inventoryRejectedEventPublisher;
    }

    @Override
    @Transactional
    public void checkAvailableTickets(final OrderRepresentation orderRepresentation) {

        try {

            orderRepresentation.getItems().forEach(itemRepresentation -> {
                final Optional<Inventory> item = this.inventoryRepository.findByItemId(itemRepresentation.getId());

                if(!item.isPresent()) {
                    throw new OrderRejectedException("Order Sold Out");
                }

                final Inventory itemInventory = item.get();

                if(itemRepresentation.getQuantity() <= itemInventory.getQuantity()) {
                    itemInventory.setQuantity(itemInventory.getQuantity() - itemRepresentation.getQuantity());
                    this.inventoryRepository.save(itemInventory);
                } else {
                    throw new OrderRejectedException("Order Sold Out");
                }

            });

        } catch (final Exception ex) {
            orderRepresentation.setStatus(OrderStatus.ORDER_REJECTED);
            inventoryRejectedEventPublisher.publish(orderRepresentation);
            throw ex;
        }

        orderRepresentation.setStatus(OrderStatus.IN_PAYMENT);
        inventoryCreatedEventPublisher.publish(orderRepresentation);
    }
}
