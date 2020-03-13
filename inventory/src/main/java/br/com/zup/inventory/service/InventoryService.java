package br.com.zup.inventory.service;

import br.com.zup.inventory.event.model.OrderRepresentation;

public interface InventoryService {

    void checkAvailableTickets(OrderRepresentation orderRepresentation);
}
