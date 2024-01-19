package dao.impl;

import common.Constants;
import common.SQLQueries;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Credential;
import model.errors.RestaurantError;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoLoginImpl implements dao.LoginDAO {

    private DBConnectionPool db;

    @Inject
    public DaoLoginImpl(DBConnectionPool db) {
        this.db = db;
    }

    @Override
    public Either<RestaurantError, Credential> get(Credential credential) {
        Either<RestaurantError, Credential> result = null;
        try (Connection con = db.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(SQLQueries.SELECT_FROM_CREDENTIALS_WHERE_USER_NAME_AND_PASSWORD)) {
            preparedStatement.setString(1, credential.getNombre());
            preparedStatement.setString(2, credential.getPassword());

            ResultSet rs = preparedStatement.executeQuery();
            result = Either.right(readRS(rs));
        } catch (SQLException ex) {
            result = Either.left(new RestaurantError(Constants.ERROR_CONNECTING_DATABASE));
        }
        return result;
    }

    private Credential readRS(ResultSet rs) throws SQLException {
        Credential credential = null;
        if (rs.next()) {
            credential = new Credential(rs.getInt(Constants.ID), rs.getString(Constants.USER_NAME), rs.getString(Constants.PASSWORD));
        }
        return credential;
    }
}
