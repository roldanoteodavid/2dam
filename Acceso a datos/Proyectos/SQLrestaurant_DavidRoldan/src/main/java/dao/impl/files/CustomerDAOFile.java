package dao.impl.files;

import common.Constants;
import config.Configuration;
import dao.CustomersDAO;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import model.Customer;
import model.errors.CustomerError;
import model.errors.CustomerErrorEmptyList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class CustomerDAOFile implements CustomersDAO {
    private final Configuration config;

    @Named("CustomerDAOFile")
    @Inject
    public CustomerDAOFile(Configuration config) {
        this.config = config;
    }

    @Override
    public Either<CustomerError, List<Customer>> getAll() {
        Path file = Paths.get(Configuration.getInstance().getProperty("pathCustomers"));
        Either<CustomerError, List<Customer>> result;
        List<Customer> customerList = new ArrayList<>();
        try {
            Stream<String> lines = Files.lines(file);
            {
                lines.forEach(line -> {
                    Customer customer = new Customer(line);
                    customerList.add(customer);
                });
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (customerList.isEmpty()) result = Either.left(new CustomerErrorEmptyList());
        else result = Either.right(customerList);
        return result;
    }

    @Override
    public Either<CustomerError, Customer> get(int id) {
        List<Customer> customerList = getAll().getOrElse(new ArrayList<>());
        Customer foundCustomer = customerList.stream()
                .filter(customer -> customer.getId() == id)
                .findFirst()
                .orElse(null);

        if (foundCustomer != null) {
            return Either.right(foundCustomer);
        } else {
            return Either.left(new CustomerError(Constants.CUSTOMER_NOT_FOUND));
        }
    }

    @Override
    public Either<CustomerError, Integer> save(Customer c) {
        if (get(c.getId()) == null) {
            String fileData =c.toFileString();
            Path file = Paths.get(Configuration.getInstance().getProperty("pathCustomers"));
            try {
                Files.write(file, fileData.getBytes(), StandardOpenOption.APPEND);
                return Either.right(0);
            } catch (IOException e) {
                return Either.left(new CustomerError(Constants.ERROR_SAVING_CUSTOMER));
            }
        } else {
            return Either.left(new CustomerError(Constants.CUSTOMER_ID_ALREADY_EXISTS));
        }
    }

    @Override
    public Either<CustomerError, Integer> update(Customer c) {
        return Either.right(0);
    }

    @Override
    public Either<CustomerError, Integer> delete(Customer c) {
        if (get(c.getId()).isRight()) {
            Path file = Paths.get(Configuration.getInstance().getProperty("pathCustomers"));
            try {
                List<String> lines = Files.readAllLines(file);
                lines.remove(c.toFileString());
                Files.write(file, lines);
                return Either.right(0);
            } catch (IOException e) {
                return Either.left(new CustomerError(Constants.ERROR_DELETING_CUSTOMER));
            }
        } else {
            return Either.left(new CustomerError(Constants.CUSTOMER_NOT_FOUND));
        }
    }

}
