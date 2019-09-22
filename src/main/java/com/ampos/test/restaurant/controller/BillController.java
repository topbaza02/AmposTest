package com.ampos.test.restaurant.controller;

import com.ampos.test.restaurant.model.Bill;
import com.ampos.test.restaurant.model.req.OrderRequest;
import com.ampos.test.restaurant.model.res.CheckBillRes;
import com.ampos.test.restaurant.querydsl.BillPredicatesBuilder;
import com.ampos.test.restaurant.service.BillService;
import com.querydsl.core.types.dsl.BooleanExpression;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/bills")
public class BillController {
    @Autowired
    private BillService service;

    @GetMapping
    @ResponseBody
    public Iterable<Bill> search(@RequestParam(value = "search") String search) {
        System.out.println("search==============");
        BillPredicatesBuilder builder = new BillPredicatesBuilder();

        if (search != null) {
            Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
            Matcher matcher = pattern.matcher(search + ",");
            while (matcher.find()) {
                builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
            }
        }
        BooleanExpression exp = builder.build();
        return service.findAll(exp);
    }


    @PostMapping
    public ResponseEntity<List<Bill>> create(@RequestBody OrderRequest request) {
        return ResponseEntity.ok(service.save(request));
    }


    @PutMapping
    public ResponseEntity<List<Bill>> update(@RequestBody OrderRequest request) {
        return ResponseEntity.ok(service.update(request));
    }

    @DeleteMapping(value = "/{billNo}")
    public ResponseEntity<Long> delete(@PathVariable Long billNo) {
        return ResponseEntity.ok(service.delete(billNo));
    }

    @GetMapping(value = "checkbill")
    public ResponseEntity<List<CheckBillRes>> checkBill() {
        return ResponseEntity.ok(service.checkBill());
    }
}
