package dao.impl.files;

import common.Constants;
import config.Configuration;
import dao.OrderItemDAO;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import model.MenuItem;
import model.Order;
import model.OrderItem;
import model.errors.RestaurantError;
import model.xml.OrderItemXML;
import model.xml.OrderXML;
import model.xml.OrdersXML;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDAOXMLimpl implements OrderItemDAO {

    private final Configuration config;

    @Named("CustomerDAOFile")
    @Inject
    public OrderItemDAOXMLimpl(Configuration config) {
        this.config = config;
    }

    @Override
    public Either<RestaurantError, List<OrderItem>> getAll(int idOrder) {
        Either<RestaurantError, List<OrderItem>> result;
        Path readersFile = Paths.get(Configuration.getInstance().getProperty(Constants.PATH_XML_ORDERS));
        try {
            JAXBContext context = JAXBContext.newInstance(OrdersXML.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            OrdersXML ordersList = (OrdersXML) unmarshaller.unmarshal(Files.newInputStream(readersFile));
            OrderXML order = ordersList.getOrders().stream().filter(order1 -> order1.getId() == idOrder).findFirst().orElse(null);
            if (order != null) {
                List<OrderItemXML> orderItemsXML = order.getOrderItems();
                if (orderItemsXML == null) {
                    orderItemsXML = new ArrayList<>();
                }
                List<OrderItem> orderItems = new ArrayList<>();
                orderItemsXML.forEach(orderItemXML -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setQuantity(orderItemXML.getQuantity());
                    MenuItem menuItem = new MenuItem();
                    menuItem.setName(orderItemXML.getMenu_item());
                    orderItem.setMenuItem(menuItem);
                    orderItems.add(orderItem);
                });
                result = Either.right(orderItems);
            } else {
                result = Either.left(new RestaurantError("No hay orders con ese id"));
            }
        } catch (JAXBException | IOException e) {
            result = Either.left(new RestaurantError("Error reading the file"));
        }
        return result;
    }

    @Override
    public Either<RestaurantError, List<OrderItemXML>> get(Order o) {
        Either<RestaurantError, List<OrderItemXML>> result;
        Path readersFile = Paths.get(Configuration.getInstance().getProperty(Constants.PATH_XML_ORDERS));
        try {
            JAXBContext context = JAXBContext.newInstance(OrdersXML.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            OrdersXML ordersList = (OrdersXML) unmarshaller.unmarshal(Files.newInputStream(readersFile));
            OrderXML order = ordersList.getOrders().stream().filter(order1 -> order1.getId() == o.getId()).findFirst().orElse(null);
            if (order != null) {
                //OrderItemXML orderItemXML = order.getOrderItems().stream().filter(orderItemXML1 -> orderItemXML1.getMenu_item().equals(c.getMenuItem().getName())).findFirst().orElse(null);
                List<OrderItemXML> orderItemXML = order.getOrderItems();
                if (orderItemXML != null) {
                    result = Either.right(orderItemXML);
                } else {
                    result = Either.left(new RestaurantError("No hay orderItems con ese menu_item"));
                }
            } else {
                result = Either.left(new RestaurantError("No hay orders con ese id"));
            }
        } catch (JAXBException | IOException e) {
            result = Either.left(new RestaurantError("Error reading the file"));
        }
        return result;
    }

    @Override
    public Either<RestaurantError, Integer> save(List<OrderItem> c, Order o) {
        Either<RestaurantError, Integer> result;
        if (get(o).isLeft()) {
            Path readersFile = Paths.get(Configuration.getInstance().getProperty(Constants.PATH_XML_ORDERS));
            try {
                JAXBContext context = JAXBContext.newInstance(OrdersXML.class);
                Marshaller marshaller = context.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                OrdersXML ordersList = (OrdersXML) unmarshaller.unmarshal(Files.newInputStream(readersFile));
                OrderXML order = ordersList.getOrders().stream().filter(order1 -> order1.getId() == o.getId()).findFirst().orElse(null);
                if (order != null) {
                    c.stream().forEach(orderItem -> {
                        OrderItemXML orderItemXML = new OrderItemXML(orderItem.getMenuItem().getName(), orderItem.getQuantity());
                        order.getOrderItems().add(orderItemXML);
                    });
                    marshaller.marshal(ordersList, Files.newOutputStream(readersFile));
                    result = Either.right(0);
                } else {
                    OrderXML neworder = new OrderXML();
                    neworder.setId(o.getId());
                    ordersList.getOrders().add(neworder);
                    neworder.setOrderItems(new ArrayList<>());
                    c.stream().forEach(orderItem -> {
                        OrderItemXML orderItemXML = new OrderItemXML(orderItem.getMenuItem().getName(), orderItem.getQuantity());
                        neworder.getOrderItems().add(orderItemXML);
                    });
                    marshaller.marshal(ordersList, Files.newOutputStream(readersFile));
                    result = Either.right(0);
                }
            } catch (JAXBException | IOException e) {
                result = Either.left(new RestaurantError("Error reading the file"));
            }
        } else {
            result = Either.left(new RestaurantError("Order item already exists"));
        }
        return result;
    }

    @Override
    public Either<RestaurantError, Integer> update(List<OrderItem> c, Order o) {
        Either<RestaurantError, Integer> result;
        Path readersFile = Paths.get(Configuration.getInstance().getProperty(Constants.PATH_XML_ORDERS));
        try {
            JAXBContext context = JAXBContext.newInstance(OrdersXML.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            OrdersXML ordersList = (OrdersXML) unmarshaller.unmarshal(Files.newInputStream(readersFile));
            OrderXML order = ordersList.getOrders().stream().filter(order1 -> order1.getId() == o.getId()).findFirst().orElse(null);
            if (order != null) {
                order.setOrderItems(new ArrayList<>());
                c.stream().forEach(orderItem -> {
                    OrderItemXML orderItemXML = new OrderItemXML(orderItem.getMenuItem().getName(), orderItem.getQuantity());
                    order.getOrderItems().add(orderItemXML);
                });
                marshaller.marshal(ordersList, Files.newOutputStream(readersFile));
                result = Either.right(0);
            } else {
                result = Either.left(new RestaurantError("No hay orders con ese id"));
            }
        } catch (JAXBException | IOException e) {
            result = Either.left(new RestaurantError("Error reading the file"));
        }

        return result;
    }

    @Override
    public Either<RestaurantError, Integer> delete(Order o) {
        Either<RestaurantError, Integer> result;
        Path readersFile = Paths.get(Configuration.getInstance().getProperty(Constants.PATH_XML_ORDERS));
        try {
            JAXBContext context = JAXBContext.newInstance(OrdersXML.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            OrdersXML ordersList = (OrdersXML) unmarshaller.unmarshal(Files.newInputStream(readersFile));
            OrderXML order = ordersList.getOrders().stream().filter(order1 -> order1.getId() == o.getId()).findFirst().orElse(null);
            if (order != null) {
                ordersList.getOrders().remove(order);
                marshaller.marshal(ordersList, Files.newOutputStream(readersFile));
                result = Either.right(0);
            } else {
                result = Either.left(new RestaurantError("No hay orders con ese id"));
            }
        } catch (JAXBException | IOException e) {
            result = Either.left(new RestaurantError("Error reading the file"));
        }
        return result;
    }
}
