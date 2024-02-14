package dao.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import common.Constants;
import dao.LoginDAO;
import dao.adapters.LocalDateAdapter;
import dao.adapters.LocalDateTimeAdapter;
import dao.adapters.ObjectIdAdapter;
import io.vavr.control.Either;
import lombok.extern.log4j.Log4j2;
import model.Credential;
import model.errors.RestaurantError;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Log4j2
public class DaoLoginImpl implements LoginDAO {


    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .registerTypeAdapter(ObjectId.class, new ObjectIdAdapter())
            .create();


    @Override
    public Either<RestaurantError, Credential> get(Credential credential) {
        Either<RestaurantError, Credential> result = null;

        try (MongoClient mongo = MongoClients.create("mongodb://informatica.iesquevedo.es:2323")) {
            MongoDatabase db = mongo.getDatabase("davidroldan_restaurant");
            MongoCollection<Document> credentials = db.getCollection("credentials");

            // Construye un filtro con el nombre de usuario y la contraseña
            Bson filter = Filters.and(
                    Filters.eq("user_name", credential.getUser_name()),
                    Filters.eq("password", credential.getPassword())
            );

            Document credentialDoc = credentials.find(filter).first();

            if (credentialDoc != null) {
                Credential credentialResult = documentToCredential(credentialDoc);
                result = Either.right(credentialResult);
            } else {
                result = Either.left(new RestaurantError(Constants.CREDENTIAL_NOT_FOUND));
            }
        } catch (Exception e) {
            result = Either.left(new RestaurantError(Constants.ERROR_OBTAINING_CREDENTIAL));
        }

        return result;
    }

    @Override
    public Either<RestaurantError, Integer> save(Credential credential) {
        Either<RestaurantError, Integer> result = null;
        try(MongoClient mongo = MongoClients.create("mongodb://informatica.iesquevedo.es:2323")) {
            MongoDatabase db = mongo.getDatabase("davidroldan_restaurant");
            MongoCollection<Document> credentials = db.getCollection("credentials");
            Document credentialDoc = Document.parse(new Gson().toJson(credential));
            credentials.insertOne(credentialDoc);
            result = Either.right(0);
        } catch (Exception e) {
            log.error(e.getMessage());
            result = Either.left(new RestaurantError(Constants.ERROR_ADDING_CREDENTIALS));
        }
        return result;
    }

    private Credential documentToCredential(Document document) {
        String json = document.toJson();
        return gson.fromJson(json, Credential.class);
    }
}
