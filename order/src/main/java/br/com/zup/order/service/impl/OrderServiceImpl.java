package br.com.zup.order.service.impl;

import br.com.zup.order.controller.response.OrderResponse;
import br.com.zup.order.domain.CreateOrderDomain;
import br.com.zup.order.domain.CreateOrderItemDomain;
import br.com.zup.order.event.order.publisher.OrderCreatedEventPublisher;
import br.com.zup.order.repository.OrderRepository;
import br.com.zup.order.service.OrderService;
import br.com.zup.order.service.translator.CreateOrderDomainToOrderEntityTranslator;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private OrderCreatedEventPublisher orderCreatedEventPublisher;

    public OrderServiceImpl(OrderRepository orderRepository, OrderCreatedEventPublisher orderCreatedEventPublisher) {
        this.orderRepository = orderRepository;
        this.orderCreatedEventPublisher = orderCreatedEventPublisher;
    }

    @Override
    public String create(CreateOrderDomain createOrderDomain) {
        String orderId = save(createOrderDomain);

        orderCreatedEventPublisher.publish(createOrderDomain);

        return orderId;
    }

    @Override
    public String save(CreateOrderDomain createOrderDomain) {
        return this.orderRepository.save(CreateOrderDomainToOrderEntityTranslator.translate(createOrderDomain)).getId();
    }

    private Map<String, Integer> createItemMap(CreateOrderDomain createOrderDomain) {
        Map<String, Integer> result = new HashMap<>();
        for (CreateOrderItemDomain item : createOrderDomain.getItems()) {
            result.put(item.getId(), item.getQuantity());
        }

        return result;
    }

    @Override
    public List<OrderResponse> findAll() {
        return this.orderRepository.findAll()
                .stream()
                .map(OrderResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
