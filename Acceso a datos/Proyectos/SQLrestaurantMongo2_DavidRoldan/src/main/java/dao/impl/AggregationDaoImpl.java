package dao.impl;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Field;
import common.Constants;
import dao.AggregationDAO;
import io.vavr.control.Either;
import model.errors.RestaurantError;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.mongodb.client.model.Accumulators.*;
import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Indexes.descending;
import static com.mongodb.client.model.Projections.*;

public class AggregationDaoImpl implements AggregationDAO {
    @Override
    public Either<RestaurantError, String> exA() {
        Either<RestaurantError, String> result = null;
        try (MongoClient mongo = MongoClients.create("mongodb://informatica.iesquevedo.es:2323")) {
            MongoDatabase db = mongo.getDatabase("davidroldan_restaurant");
            MongoCollection<Document> menuItems = db.getCollection("menu_items");

            //a. Get the description of the most expensive item
            List<Document> documents = menuItems.aggregate(
                            Arrays.asList(
                                    sort(descending("price")),
                                    limit(1),
                                    project(fields(include("description"), excludeId()))))
                    .into(new ArrayList<>());

            if (!documents.isEmpty()) {
                List<String> jsonDocuments = new ArrayList<>();
                for (Document document : documents) {
                    jsonDocuments.add(document.toJson());
                }
                String jsonResult = "[" + String.join(",", jsonDocuments) + "]";
                result = Either.right(jsonResult);
            } else {
                result = Either.left(new RestaurantError(Constants.NO_RESULTS));
            }
        } catch (Exception e) {
            result = Either.left(new RestaurantError(Constants.ERROR));
        }
        return result;
    }

    @Override
    public Either<RestaurantError, String> exB(ObjectId id) {
        Either<RestaurantError, String> result = null;
        try (MongoClient mongo = MongoClients.create("mongodb://informatica.iesquevedo.es:2323")) {
            MongoDatabase db = mongo.getDatabase("davidroldan_restaurant");
            MongoCollection<Document> customers = db.getCollection("customers");
            //b. Get the orders of a given customer, showing the name of the customer and the number of seats
            List<Document> documents = customers.aggregate(
                            Arrays.asList(
                                    match(eq("_id", id)),
                                    unwind("$orders"),
                                    project(fields(include("first_name", "orders.table_id"), excludeId())))
                    )
                    .into(new ArrayList<>());

            if (!documents.isEmpty()) {
                List<String> jsonDocuments = new ArrayList<>();
                for (Document document : documents) {
                    jsonDocuments.add(document.toJson());
                }
                String jsonResult = "[" + String.join(",", jsonDocuments) + "]";
                result = Either.right(jsonResult);
            } else {
                result = Either.left(new RestaurantError(Constants.NO_RESULTS));
            }
        } catch (Exception e) {
            result = Either.left(new RestaurantError(Constants.ERROR));
        }

        return result;
    }

    @Override
    public Either<RestaurantError, String> exC() {
        Either<RestaurantError, String> result = null;
        try (MongoClient mongo = MongoClients.create("mongodb://informatica.iesquevedo.es:2323")) {
            MongoDatabase db = mongo.getDatabase("davidroldan_restaurant");
            MongoCollection<Document> customers = db.getCollection("customers");
            //c. Get the number of items of each order
            List<Document> documents = customers.aggregate(
                            Arrays.asList(
                                    unwind("$orders"),
                                    unwind("$orders.order_items"),
                                    group("$orders.order_date", sum("totalItems", "$orders.order_items.quantity")))
                    )
                    .into(new ArrayList<>());

            if (!documents.isEmpty()) {
                List<String> jsonDocuments = new ArrayList<>();
                for (Document document : documents) {
                    jsonDocuments.add(document.toJson());
                }
                String jsonResult = "[" + String.join(",", jsonDocuments) + "]";
                result = Either.right(jsonResult);
            } else {
                result = Either.left(new RestaurantError(Constants.NO_RESULTS));
            }
        } catch (Exception e) {
            result = Either.left(new RestaurantError(Constants.ERROR));
        }

        return result;
    }

