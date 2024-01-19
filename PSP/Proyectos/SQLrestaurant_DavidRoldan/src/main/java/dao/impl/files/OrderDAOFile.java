package dao.impl.files;

import common.Constants;
import config.Configuration;
import dao.OrderDAO;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import model.Order;
import model.errors.OrderError;
import model.errors.OrderErrorEmptyList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOFile implements OrderDAO {

    private final Configuration config;

    @Inject
    @Named("OrderDAOFile")
    public OrderDAOFile(Configuration config) {
        this.config = config;
    }

    @Override
    public Either<OrderError, List<Order>> getAll() {
        Path file = Paths.get(Configuration.getInstance().getProperty("pathOrders"));
        Either<OrderError, List<Order>> result;
        List<Order> orderList = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(file)) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    Order order = new Order(line);
                    orderList.add(order);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (orderList.isEmpty()) result = Either.left(new OrderErrorEmptyList());
        else result = Either.right(orderList);
        return result;
    }

    @Override
    public Either<OrderError, Order> get(int id) {
        List<Order> orderList = getAll().getOrElse(new ArrayList<>());
        Order foundOrder = orderList.stream()
                .filter(order -> order.getId() == id)
                .findFirst()
                .orElse(null);

        if (foundOrder != null) {
            return Either.right(foundOrder);
        } else {
            return Either.left(new OrderError(Constants.ORDER_NOT_FOUND));
        }
    }

    @Override
    public Either<OrderError, Integer> save(Order c) {
        if (get(c.getId()).isLeft()) {
            String fileData = "\n"+c.toFileString();
            Path file = Paths.get(Configuration.getInstance().getProperty("pathOrders"));
            try (BufferedWriter writer = Files.newBufferedWriter(file, StandardOpenOption.APPEND)) {
                writer.write(fileData);
                return Either.right(0);
            } catch (IOException e) {
                return Either.left(new OrderError(Constants.ERROR_SAVING_ORDER));
            }
        } else {
            return Either.left(new OrderError(Constants.ORDER_ID_ALREADY_EXISTS));
        }
    }

    @Override
    public Either<OrderError, Integer> update(Order c) {
        return Either.right(0);
    }

    @Override
    public Either<OrderError, Integer> delete(Order c) {
        if (get(c.getId()).isRight()) {
            Path file = Paths.get(Configuration.getInstance().getProperty("pathOrders"));
            try {
                List<String> lines = Files.readAllLines(file);
                lines.remove(c.toFileString());
                try (BufferedWriter writer = Files.newBufferedWriter(file)) {
                    for (String line : lines) {
                        writer.write(line);
                        writer.newLine();
                    }
                }
                return Either.right(0);
            } catch (IOException e) {
                return Either.left(new OrderError(Constants.ERROR_DELETING_ORDER));
            }
        } else {
            return Either.left(new OrderError(Constants.ORDER_NOT_FOUND));
        }
    }
}
