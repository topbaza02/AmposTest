package com.ampos.test.restaurant.controller;

import com.ampos.test.restaurant.model.Menu;
import com.ampos.test.restaurant.querydsl.MenuPredicatesBuilder;
import com.ampos.test.restaurant.service.MenuService;
import com.querydsl.core.types.dsl.BooleanExpression;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/menus")
public class MenuController {
    @Autowired
    private MenuService service;

    @PostMapping
    public Menu create(@RequestBody Menu menu) {
        return service.save(menu);
    }

    @GetMapping(path = "/initial/data")
    public ResponseEntity<List<Menu>> initialData() {
        Menu menuA = new Menu("Sheet Pan Chicken", "One-dish dinners are a dream come true for busy cooks.", new BigDecimal(300), "chicken, sheet, powder");
        Menu menuB = new Menu("Shrimp Lo Mein", "Lo mein—a Chinese dish that means stirred noodles—is a standard takeout order for many Americans, but making it at home is an opportunity to not only expand your culinary repertoire", new BigDecimal(1050), "Shrimp, oil");
        Menu menuC = new Menu("Lemony Lentil Soup", "In this simple soup recipe, you’ll simmer everyday ingredients like carrots, onion, garlic, and spices with red lentils, which are the perfect soup legume since they break down into soft submission.", new BigDecimal(50), "lemon, water");
        Menu menuD = new Menu("Chicken Curry in a Hurry", "Wait a minute, you might be thinking. How does curry make a list of quick dinner ideas? Our hurried-up version uses rotisserie chicken to save time, and the meal’s ready in just over half and hour.", new BigDecimal(750), "chicken, curry");
        List<Menu> menus = Arrays.asList(menuA, menuB, menuC, menuD);
        return ResponseEntity.ok(service.save(menus));
    }

    @GetMapping
    @ResponseBody
    public Iterable<Menu> search(@RequestParam(value = "search") String search) {
        System.out.println("search==============");
        MenuPredicatesBuilder builder = new MenuPredicatesBuilder();

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

}
