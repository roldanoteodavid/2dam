package dao.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import common.Constants;
import common.SQLQueries;
import dao.OrderDAO;
import dao.adapters.LocalDateAdapter;
import dao.adapters.LocalDateTimeAdapter;
import dao.adapters.ObjectIdAdapter;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import lombok.extern.log4j.Log4j2;
import model.Customer;
import model.Order;
import model.OrderItem;
import model.errors.RestaurantError;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import static com.mongodb.client.model.Filters.eq;

@Log4j2
@Named(Constants.JDBC_ORDER_DAO)
public class OrderDaoImpl implements OrderDAO {

    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .registerTypeAdapter(ObjectId.class, new ObjectIdAdapter())
            .create();

    private JPAUtil jpaUtil;
    private EntityManager em;

    @Inject
    public OrderDaoImpl(JPAUtil jpaUtil) {
        this.jpaUtil = jpaUtil;
    }

    @Override
    public Either<RestaurantError, List<Order>> getAll() {
        Either<RestaurantError, List<Order>> result = null;
        try (MongoClient mongo = MongoClients.create("mongodb://informatica.iesquevedo.es:2323")) {
            MongoDatabase db = mongo.getDatabase("davidroldan_restaurant");
            MongoCollection<Document> customers = db.getCollection("customers");

            List<Customer> customerList = new ArrayList<>();
            List<Order> orderList = new ArrayList<>();
            List<Document> documents = customers.find().into(new ArrayList<>());
            for (Document document : documents) {
                customerList.add(convertDocumentToCustomer(document));
            }
            for (Customer customer : customerList) {
                List<Order> orders = customer.getOrders();
                if (orders != null){
                    orderList.addAll(orders);
                }
            }
            result = Either.right(orderList);
        } catch (Exception e) {
            log.error(e.getMessage());
            result = Either.left(new RestaurantError(Constants.ERROR_OBTAINING_CUSTOMERS));
        }
        return result;
    }

    @Override
    public Either<RestaurantError, List<Order>> getAll(int idcustomer) {
        return null;
    }

    @Override
    public Either<RestaurantError, Order> get(int id) {
        Either<RestaurantError, Order> result = null;
        //nada
        return result;
    }

    @Override
    public Either<RestaurantError, Integer> save(Order c, ObjectId id) {
        Either<RestaurantError, Integer> result;
        try (MongoClient mongo = MongoClients.create("mongodb://informatica.iesquevedo.es:2323")) {
            MongoDatabase db = mongo.getDatabase("davidroldan_restaurant");
            MongoCollection<Document> customers = db.getCollection("customers");

            Document customerDocument = customers.find(eq("_id", id)).first();

            if (customerDocument != null) {
                // Convert Document to Customer using Gson
                Customer customer = convertDocumentToCustomer(customerDocument);
                if (customer.getOrders() == null) {
                    customer.setOrders(new ArrayList<>());
                }
                customer.getOrders().add(c);

                Document updatedCustomer = Document.parse(gson.toJson(customer));
                customers.replaceOne(Filters.eq("_id", id), updatedCustomer);

                result = Either.right(0);
            } else {
                result = Either.left(new RestaurantError(Constants.ERROR_OBTAINING_CUSTOMER));
            }
        } catch (Exception ex) {
            result = Either.left(new RestaurantError(Constants.ERROR_ADDING_ORDER));
        }
        return result;
    }

    @Override
    public Either<RestaurantError, Integer> save(List<Order> c) {
        return null;
    }

    @Override
    public Either<RestaurantError, Integer> update(Order order) {
        Either<RestaurantError, Integer> result;
        try (MongoClient mongo = MongoClients.create("mongodb://informatica.iesquevedo.es:2323")) {
            MongoDatabase db = mongo.getDatabase("davidroldan_restaurant");
            MongoCollection<Document> customers = db.getCollection("customers");

            // Convertir la fecha de Order a formato de cadena
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String orderDateStr = order.getOrder_date().format(formatter);

            // Buscar el cliente que contiene el pedido a actualizar
            Bson filter = Filters.elemMatch("orders", Filters.regex("order_date", Pattern.compile("^" + orderDateStr)));
            Document customerDocument = customers.find(filter).first();

            if (customerDocument != null) {
                // Obtener la lista de pedidos del cliente
                List<Document> orders = (List<Document>) customerDocument.get("orders");

                // Iterar sobre la lista de pedidos y actualizar el pedido con la fecha deseada
                for (Document orderDoc : orders) {
                    if (orderDoc.getString("order_date").startsWith(orderDateStr)) {
                        // Actualizar los detalles del pedido con los nuevos valores
                        orderDoc.put("table_id", order.getTable_id());
                        List<Document> orderItemsDocuments = order.getOrder_items().stream().map(orderItem -> Document.parse(gson.toJson(orderItem))).toList();
                        orderDoc.put("order_items", orderItemsDocuments);

                        break; // No es necesario seguir iterando
                    }
                }

                // Actualizar el documento del cliente en la base de datos
                Bson update = Updates.set("orders", orders);
                customers.updateOne(Filters.eq("_id", customerDocument.getObjectId("_id")), update);

                result = Either.right(0);
            } else {
                result = Either.left(new RestaurantError(Constants.ORDER_NOT_FOUND));
            }
        } catch (Exception ex) {
            result = Either.left(new RestaurantError(Constants.ERROR_UPDATING_ORDER));
        }
        return result;
    }


    @Override
    public Either<RestaurantError, Integer> delete(Order order) {
        Either<RestaurantError, Integer> result;
        try (MongoClient mongo = MongoClients.create("mongodb://informatica.iesquevedo.es:2323")) {
            MongoDatabase db = mongo.getDatabase("davidroldan_restaurant");
            MongoCollection<Document> customers = db.getCollection("customers");

            // Convertir la fecha de Order a formato de cadena
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String orderDateStr = order.getOrder_date().format(formatter);

            // Buscar el cliente que contiene el pedido a borrar
            Bson filter = Filters.elemMatch("orders", Filters.regex("order_date", Pattern.compile("^" + orderDateStr)));
            Document customerDocument = customers.find(filter).first();

            if (customerDocument != null) {
                // Obtener la lista de pedidos del cliente
                List<Document> orders = (List<Document>) customerDocument.get("orders");

                // Iterar sobre la lista de pedidos y eliminar el pedido con una fecha que coincida
                orders.removeIf(orderDoc -> orderDoc.getString("order_date").startsWith(orderDateStr));

                // Actualizar el documento del cliente en la base de datos
                Bson update = Updates.set("orders", orders);
                customers.updateOne(Filters.eq("_id", customerDocument.getObjectId("_id")), update);

                result = Either.right(0);
            } else {
                result = Either.left(new RestaurantError(Constants.ORDER_NOT_FOUND));
            }
        } catch (Exception ex) {
            result = Either.left(new RestaurantError(Constants.ERROR_DELETING_ORDER));
        }
        return result;
    }




    private Customer convertDocumentToCustomer(Document document) {
        return gson.fromJson(document.toJson(), Customer.class);
    }
}
