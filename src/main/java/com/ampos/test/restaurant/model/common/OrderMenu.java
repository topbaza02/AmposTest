package com.ampos.test.restaurant.model.common;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class OrderMenu {
    private Long menuId;
    private Integer quantity;
    private BigDecimal price;
    private String menuName;
}
