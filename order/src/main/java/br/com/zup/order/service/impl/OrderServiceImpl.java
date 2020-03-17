package br.com.zup.order.service.impl;

import br.com.zup.order.controller.response.OrderResponse;
import br.com.zup.order.domain.CreateOrderDomain;
import br.com.zup.order.domain.CreateOrderItemDomain;
import br.com.zup.order.enumeration.OrderStatus;
import br.com.zup.order.event.order.OrderCreatedEvent;
import br.com.zup.order.repository.OrderRepository;
import br.com.zup.order.service.OrderService;
import br.com.zup.order.service.translator.CreateOrderDomainToOrderEntityTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private KafkaTemplate<String, OrderCreatedEvent> template;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, KafkaTemplate<String, OrderCreatedEvent> template) {
        this.orderRepository = orderRepository;
        this.template = template;
    }

    @Override
    public String create(CreateOrderDomain createOrderDomain) {
        String orderId = save(createOrderDomain);

        OrderCreatedEvent event = OrderCreatedEvent.builder()
                .orderId(orderId)
                .customerId(createOrderDomain.getCustomerId())
                .amount(createOrderDomain.getAmount())
                .status(OrderStatus.ORDER_CREATED)
                .items(createItemMap(createOrderDomain))
                .build();

        this.template.send("created-orders", event);

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
