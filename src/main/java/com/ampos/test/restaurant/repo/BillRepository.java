package com.ampos.test.restaurant.repo;

import com.ampos.test.restaurant.model.Bill;
import com.ampos.test.restaurant.model.QBill;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(collectionResourceRel = "bills", path = "bills")
public interface BillRepository extends PagingAndSortingRepository<Bill, Long>
        , QuerydslPredicateExecutor<Bill>, QuerydslBinderCustomizer<QBill> {
    default void customize(
            QuerydslBindings bindings, QBill root) {
        bindings.bind(String.class)
                .first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
        bindings.excluding(root.billNo);
    }

    @Query("SELECT MAX(u.billNo) FROM Bill u")
    Long findMaxBillNo();

    @Transactional
    void deleteByBillNo(Long billNo);
}
