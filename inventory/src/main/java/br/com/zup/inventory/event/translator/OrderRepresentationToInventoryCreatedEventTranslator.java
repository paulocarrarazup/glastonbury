package br.com.zup.inventory.event.translator;

import br.com.zup.inventory.event.inventory.InventoryCreatedEvent;
import br.com.zup.inventory.event.inventory.InventoryRejectedEvent;
import br.com.zup.inventory.event.order.model.OrderRepresentation;
import lombok.experimental.UtilityClass;
import org.modelmapper.ModelMapper;

@UtilityClass
public class OrderRepresentationToInventoryCreatedEventTranslator {

    private static final ModelMapper MAPPER = new ModelMapper();

    public InventoryCreatedEvent translate(final OrderRepresentation orderRepresentation) {
        return MAPPER.map(orderRepresentation, InventoryCreatedEvent.class);
    }
}
