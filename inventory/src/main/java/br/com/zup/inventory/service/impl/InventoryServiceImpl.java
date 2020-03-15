package br.com.zup.inventory.service.impl;

import br.com.zup.inventory.enumeration.OrderStatus;
import br.com.zup.inventory.event.order.PublishOrderEvent;
import br.com.zup.inventory.event.order.model.OrderRepresentation;
import br.com.zup.inventory.event.payment.PublishPaymentEvent;
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
    private PublishPaymentEvent publishPaymentEvent;
    private PublishOrderEvent publishOrderEvent;

    public InventoryServiceImpl(InventoryRepository inventoryRepository, PublishPaymentEvent publishPaymentEvent, PublishOrderEvent publishOrderEvent) {
        this.inventoryRepository = inventoryRepository;
        this.publishPaymentEvent = publishPaymentEvent;
        this.publishOrderEvent = publishOrderEvent;
    }

    @Override
    @Transactional
    public void checkAvailableTickets(final OrderRepresentation orderRepresentation) {

        try {

            orderRepresentation.getItems().forEach((itemKey, itemValue) -> {
                final Optional<Inventory> item = this.inventoryRepository.findByItemId(itemKey);

                if(!item.isPresent()) {
                    throw new OrderRejectedException("Order Sold Out");
                }

                final Inventory itemInventory = item.get();

                if(itemValue <= itemInventory.getQuantity()) {
                    itemInventory.setQuantity(itemInventory.getQuantity() - itemValue);
                    this.inventoryRepository.save(itemInventory);
                } else {
                    throw new OrderRejectedException("Order Sold Out");
                }

            });

        } catch (final Exception ex) {
            orderRepresentation.setStatus(OrderStatus.ORDER_REJECTED);
            publishOrderEvent.publish(orderRepresentation);
            throw ex;
        }

        orderRepresentation.setStatus(OrderStatus.IN_PAYMENT);
        publishPaymentEvent.publish(orderRepresentation);
    }
}
