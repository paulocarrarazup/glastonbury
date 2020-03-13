package br.com.zup.inventory.gateway.database.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity(name = "inventory")
public class Inventory {

    @Id
    private Integer id;

    private String itemId;

    private Integer quantity;
}
