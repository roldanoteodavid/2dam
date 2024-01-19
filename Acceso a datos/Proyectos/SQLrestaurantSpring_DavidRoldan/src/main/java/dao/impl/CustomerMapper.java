package dao.impl;

import common.Constants;
import model.Customer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class CustomerMapper implements RowMapper<Customer> {
    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        int id = rs.getInt(Constants.ID);
        String firstName = rs.getString(Constants.FIRST_NAME);
        String lastName = rs.getString(Constants.LAST_NAME);
        String email = rs.getString(Constants.EMAIL);
        String phone = rs.getString(Constants.PHONE);
        LocalDate birthDate = rs.getDate(Constants.DATE_OF_BIRTH).toLocalDate();
        return new Customer(id, firstName, lastName, email, phone, birthDate);
    }
}
