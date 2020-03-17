package br.com.zup.order.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateOrderItemRequest {

    private String id;
    private String name;
    private BigDecimal amount;
    private Integer quantity;
}
