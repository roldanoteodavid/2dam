package dao.impl;

import com.google.gson.Gson;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import common.Constants;
import dao.MenuItemDAO;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import static com.mongodb.client.model.Filters.eq;
import jakarta.persistence.EntityManager;
import lombok.extern.log4j.Log4j2;
import model.MenuItem;
import model.errors.RestaurantError;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

@Log4j2
public class MenuItemDaoImpl implements MenuItemDAO {


    @Override
    public Either<RestaurantError, List<MenuItem>> getAll() {
        Either<RestaurantError, List<MenuItem>> result = null;
        try(MongoClient mongo = MongoClients.create("mongodb://informatica.iesquevedo.es:2323")) {
            MongoDatabase db = mongo.getDatabase("davidroldan_restaurant");
            MongoCollection<Document> menuItems = db.getCollection("menu_items");
            List<MenuItem> menuItemList = new ArrayList<>();
            List<Document> documents = menuItems.find().into(new ArrayList<>());
            for (Document document : documents) {
                menuItemList.add(convertDocumentToMenuItem(document));
            }
            result = Either.right(menuItemList);
        } catch (Exception e) {
            result = Either.left(new RestaurantError(Constants.ERROR_OBTAINING_CUSTOMERS));
        }
        return result;
    }

    @Override
    public Either<RestaurantError, MenuItem> get(int id) {
        Either<RestaurantError, MenuItem> result = null;
        try(MongoClient mongo = MongoClients.create("mongodb://informatica.iesquevedo.es:2323")) {
            MongoDatabase db = mongo.getDatabase("davidroldan_restaurant");
            MongoCollection<Document> menuItems = db.getCollection("menu_items");

            Document menuItemDocument = menuItems.find(eq("_id", id)).first();
            if (menuItemDocument != null) {
                MenuItem menuItem = convertDocumentToMenuItem(menuItemDocument);
                result = Either.right(menuItem);
            } else {
                result = Either.left(new RestaurantError(Constants.ERROR_OBTAINING_CUSTOMERS));
            }
        } catch (Exception e) {
            result = Either.left(new RestaurantError(Constants.ERROR_OBTAINING_CUSTOMERS));
        }
        return result;
    }

    @Override
    public Either<RestaurantError, Integer> save(MenuItem m) {
        Either<RestaurantError, Integer> result = null;
        try(MongoClient mongo = MongoClients.create("mongodb://informatica.iesquevedo.es:2323")) {
            MongoDatabase db = mongo.getDatabase("davidroldan_restaurant");
            MongoCollection<Document> menuItems = db.getCollection("menu_items");
            Document menuItemDocument = Document.parse(new Gson().toJson(m));
            menuItems.insertOne(menuItemDocument);
            result = Either.right(0);
        } catch (Exception e) {
            log.error(e.getMessage());
            result = Either.left(new RestaurantError(Constants.ERROR_OBTAINING_CUSTOMERS));
        }
        return null;
    }

    private MenuItem convertDocumentToMenuItem(Document document) {
        return new Gson().fromJson(document.toJson(), MenuItem.class);
    }
}