    @Override
    public Either<RestaurantError, String> exD() {
        Either<RestaurantError, String> result = null;
        try (MongoClient mongo = MongoClients.create("mongodb://informatica.iesquevedo.es:2323")) {
            MongoDatabase db = mongo.getDatabase("davidroldan_restaurant");
            MongoCollection<Document> customers = db.getCollection("customers");
            //d. Get the name of the customers who have ordered steak
            List<Document> documents = customers.aggregate(
                            Arrays.asList(
                                    match(eq("orders.order_items.menu_item_id", 3)),
                                    project(fields(include("first_name", "last_name"), excludeId())))
                    )
                    .into(new ArrayList<>());

            if (!documents.isEmpty()) {
                List<String> jsonDocuments = new ArrayList<>();
                for (Document document : documents) {
                    jsonDocuments.add(document.toJson());
                }
                String jsonResult = "[" + String.join(",", jsonDocuments) + "]";
                result = Either.right(jsonResult);
            } else {
                result = Either.left(new RestaurantError(Constants.NO_RESULTS));
            }
        } catch (Exception e) {
            result = Either.left(new RestaurantError(Constants.ERROR));
        }
        return result;
    }

    @Override
    public Either<RestaurantError, String> exE() {
        Either<RestaurantError, String> result = null;
        try (MongoClient mongo = MongoClients.create("mongodb://informatica.iesquevedo.es:2323")) {
            MongoDatabase db = mongo.getDatabase("davidroldan_restaurant");
            MongoCollection<Document> customers = db.getCollection("customers");
            //e. Get the average number of items per order


            List<Document> documents = customers.aggregate(
                            Arrays.asList(
                                    unwind("$orders"),
                                    unwind("$orders.order_items"),
                                    group("$orders.order_date", sum("totalItems", "$orders.order_items.quantity")),
                                    group(0, avg("average", "$totalItems")))

                    )
                    .into(new ArrayList<>());

            if (!documents.isEmpty()) {
                List<String> jsonDocuments = new ArrayList<>();
                for (Document document : documents) {
                    jsonDocuments.add(document.toJson());
                }
                String jsonResult = "[" + String.join(",", jsonDocuments) + "]";
                result = Either.right(jsonResult);
            } else {
                result = Either.left(new RestaurantError(Constants.NO_RESULTS));
            }
        } catch (Exception e) {
            result = Either.left(new RestaurantError(Constants.ERROR));
        }
        return result;
    }

    @Override
    public Either<RestaurantError, String> exF() {
        Either<RestaurantError, String> result = null;
        try (MongoClient mongo = MongoClients.create("mongodb://informatica.iesquevedo.es:2323")) {
            MongoDatabase db = mongo.getDatabase("davidroldan_restaurant");
            MongoCollection<Document> customers = db.getCollection("customers");
            //f. Get the item most requested

            List<Document> documents = customers.aggregate(
                            Arrays.asList(
                                    unwind("$orders"),
                                    unwind("$orders.order_items"),
                                    group("$orders.order_items.menu_item_id", sum("totalQuantity", "$orders.order_items.quantity")),
                                    sort(descending("totalQuantity")),
                                    limit(1)
                            )

                    )
                    .into(new ArrayList<>());

            if (!documents.isEmpty()) {
                List<String> jsonDocuments = new ArrayList<>();
                for (Document document : documents) {
                    jsonDocuments.add(document.toJson());
                }
                String jsonResult = "[" + String.join(",", jsonDocuments) + "]";
                result = Either.right(jsonResult);
            } else {
                result = Either.left(new RestaurantError(Constants.NO_RESULTS));
            }
        } catch (Exception e) {
            result = Either.left(new RestaurantError(Constants.ERROR));
        }
        return result;
    }

