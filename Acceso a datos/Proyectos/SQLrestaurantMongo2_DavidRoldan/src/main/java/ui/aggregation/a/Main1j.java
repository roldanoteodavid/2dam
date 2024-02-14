package ui.aggregation.a;

import io.vavr.control.Either;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import model.errors.RestaurantError;
import services.AggregationService;

public class Main1j {
    public static void main(String[] args) {
        SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        final SeContainer container = initializer.initialize();
        AggregationService aggregationService = container.select(AggregationService.class).get();
        Either<RestaurantError, String> either = aggregationService.exJ();

        System.out.println("j. Get the items that have never been requested more than once in an order");
        if (either.isRight()) {
            System.out.println(either.get());
        } else if (either.isLeft()) {
            System.out.println(either.getLeft().getMessage());
        }
    }
}
