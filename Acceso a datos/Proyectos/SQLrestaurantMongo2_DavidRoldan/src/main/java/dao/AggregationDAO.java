package dao;

import io.vavr.control.Either;
import model.errors.RestaurantError;
import org.bson.types.ObjectId;

public interface AggregationDAO {
    Either<RestaurantError, String> exA();
    Either<RestaurantError, String> exB(ObjectId id);
    Either<RestaurantError, String> exC();
    Either<RestaurantError, String> exD();
    Either<RestaurantError, String> exE();
    Either<RestaurantError, String> exF();
    Either<RestaurantError, String> exG(ObjectId id);
    Either<RestaurantError, String> exH();
    Either<RestaurantError, String> exI();
    Either<RestaurantError, String> exJ();
    Either<RestaurantError, String> exK();
    Either<RestaurantError, String> exL();
    Either<RestaurantError, String> exM();
    Either<RestaurantError, String> ex2A();
    Either<RestaurantError, String> ex2B();
    Either<RestaurantError, String> ex2C();
    Either<RestaurantError, String> ex2D();
    Either<RestaurantError, String> ex2E();
}
