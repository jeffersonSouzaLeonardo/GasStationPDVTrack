package com.br.manager.domain.sale.entity;

import com.br.manager.domain.price.entity.PriceTable;
import com.br.manager.domain.operational.entity.Product;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "comanda_item")
public class CommandItem {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "command_id", nullable = false)
    private Command command;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "unit_value", nullable = false)
    private BigDecimal unitValue;

    @Column(name = "discount", nullable = false)
    private BigDecimal discount;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "price_table_id", nullable = false)
    private PriceTable priceTable;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public Command getCommand() { return command; }
    public void setCommand(Command command) { this.command = command; }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }

    public BigDecimal getUnitValue() { return unitValue; }
    public void setUnitValue(BigDecimal unitValue) { this.unitValue = unitValue; }

    public BigDecimal getDiscount() { return discount; }
    public void setDiscount(BigDecimal discount) { this.discount = discount; }

    public PriceTable getPriceTable() { return priceTable; }
    public void setPriceTable(PriceTable priceTable) { this.priceTable = priceTable; }
}