    @Override
    public Either<RestaurantError, String> exG(ObjectId id) {
        Either<RestaurantError, String> result = null;
        try (MongoClient mongo = MongoClients.create("mongodb://informatica.iesquevedo.es:2323")) {
            MongoDatabase db = mongo.getDatabase("davidroldan_restaurant");
            MongoCollection<Document> customers = db.getCollection("customers");
            //g. Show a list with the number of items of each type requested by a selected customer

            List<Document> documents = customers.aggregate(
                            Arrays.asList(
                                    match(eq("_id", id)),
                                    unwind("$orders"),
                                    unwind("$orders.order_items"),
                                    group("$orders.order_items.menu_item_id", sum("totalQuantity", "$orders.order_items.quantity"))
                            )
                    )
                    .into(new ArrayList<>());

            if (!documents.isEmpty()) {
                List<String> jsonDocuments = new ArrayList<>();
                for (Document document : documents) {
                    jsonDocuments.add(document.toJson());
                }
                String jsonResult = "[" + String.join(",", jsonDocuments) + "]";
                result = Either.right(jsonResult);
            } else {
                result = Either.left(new RestaurantError(Constants.NO_RESULTS));
            }
        } catch (Exception e) {
            result = Either.left(new RestaurantError(Constants.ERROR));
        }
        return result;
    }

    @Override
    public Either<RestaurantError, String> exH() {
        Either<RestaurantError, String> result = null;
        try (MongoClient mongo = MongoClients.create("mongodb://informatica.iesquevedo.es:2323")) {
            MongoDatabase db = mongo.getDatabase("davidroldan_restaurant");
            MongoCollection<Document> customers = db.getCollection("customers");
            //h. Get the most requested table

            List<Document> documents = customers.aggregate(
                            Arrays.asList(
                                    unwind("$orders"),
                                    group("$orders.table_id", sum("totalQuantity", 1)),
                                    sort(descending("totalQuantity")),
                                    limit(1)
                            )
                    )
                    .into(new ArrayList<>());

            if (!documents.isEmpty()) {
                List<String> jsonDocuments = new ArrayList<>();
                for (Document document : documents) {
                    jsonDocuments.add(document.toJson());
                }
                String jsonResult = "[" + String.join(",", jsonDocuments) + "]";
                result = Either.right(jsonResult);
            } else {
                result = Either.left(new RestaurantError(Constants.NO_RESULTS));
            }
        } catch (Exception e) {
            result = Either.left(new RestaurantError(Constants.ERROR));
        }
        return result;
    }

    @Override
    public Either<RestaurantError, String> exI() {
        Either<RestaurantError, String> result = null;
        try (MongoClient mongo = MongoClients.create("mongodb://informatica.iesquevedo.es:2323")) {
            MongoDatabase db = mongo.getDatabase("davidroldan_restaurant");
            MongoCollection<Document> customers = db.getCollection("customers");
            //i. Get the most requested table per customer

            List<Document> documents = customers.aggregate(
                            Arrays.asList(
                                    unwind("$orders"),
                                    group("$_id", max("maxTable", "$orders.table_id"))
                            )
                    )
                    .into(new ArrayList<>());

            if (!documents.isEmpty()) {
                List<String> jsonDocuments = new ArrayList<>();
                for (Document document : documents) {
                    jsonDocuments.add(document.toJson());
                }
                String jsonResult = "[" + String.join(",", jsonDocuments) + "]";
                result = Either.right(jsonResult);
            } else {
                result = Either.left(new RestaurantError(Constants.NO_RESULTS));
            }
        } catch (Exception e) {
            result = Either.left(new RestaurantError(Constants.ERROR));
        }
        return result;
    }

