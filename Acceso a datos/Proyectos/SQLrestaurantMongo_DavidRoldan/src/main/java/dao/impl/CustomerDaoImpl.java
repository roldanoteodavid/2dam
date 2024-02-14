package dao.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import common.Constants;
import dao.CustomersDAO;
import dao.adapters.LocalDateAdapter;
import dao.adapters.LocalDateTimeAdapter;
import dao.adapters.ObjectIdAdapter;
import io.vavr.control.Either;
import lombok.extern.log4j.Log4j2;
import model.Credential;
import model.Customer;
import model.errors.RestaurantError;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static com.mongodb.client.model.Filters.eq;

@Log4j2
public class CustomerDaoImpl implements CustomersDAO {

    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .registerTypeAdapter(ObjectId.class, new ObjectIdAdapter())
            .create();

    @Override
    public Either<RestaurantError, List<Customer>> getAll() {
        Either<RestaurantError, List<Customer>> result = null;
        try (MongoClient mongo = MongoClients.create("mongodb://informatica.iesquevedo.es:2323")) {
            MongoDatabase db = mongo.getDatabase("davidroldan_restaurant");
            MongoCollection<Document> customers = db.getCollection("customers");
            List<Customer> customerList = new ArrayList<>();
            List<Document> documents = customers.find().into(new ArrayList<>());
            for (Document document : documents) {
                customerList.add(convertDocumentToCustomer(document));
            }
            result = Either.right(customerList);
        } catch (Exception e) {
            log.error(e.getMessage());
            result = Either.left(new RestaurantError(Constants.ERROR_OBTAINING_CUSTOMERS));
        }
        return result;
    }

    @Override
    public Either<RestaurantError, Customer> get(ObjectId id) {
        Either<RestaurantError, Customer> result = null;
        try (MongoClient mongo = MongoClients.create("mongodb://informatica.iesquevedo.es:2323")) {
            MongoDatabase db = mongo.getDatabase("davidroldan_restaurant");
            MongoCollection<Document> customers = db.getCollection("customers");

            // Find the document with the specified ID
            Document customerDocument = customers.find(eq("_id", id)).first();

            if (customerDocument != null) {
                // Convert Document to Customer using Gson
                Customer customer = convertDocumentToCustomer(customerDocument);
                result = Either.right(customer);
            } else {
                result = Either.left(new RestaurantError(Constants.ERROR_OBTAINING_CUSTOMER));
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            result = Either.left(new RestaurantError(Constants.ERROR_OBTAINING_CUSTOMER));
        }
        return result;
    }

    @Override
    public Either<RestaurantError, Customer> get(LocalDateTime orderdate) {
        Either<RestaurantError, Customer> result = null;
        try (MongoClient mongo = MongoClients.create("mongodb://informatica.iesquevedo.es:2323")) {
            MongoDatabase db = mongo.getDatabase("davidroldan_restaurant");
            MongoCollection<Document> customers = db.getCollection("customers");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String orderDateStr = orderdate.format(formatter);

            // Buscar el cliente que contiene el pedido
            Bson filter = Filters.elemMatch("orders", Filters.eq("order_date", orderDateStr));
            Document customerDocument = customers.find(filter).first();

            if (customerDocument != null) {
                // Convert Document to Customer using Gson
                Customer customer = convertDocumentToCustomer(customerDocument);
                result = Either.right(customer);
            } else {
                result = Either.left(new RestaurantError(Constants.ERROR_OBTAINING_CUSTOMER));
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            result = Either.left(new RestaurantError(Constants.ERROR_OBTAINING_CUSTOMER));
        }
        return result;
    }

    @Override
    public Either<RestaurantError, Integer> save(Customer c, Credential credential) {
        Either<RestaurantError, Integer> result;
        try (MongoClient mongo = MongoClients.create("mongodb://informatica.iesquevedo.es:2323")) {
            MongoDatabase db = mongo.getDatabase("davidroldan_restaurant");
            MongoCollection<Document> customers = db.getCollection("customers");
            MongoCollection<Document> credentials = db.getCollection("credentials");

            // Convert Customer to Document using Gson
            Document customerDocument = Document.parse(gson.toJson(c));

            // Insert the document into the collection
            customers.insertOne(customerDocument);

            ObjectId customerId = (ObjectId) customerDocument.get("_id");

            // Establecer la id generada en la entidad Credential
            credential.set_id(customerId);

            // Convert Credential to Document using Gson
            Document credentialDocument = Document.parse(gson.toJson(credential));

            // Insertar el documento del Credential en la colección
            credentials.insertOne(credentialDocument);

            result = Either.right(0);
        } catch (Exception e) {
            log.error(e.getMessage());
            result = Either.left(new RestaurantError(Constants.ERROR_ADDING_CUSTOMER));
        }
        return result;
    }

    @Override
    public Either<RestaurantError, Integer> update(Customer c) {
        Either<RestaurantError, Integer> result = null;
        try (MongoClient mongo = MongoClients.create("mongodb://informatica.iesquevedo.es:2323")) {
            MongoDatabase db = mongo.getDatabase("davidroldan_restaurant");
            MongoCollection<Document> customers = db.getCollection("customers");

            Document updatedCustomer = Document.parse(gson.toJson(c));

            customers.replaceOne(Filters.eq("_id", c.getId()), updatedCustomer);

            result = Either.right(0);
        } catch (Exception e) {
            log.error(e.getMessage());
            result = Either.left(new RestaurantError(Constants.ERROR_UPDATING_CUSTOMER));
        }
        return result;
    }


    @Override
    public Either<RestaurantError, Integer> delete(Customer c, boolean deleteOrders) {
        Either<RestaurantError, Integer> result = null;
        try (MongoClient mongo = MongoClients.create("mongodb://informatica.iesquevedo.es:2323")) {
            MongoDatabase db = mongo.getDatabase("davidroldan_restaurant");
            MongoCollection<Document> customers = db.getCollection("customers");
            MongoCollection<Document> credentials = db.getCollection("credentials");

            // Delete the customer document based on the customer ID
            if (customers.find(Filters.eq("_id", c.getId())).first() != null) {
                customers.deleteOne(Filters.eq("_id", c.getId()));
                credentials.deleteOne(Filters.eq("_id", c.getId()));
                result = Either.right(0);
            } else {
                result = Either.left(new RestaurantError(Constants.CUSTOMER_NOT_FOUND));
            }
        } catch (Exception ex) {
            // Handle exceptions accordingly
            result = Either.left(new RestaurantError(Constants.ERROR_DELETING_CUSTOMER));
        }
        return result;
    }


    private Customer convertDocumentToCustomer(Document document) {
        return gson.fromJson(document.toJson(), Customer.class);
    }
}
