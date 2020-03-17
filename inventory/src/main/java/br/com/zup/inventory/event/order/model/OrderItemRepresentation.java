package br.com.zup.inventory.event.order.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemRepresentation {

    private String id;
    private String name;
    private BigDecimal amount;
    private Integer quantity;
}
