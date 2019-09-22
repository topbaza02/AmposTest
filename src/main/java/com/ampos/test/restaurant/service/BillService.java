package com.ampos.test.restaurant.service;

import com.ampos.test.restaurant.model.Bill;
import com.ampos.test.restaurant.model.Menu;
import com.ampos.test.restaurant.model.common.OrderMenu;
import com.ampos.test.restaurant.model.req.OrderRequest;
import com.ampos.test.restaurant.model.res.CheckBillRes;
import com.ampos.test.restaurant.repo.BillRepository;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.querydsl.core.types.dsl.BooleanExpression;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class BillService {
    @Autowired
    private BillRepository repository;

    @Autowired
    private MenuService menuService;

    public Bill save(Bill bill) {
        return repository.save(bill);
    }

    public List<Bill> save(List<Bill> bills) {
        return (List<Bill>) repository.saveAll(bills);
    }

    public Iterable<Bill> findAll(BooleanExpression exp) {
        return repository.findAll(exp);
    }

    public List<Bill> save(OrderRequest request) {
        if (!CollectionUtils.isEmpty(request.getOrderMenus())) {
            List<Bill> saveList = request.getOrderMenus().stream().map(orderMenu -> {
                Bill bill = new Bill();
                bill.setBillNo(generateBillNo());
                bill.setOrderedTime(Objects.nonNull(request.getOrderedTime()) ? request.getOrderedTime() : new Date());
                bill.setMenuId(orderMenu.getMenuId());
                bill.setQuantity(orderMenu.getQuantity());
                return bill;
            }).collect(Collectors.toList());
            return save(saveList);
        } else {
            return null;
        }
    }

    private Long generateBillNo() {
        Long billNo = repository.findMaxBillNo();
        return Objects.nonNull(billNo) ? billNo + 1 : 1L;
    }

    public List<Bill> update(OrderRequest request) {
        if (Objects.nonNull(request.getBillNo())) {
            repository.deleteByBillNo(request.getBillNo());
            List<Bill> saveList = request.getOrderMenus().stream().map(orderMenu -> {
                Bill bill = new Bill();
                bill.setBillNo(request.getBillNo());
                bill.setOrderedTime(Objects.nonNull(request.getOrderedTime()) ? request.getOrderedTime() : new Date());
                bill.setMenuId(orderMenu.getMenuId());
                bill.setQuantity(orderMenu.getQuantity());
                return bill;
            }).collect(Collectors.toList());
            return save(saveList);
        } else {
            return null;
        }
    }

    public Long delete(Long id) {
        repository.deleteByBillNo(id);
        return id;
    }

    public List<CheckBillRes> checkBill() {
        Iterable<Bill> iterable = repository.findAll();
        if (!Iterables.isEmpty(iterable)) {
            ArrayList<Bill> billList = Lists.newArrayList(iterable);
            Set<Long> idSet = billList.stream().map(Bill::getMenuId).collect(Collectors.toSet());
            List<Menu> menuList = menuService.findByIdIn(idSet);

            List<CheckBillRes> checkBillResList = new ArrayList<>();
            for (Bill bill : billList) {
                List<OrderMenu> orderMenus = new ArrayList<>();
                OrderMenu orderMenu = new OrderMenu();
                orderMenu.setMenuId(bill.getMenuId());
                orderMenu.setQuantity(bill.getQuantity());
                Menu menu = getMenu(bill.getMenuId(), menuList);
                orderMenu.setMenuName(menu.getName());
                orderMenu.setPrice(menu.getPrice());
                orderMenus.add(orderMenu);

                CheckBillRes checkBillRes = getCheckBillByBillNo(checkBillResList, bill.getBillNo());
                if (Objects.nonNull(checkBillRes)) {
                    checkBillRes.getOrderMenus().addAll(orderMenus);
                } else {
                    checkBillRes = new CheckBillRes();
                    checkBillRes.setBillNo(bill.getBillNo());
                    checkBillRes.setOrderMenus(orderMenus);
                    checkBillResList.add(checkBillRes);
                }
            }

            return calSubTotal(checkBillResList);
        } else {
            return null;
        }
    }

    private List<CheckBillRes> calSubTotal(List<CheckBillRes> checkBillResList) {
        checkBillResList.forEach(bill ->
        {
            BigDecimal total = bill.getOrderMenus().stream().map(menu -> menu.getPrice().multiply(BigDecimal.valueOf(menu.getQuantity())))
                    .reduce(BigDecimal.valueOf(0), BigDecimal::add);
            bill.setTotalPrice(total);
        });

        return checkBillResList;
    }

    private Menu getMenu(Long menuId, List<Menu> menuList) {
        return menuList.stream().filter(menu -> menu.getId().equals(menuId)).findFirst().orElse(null);
    }

    private CheckBillRes getCheckBillByBillNo(List<CheckBillRes> checkBillResList, Long billNo) {
        return checkBillResList.stream().filter(checkBillRes -> checkBillRes.getBillNo().equals(billNo)).findFirst().orElse(null);
    }
}
