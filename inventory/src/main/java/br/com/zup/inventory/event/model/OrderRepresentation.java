package br.com.zup.inventory.event.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class OrderRepresentation {

    private String orderId;
    private String customerId;
    private BigDecimal amount;
    private Map<String, Integer> items;
}
