package services.impl;

import dao.AggregationDAO;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.errors.RestaurantError;
import org.bson.types.ObjectId;
import services.AggregationService;

public class AggregationServiceImpl implements AggregationService {
    private final AggregationDAO dao;

    @Inject
    public AggregationServiceImpl(AggregationDAO aggregationDAO) {
        this.dao = aggregationDAO;
    }

    @Override
    public Either<RestaurantError, String> exA() {
        return dao.exA();
    }

    @Override
    public Either<RestaurantError, String> exB(ObjectId id) {
        return dao.exB(id);
    }

    @Override
    public Either<RestaurantError, String> exC() {
        return dao.exC();
    }

    @Override
    public Either<RestaurantError, String> exD() {
        return dao.exD();
    }

    @Override
    public Either<RestaurantError, String> exE() {
        return dao.exE();
    }

    @Override
    public Either<RestaurantError, String> exF() {
        return dao.exF();
    }

    @Override
    public Either<RestaurantError, String> exG(ObjectId id) {
        return dao.exG(id);
    }

    @Override
    public Either<RestaurantError, String> exH() {
        return dao.exH();
    }

    @Override
    public Either<RestaurantError, String> exI() {
        return dao.exI();
    }

    @Override
    public Either<RestaurantError, String> exJ() {
        return dao.exJ();
    }

    @Override
    public Either<RestaurantError, String> exK() {
        return dao.exK();
    }

    @Override
    public Either<RestaurantError, String> exL() {
        return dao.exL();
    }

    @Override
    public Either<RestaurantError, String> exM() {
        return dao.exM();
    }

    @Override
    public Either<RestaurantError, String> ex2A() {
        return dao.ex2A();
    }

    @Override
    public Either<RestaurantError, String> ex2B() {
        return dao.ex2B();
    }

    @Override
    public Either<RestaurantError, String> ex2C() {
        return dao.ex2C();
    }

    @Override
    public Either<RestaurantError, String> ex2D() {
        return dao.ex2D();
    }

    @Override
    public Either<RestaurantError, String> ex2E() {
        return dao.ex2E();
    }
}
