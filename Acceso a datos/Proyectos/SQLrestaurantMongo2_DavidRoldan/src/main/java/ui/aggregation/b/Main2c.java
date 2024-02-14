package ui.aggregation.b;

import io.vavr.control.Either;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import model.errors.RestaurantError;
import services.AggregationService;

public class Main2c {
    public static void main(String[] args) {
        SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        final SeContainer container = initializer.initialize();
        AggregationService aggregationService = container.select(AggregationService.class).get();
        Either<RestaurantError, String> either = aggregationService.ex2C();

        System.out.println("c. Find the most common origin of the characters, discarding the unknown ones. ");
        if (either.isRight()) {
            System.out.println(either.get());
        } else if (either.isLeft()) {
            System.out.println(either.getLeft());
        }
    }
}
