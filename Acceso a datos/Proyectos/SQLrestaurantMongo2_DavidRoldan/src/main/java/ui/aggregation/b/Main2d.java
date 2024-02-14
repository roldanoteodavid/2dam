package ui.aggregation.b;

import io.vavr.control.Either;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import model.errors.RestaurantError;
import services.AggregationService;

public class Main2d {
    public static void main(String[] args) {
        SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        final SeContainer container = initializer.initialize();
        AggregationService aggregationService = container.select(AggregationService.class).get();
        Either<RestaurantError, String> either = aggregationService.ex2D();

        System.out.println("d. Analysis by gender of the living and dead characters in each episode.");
        if (either.isRight()) {
            System.out.println(either.get());
        } else if (either.isLeft()) {
            System.out.println(either.getLeft().getMessage());
        }
    }
}
