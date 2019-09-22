package com.ampos.test.restaurant.repo;

import com.ampos.test.restaurant.model.Menu;
import com.ampos.test.restaurant.model.QMenu;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import java.util.List;
import java.util.Set;
import javax.transaction.Transactional;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(collectionResourceRel = "menus", path = "menus")
public interface MenuRepository extends PagingAndSortingRepository<Menu, Long>
        , QuerydslPredicateExecutor<Menu>, QuerydslBinderCustomizer<QMenu> {
    default void customize(
            QuerydslBindings bindings, QMenu root) {
        bindings.bind(String.class)
                .first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
        bindings.excluding(root.name);
    }

    @Transactional
    List<Menu> findByIdIn(Set<Long> idSet);
}