    @Override
    public Either<RestaurantError, String> exJ() {
        Either<RestaurantError, String> result = null;
        try (MongoClient mongo = MongoClients.create("mongodb://informatica.iesquevedo.es:2323")) {
            MongoDatabase db = mongo.getDatabase("davidroldan_restaurant");
            MongoCollection<Document> customers = db.getCollection("customers");
            //j. Get the items that have never been requested more than once in an order

            List<Document> documents = customers.aggregate(
                            Arrays.asList(
                                    unwind("$orders"),
                                    unwind("$orders.order_items"),
                                    group(and(eq("order_id","$orders.order_items.menu_item_id"), eq("menu_item_id", "$orders.order_items.menu_item_id")), sum("totalQuantity", "$orders.order_items.quantity")),
                                    group("$_id.menu_item_id", max("maxQ", "$totalQuantity")),
                                    match(lte("maxQ", 1))
                            )
                    ).into(new ArrayList<>());

            if (!documents.isEmpty()) {
                List<String> jsonDocuments = new ArrayList<>();
                for (Document document : documents) {
                    jsonDocuments.add(document.toJson());
                }
                String jsonResult = "[" + String.join(",", jsonDocuments) + "]";
                result = Either.right(jsonResult);
            } else {
                result = Either.left(new RestaurantError(Constants.NO_RESULTS));
            }
        } catch (Exception e) {
            result = Either.left(new RestaurantError(Constants.ERROR));
        }
        return result;
    }

    @Override
    public Either<RestaurantError, String> exK() {
        Either<RestaurantError, String> result = null;
        try (MongoClient mongo = MongoClients.create("mongodb://informatica.iesquevedo.es:2323")) {
            MongoDatabase db = mongo.getDatabase("davidroldan_restaurant");
            MongoCollection<Document> customers = db.getCollection("customers");
            //k. Price paid for each order (Use $lookup)

            List<Document> documents = customers.aggregate(
                            Arrays.asList(
                                    unwind("$orders"),
                                    unwind("$orders.order_items"),
                                    lookup("menu_items", "orders.order_items.menu_item_id", "_id", "menu_items"),
                                    unwind("$menu_items"),
                                    addFields(new Field("totalPrice",
                                            new Document("$multiply", Arrays.asList("$orders.order_items.quantity", "$menu_items.price")))),
                                    group("$orders.order_date", sum("totalPrice", "$totalPrice"))
                            )
                    )
                    .into(new ArrayList<>());

            if (!documents.isEmpty()) {
                List<String> jsonDocuments = new ArrayList<>();
                for (Document document : documents) {
                    jsonDocuments.add(document.toJson());
                }
                String jsonResult = "[" + String.join(",", jsonDocuments) + "]";
                result = Either.right(jsonResult);
            } else {
                result = Either.left(new RestaurantError(Constants.NO_RESULTS));
            }
        } catch (Exception e) {
            result = Either.left(new RestaurantError(Constants.ERROR));
        }
        return result;
    }

    @Override
    public Either<RestaurantError, String> exL() {
        Either<RestaurantError, String> result = null;
        try (MongoClient mongo = MongoClients.create("mongodb://informatica.iesquevedo.es:2323")) {
            MongoDatabase db = mongo.getDatabase("davidroldan_restaurant");
            MongoCollection<Document> customers = db.getCollection("customers");
            //l. Get the name of the customer that has spent more money in the restaurant

            List<Document> documents = customers.aggregate(
                            Arrays.asList(
                                    unwind("$orders"),
                                    unwind("$orders.order_items"),
                                    lookup("menu_items", "orders.order_items.menu_item_id", "_id", "menu_items"),
                                    unwind("$menu_items"),
                                    addFields(new Field("totalPrice",
                                            new Document("$multiply", Arrays.asList("$orders.order_items.quantity", "$menu_items.price")))),
                                    group("$_id", sum("totalPrice", "$totalPrice")),
                                    sort(descending("totalPrice")),
                                    limit(1)
                            )

                    )
                    .into(new ArrayList<>());

            if (!documents.isEmpty()) {
                List<String> jsonDocuments = new ArrayList<>();
                for (Document document : documents) {
                    jsonDocuments.add(document.toJson());
                }
                String jsonResult = "[" + String.join(",", jsonDocuments) + "]";
                result = Either.right(jsonResult);
            } else {
                result = Either.left(new RestaurantError(Constants.NO_RESULTS));
            }
        } catch (Exception e) {
            result = Either.left(new RestaurantError(Constants.ERROR));
        }
        return result;
    }

