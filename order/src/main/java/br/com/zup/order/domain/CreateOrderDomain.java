package br.com.zup.order.domain;

import br.com.zup.order.enumeration.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateOrderDomain {

    private String id;
    private String customerId;
    private BigDecimal amount;
    private OrderStatus status;
    private List<CreateOrderItemDomain> items;
}
