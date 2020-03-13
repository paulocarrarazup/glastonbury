package br.com.zup.inventory.service.impl;

import br.com.zup.inventory.event.model.OrderRepresentation;
import br.com.zup.inventory.service.InventoryService;
import org.springframework.stereotype.Service;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Override
    public void checkAvailableTickets(OrderRepresentation orderRepresentation) {
        //TODO: check if exist available ticket with ids and publish to payments topic
    }
}
