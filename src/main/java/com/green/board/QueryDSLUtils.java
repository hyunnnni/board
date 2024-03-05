package com.green.board;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.PathBuilder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class QueryDSLUtils {
    public OrderSpecifier[] getOrderSpecifier(Pageable pageable, Class<?> type, PathMetadata metadata) {
        List<OrderSpecifier> orders = new LinkedList();
        PathBuilder pathBuilder = new PathBuilder(type, metadata);

        for (Sort.Order orderItem : pageable.getSort()) {
            Order order = orderItem.isAscending() ? Order.ASC : Order.DESC;
            orders.add(new OrderSpecifier(order, pathBuilder.get(orderItem.getProperty())));
        }
        return orders.toArray(OrderSpecifier[]::new);
    }
}