    @Override
    public Either<RestaurantError, String> exM() {
        Either<RestaurantError, String> result = null;
        try (MongoClient mongo = MongoClients.create("mongodb://informatica.iesquevedo.es:2323")) {
            MongoDatabase db = mongo.getDatabase("davidroldan_restaurant");
            MongoCollection<Document> customers = db.getCollection("customers");
            //m. Total paid amount

            List<Document> documents = customers.aggregate(
                            Arrays.asList(
                                    unwind("$orders"),
                                    unwind("$orders.order_items"),
                                    lookup("menu_items", "orders.order_items.menu_item_id", "_id", "menu_items"),
                                    unwind("$menu_items"),
                                    addFields(new Field("totalPrice",
                                            new Document("$multiply", Arrays.asList("$orders.order_items.quantity", "$menu_items.price")))),
                                    group(null, sum("totalPrice", "$totalPrice"))
                            )

                    )
                    .into(new ArrayList<>());

            if (!documents.isEmpty()) {
                List<String> jsonDocuments = new ArrayList<>();
                for (Document document : documents) {
                    jsonDocuments.add(document.toJson());
                }
                String jsonResult = "[" + String.join(",", jsonDocuments) + "]";
                result = Either.right(jsonResult);
            } else {
                result = Either.left(new RestaurantError(Constants.NO_RESULTS));
            }
        } catch (Exception e) {
            result = Either.left(new RestaurantError(Constants.ERROR));
        }
        return result;
    }

    @Override
    public Either<RestaurantError, String> ex2A() {
        Either<RestaurantError, String> result = null;
        try (MongoClient mongo = MongoClients.create("mongodb://informatica.iesquevedo.es:2323")) {
            MongoDatabase db = mongo.getDatabase("davidroldan_rickymorty");
            MongoCollection<Document> characters = db.getCollection("characters");

            //a. Name and number of episodes of character who appear more than 10 times and less than 30.
            List<Document> documents = characters.aggregate(
                            Arrays.asList(
                                    unwind("$episode"),
                                    group("$name", sum("totalEpisodes", 1)),
                                    match(and(gt("totalEpisodes", 10), lt("totalEpisodes", 30)))
                            )
                    )
                    .into(new ArrayList<>());

            if (!documents.isEmpty()) {
                List<String> jsonDocuments = new ArrayList<>();
                for (Document document : documents) {
                    jsonDocuments.add(document.toJson());
                }
                String jsonResult = "[" + String.join(",", jsonDocuments) + "]";
                result = Either.right(jsonResult);
            } else {
                result = Either.left(new RestaurantError(Constants.NO_RESULTS));
            }
        } catch (Exception e) {
            result = Either.left(new RestaurantError(Constants.ERROR));
        }
        return result;
    }

    @Override
    public Either<RestaurantError, String> ex2B() {
        Either<RestaurantError, String> result = null;
        try (MongoClient mongo = MongoClients.create("mongodb://informatica.iesquevedo.es:2323")) {
            MongoDatabase db = mongo.getDatabase("davidroldan_rickymorty");
            MongoCollection<Document> characters = db.getCollection("characters");

            //b. Episode with more Ricks
            List<Document> documents = characters.aggregate(
                            Arrays.asList(
                                    unwind("$episode"),
                                    match(regex("name", "^Rick")),
                                    group("$episode", sum("totalRicks", 1)),
                                    sort(descending("totalRicks")),
                                    limit(1)
                            )
                    )
                    .into(new ArrayList<>());

            if (!documents.isEmpty()) {
                List<String> jsonDocuments = new ArrayList<>();
                for (Document document : documents) {
                    jsonDocuments.add(document.toJson());
                }
                String jsonResult = "[" + String.join(",", jsonDocuments) + "]";
                result = Either.right(jsonResult);
            } else {
                result = Either.left(new RestaurantError(Constants.NO_RESULTS));
            }
        } catch (Exception e) {
            result = Either.left(new RestaurantError(Constants.ERROR));
        }
        return result;
    }

