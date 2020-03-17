package br.com.zup.order.service;

import br.com.zup.order.controller.request.CreateOrderRequest;
import br.com.zup.order.controller.response.OrderResponse;
import br.com.zup.order.domain.CreateOrderDomain;

import java.util.List;

public interface OrderService {

    String create(CreateOrderDomain createOrderDomain);

    List<OrderResponse> findAll();

    String save(CreateOrderDomain createOrderDomain);
}
