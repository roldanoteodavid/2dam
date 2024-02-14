package ui.aggregation.a;

import io.vavr.control.Either;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import model.errors.RestaurantError;
import org.bson.types.ObjectId;
import services.AggregationService;

public class Main1b {
    public static void main(String[] args) {
        SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        final SeContainer container = initializer.initialize();
        AggregationService aggregationService = container.select(AggregationService.class).get();
        Either<RestaurantError, String> either = aggregationService.exB(new ObjectId("65bcca032f6b2c56727c15bb"));
//        Either<RestaurantError, String> either = aggregationService.exB(new ObjectId("65bcd90c58dbc46f0d835009"));

        System.out.println("b. Get the orders of a given customer, showing the name of the customer and the number of seats");
        if (either.isRight()) {
            System.out.println(either.get());
        } else if (either.isLeft()) {
            System.out.println(either.getLeft());
        }
    }
}
