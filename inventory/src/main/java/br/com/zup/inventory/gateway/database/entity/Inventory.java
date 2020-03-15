package br.com.zup.inventory.gateway.database.entity;

import br.com.zup.inventory.enumeration.InventoryStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity(name = "inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "item_id")
    private String itemId;

    private Integer quantity;
}
