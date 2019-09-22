package com.ampos.test.restaurant.model.res;

import com.ampos.test.restaurant.model.common.OrderMenu;
import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

@Data
public class CheckBillRes {
    private Long billNo;
    private BigDecimal totalPrice;
    private List<OrderMenu> orderMenus;
}
