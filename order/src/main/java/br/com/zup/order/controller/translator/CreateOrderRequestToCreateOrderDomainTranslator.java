package br.com.zup.order.controller.translator;

import br.com.zup.order.controller.request.CreateOrderRequest;
import br.com.zup.order.domain.CreateOrderDomain;
import lombok.experimental.UtilityClass;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

@UtilityClass
public class CreateOrderRequestToCreateOrderDomainTranslator {

    private static final ModelMapper MAPPER = new ModelMapper();

    public CreateOrderDomain translate(CreateOrderRequest createOrderRequest) {
        MAPPER.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        return MAPPER.map(createOrderRequest, CreateOrderDomain.class);
    }
}
