package br.com.zup.order.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateOrderItemDomain {

    private String id;
    private String name;
    private BigDecimal amount;
    private Integer quantity;
}
