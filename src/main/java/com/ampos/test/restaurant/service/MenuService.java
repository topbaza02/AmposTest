package com.ampos.test.restaurant.service;

import com.ampos.test.restaurant.model.Menu;
import com.ampos.test.restaurant.repo.MenuRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuService {
    @Autowired
    private MenuRepository repository;

    public Menu save(Menu menu) {
        return repository.save(menu);
    }

    public List<Menu> save(List<Menu> menus) {
        return (List<Menu>) repository.saveAll(menus);
    }

    public Iterable<Menu> findAll(BooleanExpression exp) {
        return repository.findAll(exp);
    }

    public List<Menu> findByIdIn(Set<Long> idSet) {
        return repository.findByIdIn(idSet);
    }
}
