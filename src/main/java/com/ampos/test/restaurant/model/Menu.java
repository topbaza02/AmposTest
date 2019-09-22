package com.ampos.test.restaurant.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private String image;
    @Column
    private BigDecimal price;
    @Column
    private String additionDetails;

    public Menu(String name, String description, BigDecimal price, String additionDetails) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.additionDetails = additionDetails;
    }
}
