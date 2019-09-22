package com.ampos.test.restaurant.querydsl;

import com.ampos.test.restaurant.model.Menu;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.StringPath;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.math.NumberUtils;

@Data
@AllArgsConstructor
public class MenuPredicate {

    private SearchCriteria criteria;


    public BooleanExpression getPredicate() {
        PathBuilder<Menu> entityPath = new PathBuilder<>(Menu.class, "menu");

        if (NumberUtils.isDigits(criteria.getValue().toString())) {
            NumberPath<Integer> path = entityPath.getNumber(criteria.getKey(), Integer.class);
            int value = Integer.parseInt(criteria.getValue().toString());
            switch (criteria.getOperation()) {
                case ":":
                    return path.eq(value);
                case ">":
                    return path.goe(value);
                case "<":
                    return path.loe(value);
            }
        } else {
            StringPath path = entityPath.getString(criteria.getKey());
            if (criteria.getOperation().equalsIgnoreCase(":")) {
                return path.containsIgnoreCase(criteria.getValue().toString());
            }
        }
        return null;
    }
}
