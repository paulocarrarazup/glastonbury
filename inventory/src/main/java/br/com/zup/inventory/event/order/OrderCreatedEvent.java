package br.com.zup.inventory.event.order;

import br.com.zup.inventory.enumeration.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderCreatedEvent {

    private String orderId;
    private String customerId;
    private BigDecimal amount;
    private Map<String, Integer> items;
    private OrderStatus status;
}
