package dao.impl;

import common.Constants;
import common.SQLQueries;
import dao.CustomersDAO;
import domain.modelo.Customer;
import domain.modelo.errores.*;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class CustomerDaoImpl implements CustomersDAO {

    private final DBConnectionPool db;

    @Inject
    public CustomerDaoImpl(DBConnectionPool db) {
        this.db = db;
    }

    @Override
    public Either<ApiError, List<Customer>> getAll() {
        Either<ApiError, List<Customer>> result = null;
        try (Connection con = db.getConnection(); Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            ResultSet rs = stmt.executeQuery(SQLQueries.SELECT_FROM_CUSTOMERS);
            List<Customer> customers = readRS(rs);

            if (customers.isEmpty()) {
                log.error(Constants.THE_CUSTOMER_LIST_IS_EMPTY);
                throw new EmptyListException(Constants.CUSTOMER_NOT_FOUND);
            } else {
                result = Either.right(customers);
            }
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
            throw new ErrorConnectingDatabaseException(ex.getMessage());
        }
        return result;
    }

    @Override
    public Either<ApiError, Customer> get(int id) {
        Either<ApiError, Customer> result = null;
        try (Connection con = db.getConnection(); PreparedStatement preparedStatement = con.prepareStatement(SQLQueries.SELECT_FROM_CUSTOMERS_WHERE_ID)) {
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            List<Customer> customers = readRS(rs);

            if (customers.isEmpty()) {
                log.error(Constants.CUSTOMER + " " + id + " " + Constants.NOT_FOUND);
                throw new NotFoundException(Constants.CUSTOMER_NOT_FOUND);
            } else {
                result = Either.right(customers.get(0));
            }

        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
            throw new ErrorConnectingDatabaseException(ex.getMessage());
        }
        return result;
    }

    @Override
    public Either<ApiError, Integer> save(Customer c) {
        Either<ApiError, Integer> result;
        try (Connection con = db.getConnection(); PreparedStatement preparedStatement = con.prepareStatement(SQLQueries.INSERT_INTO_CUSTOMERS_ID_FIRST_NAME_LAST_NAME_EMAIL_PHONE_DATE_OF_BIRTH_VALUES)) {
            preparedStatement.setInt(1, c.getId());
            preparedStatement.setString(2, c.getFirstname());
            preparedStatement.setString(3, c.getLastname());
            preparedStatement.setString(4, c.getEmail());
            preparedStatement.setString(5, c.getPhone());
            preparedStatement.setDate(6, Date.valueOf(c.getDob()));

            int rs = preparedStatement.executeUpdate();

            if (rs == 0) {
                log.error(Constants.ERROR_ADDING_CUSTOMER + c);
                throw new NotFoundException(Constants.ERROR_ADDING_CUSTOMER + c);
            } else {
                result = Either.right(0);
            }
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1062) {
                log.error(Constants.CUSTOMER + " " + c.getId() + " " + Constants.ALREADY_EXISTS + " " + ex.getMessage());
                throw new AlreadyExistsException(Constants.CUSTOMER + " " + c.getId() + " " + Constants.ALREADY_EXISTS);
            } else {
                log.error(ex.getMessage(), ex);
                throw new ErrorConnectingDatabaseException(ex.getMessage());
            }
        }
        return result;
    }

    @Override
    public Either<ApiError, Integer> update(Customer c) {
        Either<ApiError, Integer> result;
        try (Connection con = db.getConnection(); PreparedStatement preparedStatement = con.prepareStatement(SQLQueries.UPDATE_CUSTOMERS_SET_FIRST_NAME_LAST_NAME_EMAIL_PHONE_DATE_OF_BIRTH_WHERE_ID)) {
            preparedStatement.setString(1, c.getFirstname());
            preparedStatement.setString(2, c.getLastname());
            preparedStatement.setString(3, c.getEmail());
            preparedStatement.setString(4, c.getPhone());
            preparedStatement.setDate(5, Date.valueOf(c.getDob()));
            preparedStatement.setInt(6, c.getId());

            int rs = preparedStatement.executeUpdate();

            if (rs == 0) {
                log.error(Constants.ERROR_UPDATING_CUSTOMER + c);
                throw new NotFoundException(Constants.ERROR_UPDATING_CUSTOMER + c);
            } else {
                result = Either.right(0);
            }
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
            throw new ErrorConnectingDatabaseException(ex.getMessage());
        }
        return result;
    }

    @Override
    public Either<ApiError, Integer> delete(Customer c) {
        Either<ApiError, Integer> result;
        try (Connection con = db.getConnection(); PreparedStatement preparedStatement = con.prepareStatement(SQLQueries.DELETE_FROM_CUSTOMERS_WHERE_ID)) {
            preparedStatement.setInt(1, c.getId());

            int rs = preparedStatement.executeUpdate();

            if (rs == 0) {
                log.error(Constants.ERROR_DELETING_CUSTOMER + c);
                throw new NotFoundException(Constants.ERROR_DELETING_CUSTOMER + c);
            } else {
                result = Either.right(0);
            }
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1451) {
                log.error(ex.getMessage(), ex);
                throw new HasOrdersException(Constants.THE_CUSTOMER_HAS_ORDERS);
            } else {
                log.error(ex.getMessage(), ex);
                throw new ErrorConnectingDatabaseException(ex.getMessage());
            }
        }
        return result;
    }

    private List<Customer> readRS(ResultSet rs) {
        List<Customer> customers = new ArrayList<>();
        try {
            while (rs.next()) {
                int id = rs.getInt(Constants.ID);
                String firstName = rs.getString(Constants.FIRST_NAME);
                String lastName = rs.getString(Constants.LAST_NAME);
                String email = rs.getString(Constants.EMAIL);
                String phone = rs.getString(Constants.PHONE);
                LocalDate birthDate = rs.getDate(Constants.DATE_OF_BIRTH).toLocalDate();
                customers.add(new Customer(id, firstName, lastName, email, phone, birthDate));
            }
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
        }
        return customers;
    }
}
