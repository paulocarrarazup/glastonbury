package br.com.zup.order.event.translator;

import br.com.zup.order.domain.CreateOrderDomain;
import br.com.zup.order.event.inventory.InventoryRejectedEvent;
import lombok.experimental.UtilityClass;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

@UtilityClass
public class InventoryRejectedEventToCreateOrderDomain {

    private static final ModelMapper MAPPER = new ModelMapper();

    public CreateOrderDomain translate(InventoryRejectedEvent inventoryRejectedEvent) {
        MAPPER.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        MAPPER.typeMap(InventoryRejectedEvent.class, CreateOrderDomain.class)
                .addMapping(src -> src.getOrderId(), CreateOrderDomain::setId);

        return MAPPER.map(inventoryRejectedEvent, CreateOrderDomain.class);
    }
}
