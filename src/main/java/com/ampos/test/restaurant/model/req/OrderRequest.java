package com.ampos.test.restaurant.model.req;

import com.ampos.test.restaurant.model.common.OrderMenu;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class OrderRequest {
    private Long billNo;
    private List<OrderMenu> orderMenus;
    private Date orderedTime;


//    public static void main(String[] args) {
//        Gson gson = new GsonBuilder().serializeNulls().create();
//        OrderRequest orderRequest  = new OrderRequest();
//        List<OrderMenu> list = new ArrayList<>();
//        list.add(new OrderMenu());
//        orderRequest.setOrderMenus( list);
//        System.out.println(gson.toJson(orderRequest));
//    }
}

