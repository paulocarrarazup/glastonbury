package br.com.zup.inventory.event.model;

import java.math.BigDecimal;
import java.util.List;

public class OrderRepresentation {

    private String orderId;
    private String customerId;
    private BigDecimal amount;
    private List<String> itemIds;
}
