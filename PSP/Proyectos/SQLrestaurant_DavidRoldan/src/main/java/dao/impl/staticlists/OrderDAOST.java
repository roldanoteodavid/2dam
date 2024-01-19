package dao.impl.staticlists;

import io.vavr.control.Either;
import model.Order;
import model.errors.OrderError;
import model.errors.OrderErrorEmptyList;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOST {

    public Either<OrderError, List<Order>> getAll() {
        Either<OrderError, List<Order>> result;
        //Fill with data
        List<Order> orderList = new ArrayList<>();

        orderList.add(new Order(1, LocalDateTime.now(), 1, 1));
        orderList.add(new Order(2, LocalDateTime.now(), 2, 2));
        orderList.add(new Order(3, LocalDateTime.now(), 3, 3));
        orderList.add(new Order(4, LocalDateTime.now(), 4, 4));
        orderList.add(new Order(5, LocalDateTime.now(), 5, 5));
        orderList.add(new Order(6, LocalDateTime.now(), 6, 6));
        if (orderList.isEmpty()) result = Either.left(new OrderErrorEmptyList());
        else result = Either.right(orderList);
        return result;
    }

    public Either<OrderError, Order> get(int id) {
        return null;
    }

    public int save(Order c) {
        return 0;
    }

    public int update(Order c) {
        return 0;
    }

    public int delete(Order c) {
        return 0;
    }
}