    @Override
    public Either<RestaurantError, String> ex2C() {
        Either<RestaurantError, String> result = null;
        try (MongoClient mongo = MongoClients.create("mongodb://informatica.iesquevedo.es:2323")) {
            MongoDatabase db = mongo.getDatabase("davidroldan_rickymorty");
            MongoCollection<Document> characters = db.getCollection("characters");

            //c. Find the most common origin of the characters, discarding the unknown ones.
            List<Document> documents = characters.aggregate(
                            Arrays.asList(
                                    match(ne("origin.name", "unknown")),
                                    group("$origin.name", sum("count", 1)),
                                    sort(descending("count")),
                                    limit(1)

                            )
                    )
                    .into(new ArrayList<>());

            if (!documents.isEmpty()) {
                List<String> jsonDocuments = new ArrayList<>();
                for (Document document : documents) {
                    jsonDocuments.add(document.toJson());
                }
                String jsonResult = "[" + String.join(",", jsonDocuments) + "]";
                result = Either.right(jsonResult);
            } else {
                result = Either.left(new RestaurantError(Constants.NO_RESULTS));
            }
        } catch (Exception e) {
            result = Either.left(new RestaurantError(Constants.ERROR));
        }
        return result;
    }

    @Override
    public Either<RestaurantError, String> ex2D() {
        Either<RestaurantError, String> result = null;
        try (MongoClient mongo = MongoClients.create("mongodb://informatica.iesquevedo.es:2323")) {
            MongoDatabase db = mongo.getDatabase("davidroldan_rickymorty");
            MongoCollection<Document> characters = db.getCollection("characters");
            //d. Analysis by gender of the living and dead characters in each episode.

            List<Document> documents = characters.aggregate(
                            Arrays.asList(
                                    unwind("$episode"),
                                    group(new Document("episode", "$episode").append("gender", "$gender").append("status", "$status"), sum("count", 1)),
                                    sort(descending("count")),
                                    addFields(new Field("episode", "$_id.episode"), new Field("gender", "$_id.gender"), new Field("status", "$_id.status")),
                                    project(fields(include("episode","gender","status","count"), excludeId()))
                            )
                    )
                    .into(new ArrayList<>());

            if (!documents.isEmpty()) {
                List<String> jsonDocuments = new ArrayList<>();
                for (Document document : documents) {
                    jsonDocuments.add(document.toJson());
                }
                String jsonResult = "[" + String.join(",", jsonDocuments) + "]";
                result = Either.right(jsonResult);
            } else {
                result = Either.left(new RestaurantError(Constants.NO_RESULTS));
            }
        } catch (Exception e) {
            result = Either.left(new RestaurantError(Constants.ERROR));
        }
        return result;
    }

    @Override
    public Either<RestaurantError, String> ex2E() {
        Either<RestaurantError, String> result = null;
        try (MongoClient mongo = MongoClients.create("mongodb://informatica.iesquevedo.es:2323")) {
            MongoDatabase db = mongo.getDatabase("davidroldan_rickymorty");
            MongoCollection<Document> characters = db.getCollection("characters");
            //Average number of episodes in which a dead character appears.

            List<Document> documents = characters.aggregate(
                            Arrays.asList(
                                    addFields(new Field("numEpisodes", new Document("$size", Arrays.asList("$episode")))),
                                    project(fields(include("name", "episode", "status", "numEpisodes"), excludeId())),
                                    match(eq("status", "Dead")),
                                    group(null, avg("averageEpisodes", "$numEpisodes"))
                            )
                    )
                    .into(new ArrayList<>());

            if (!documents.isEmpty()) {
                List<String> jsonDocuments = new ArrayList<>();
                for (Document document : documents) {
                    jsonDocuments.add(document.toJson());
                }
                String jsonResult = "[" + String.join(",", jsonDocuments) + "]";
                result = Either.right(jsonResult);
            } else {
                result = Either.left(new RestaurantError(Constants.NO_RESULTS));
            }
        } catch (Exception e) {
            result = Either.left(new RestaurantError(Constants.ERROR));
        }
        return result;
    }
}