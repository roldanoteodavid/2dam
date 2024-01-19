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
        Path file = Paths.get(Configuration.getInstance().getProperty(Constants.PATH_ORDERS));
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
        if (orderList.isEmpty()) {
            result = Either.left(new OrderErrorEmptyList());
        } else {
            result = Either.right(orderList);
        }
        return result;
    }

    @Override
    public Either<OrderError, Order> get(int id) {
        Either<OrderError, Order> result;
        List<Order> orderList = getAll().getOrElse(new ArrayList<>());
        Order foundOrder = orderList.stream()
                .filter(order -> order.getId() == id)
                .findFirst()
                .orElse(null);

        if (foundOrder != null) {
            result = Either.right(foundOrder);
        } else {
            result = Either.left(new OrderError(Constants.ORDER_NOT_FOUND));
        }
        return result;
    }

    @Override
    public Either<OrderError, Integer> save(Order c) {
        Either<OrderError, Integer> result;
        if (get(c.getId()).isLeft()) {
            String fileData = "\n" + c.toFileString();
            Path file = Paths.get(Configuration.getInstance().getProperty(Constants.PATH_ORDERS));
            try (BufferedWriter writer = Files.newBufferedWriter(file, StandardOpenOption.APPEND)) {
                writer.write(fileData);
                result = Either.right(0);
            } catch (IOException e) {
                result = Either.left(new OrderError(Constants.ERROR_SAVING_ORDER));
            }
        } else {
            result = Either.left(new OrderError(Constants.ORDER_ID_ALREADY_EXISTS));
        }
        return result;
    }

    @Override
    public Either<OrderError, Integer> update(Order c) {
        Either<OrderError, Integer> result;
        if (get(c.getId()).isRight()) {
            Path file = Paths.get(Configuration.getInstance().getProperty(Constants.PATH_ORDERS));
            try {
                List<String> lines = Files.readAllLines(file);
                Order order = get(c.getId()).get();
                int index = lines.indexOf(order.toFileString());
                lines.set(index, c.toFileString());
                //lines.remove(order.toFileString());
                //lines.add(c.toFileString());
                try (BufferedWriter writer = Files.newBufferedWriter(file)) {
                    for (String line : lines) {
                        writer.write(line);
                        writer.newLine();
                    }
                }
                result = Either.right(0);
            } catch (IOException e) {
                result = Either.left(new OrderError(Constants.ERROR_UPDATING_ORDER));
            }
        } else {
            result = Either.left(new OrderError(Constants.ORDER_NOT_FOUND));
        }
        return result;
    }

    @Override
    public Either<OrderError, Integer> delete(Order c) {
        Either<OrderError, Integer> result;
        if (get(c.getId()).isRight()) {
            Path file = Paths.get(Configuration.getInstance().getProperty(Constants.PATH_ORDERS));
            try {
                List<String> lines = Files.readAllLines(file);
                lines.remove(c.toFileString());
                try (BufferedWriter writer = Files.newBufferedWriter(file)) {
                    for (String line : lines) {
                        writer.write(line);
                        writer.newLine();
                    }
                }
                result = Either.right(0);
            } catch (IOException e) {
                result = Either.left(new OrderError(Constants.ERROR_DELETING_ORDER));
            }
        } else {
            result = Either.left(new OrderError(Constants.ORDER_NOT_FOUND));
        }
        return result;
    }
}
