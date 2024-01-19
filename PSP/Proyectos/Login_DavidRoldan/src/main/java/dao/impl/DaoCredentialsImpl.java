package dao.impl;

import common.Constants;
import common.SQLQueries;
import dao.DaoCredentials;
import domain.modelo.Credentials;
import domain.modelo.errores.*;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.time.Duration;
import java.time.LocalDateTime;

@Log4j2
public class DaoCredentialsImpl implements DaoCredentials {
    private final DBConnectionPool db;

    @Inject
    public DaoCredentialsImpl(DBConnectionPool db) {
        this.db = db;
    }

    @Override
    public Credentials get(String user) {
        Credentials c = new Credentials();
        try(Connection con = db.getConnection(); PreparedStatement preparedStatement = con.prepareStatement(SQLQueries.SELECT_FROM_CREDENTIALS_WHERE_USERNAME)) {
            preparedStatement.setString(1, user);

            ResultSet rs = preparedStatement.executeQuery();
            int activado = 0;
            if (rs.next()) {
                c.setUsername(rs.getString(Constants.USERNAME));
                c.setPassword(rs.getString(Constants.PASSWORD));
                c.setEmail(rs.getString(Constants.EMAIL));
                c.setRole(rs.getString(Constants.ROLE));
                c.setTemporalPassword(rs.getString(Constants.TEMPPASSWORD));
                c.setTemporalCode(rs.getString(Constants.TEMPORAL));
                activado = rs.getInt(Constants.VALIDATE);
            }
            if (c.getUsername()==null) {
                throw new ValidationException(Constants.USER_OR_PASSWORD_INCORRECT);
            }
            if (activado == 0) {
                log.error(Constants.USER_NOT_ACTIVATED);
                throw new ErrorConnectingDatabaseException(Constants.USER_NOT_ACTIVATED);
            }
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
            throw new ErrorConnectingDatabaseException(ex.getMessage());
        }
        return c;
    }

    @Override
    public Credentials getByAccess(String accesstoken) {
        Credentials c = new Credentials();
        try(Connection con = db.getConnection(); PreparedStatement preparedStatement = con.prepareStatement(SQLQueries.SELECT_FROM_CREDENTIALS_WHERE_ACCESSTOKEN)) {
            preparedStatement.setString(1, accesstoken.strip());

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                c.setUsername(rs.getString(Constants.USERNAME));
                c.setEmail(rs.getString(Constants.EMAIL));
                c.setRole(rs.getString(Constants.ROLE));
            }
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
            throw new ErrorConnectingDatabaseException(ex.getMessage());
        }
        return c;
    }

    @Override
    public int save(Credentials c) {
        int result = 0;
        try(Connection con = db.getConnection(); PreparedStatement preparedStatement = con.prepareStatement(SQLQueries.INSERT_INTO_CREDENTIALS)) {
            preparedStatement.setString(1, c.getUsername());
            preparedStatement.setString(2, c.getPassword());
            preparedStatement.setString(3, c.getEmail());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            preparedStatement.setString(5, c.getTemporalCode());

            int rs = preparedStatement.executeUpdate();
            if (rs != 0) {
                result = 1;
            }
        } catch (SQLException ex) {
            //user already exists
            if (ex.getErrorCode() == 1062) {
                result = 2;
                throw new AlreadyExistsException(Constants.USERNAME+" "+Constants.ALREADY_EXISTS);
            } else {
                log.error(ex.getMessage(), ex);
                throw new ErrorConnectingDatabaseException(ex.getMessage());
            }
        }
        return result;
    }

    @Override
    public boolean setRefreshToken(Credentials c) {
        boolean result = false;
        try (Connection con = db.getConnection(); PreparedStatement preparedStatement = con.prepareStatement(SQLQueries.UPDATE_ACCESSTOKEN_REFRESHTOKEN_WHERE_USERNAME)) {
            preparedStatement.setString(1, c.getAccessToken());
            preparedStatement.setString(2, c.getRefreshToken());
            preparedStatement.setString(3, c.getUsername());

            int rs = preparedStatement.executeUpdate();
            if (rs != 0) {
                result = true;
            }
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
            throw new ErrorConnectingDatabaseException(ex.getMessage());
        }
        return result;
    }

    @Override
    public boolean setTwoFactorCode(Credentials c) {
        boolean result = false;
        try(Connection con = db.getConnection(); PreparedStatement preparedStatement = con.prepareStatement(SQLQueries.UPDATE_TEMPORAL_WHERE_USERNAME)) {
            preparedStatement.setString(1, c.getTemporalCode());
            preparedStatement.setString(2, c.getUsername());

            int rs = preparedStatement.executeUpdate();
            if (rs != 0) {
                result = true;
            }
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
            throw new ErrorConnectingDatabaseException(ex.getMessage());
        }
        return result;
    }

    @Override
    public Credentials refreshToken(Credentials credentials) {
        Credentials result = null;
        try(Connection con = db.getConnection(); PreparedStatement preparedStatement = con.prepareStatement(SQLQueries.UPDATE_ACCESSTOKEN_WHERE_REFRESHTOKEN)) {
            preparedStatement.setString(1, credentials.getAccessToken());
            preparedStatement.setString(2, credentials.getRefreshToken());

            int rs = preparedStatement.executeUpdate();
            if (rs != 0) {
                result = credentials;
            }
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
            throw new ErrorConnectingDatabaseException(ex.getMessage());
        }
        return result;
    }

    @Override
    public boolean forgotPassword(Credentials c) {
        boolean result = false;
        try(Connection con = db.getConnection(); PreparedStatement preparedStatement = con.prepareStatement(SQLQueries.UPDATE_CREDENTIALS_SET_TEMPORAL_WHERE_USERNAME)) {
            preparedStatement.setString(1, c.getTemporalCode());
            preparedStatement.setString(2, c.getUsername());

            int rs = preparedStatement.executeUpdate();
            if (rs != 0) {
                result = true;
            }
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
            throw new ErrorConnectingDatabaseException(ex.getMessage());
        }
        return result;
    }

    @Override
    public boolean changePassword(Credentials c) {
        boolean result = false;
        try(Connection con = db.getConnection(); PreparedStatement preparedStatement = con.prepareStatement(SQLQueries.UPDATE_CREDENTIALS_SET_PASSWORD_WHERE_USERNAME)) {
            preparedStatement.setString(1, c.getPassword());
            preparedStatement.setString(2, c.getUsername());

            int rs = preparedStatement.executeUpdate();
            if (rs != 0) {
                result = true;
            }
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
            throw new ErrorConnectingDatabaseException(ex.getMessage());
        }
        return result;
    }

    @Override
    public Either<ApiError, Integer> activarUsuario(String codigo, String nuevocodigo) {
        Either<ApiError, Integer> result;
        try (Connection con = db.getConnection()) {
            try (PreparedStatement preparedStatement = con.prepareStatement(SQLQueries.SELECT_FROM_CREDENTIALS_WHERE_ACTIVATION_CODE)) {
                preparedStatement.setString(1, codigo);

                ResultSet rs = preparedStatement.executeQuery();
                if (rs.next()) {
                    // Aquí puedes obtener los datos del usuario si es necesario
                    String email = rs.getString(Constants.EMAIL);
                    int validate = rs.getInt(Constants.VALIDATE);
                    LocalDateTime fecha = rs.getTimestamp(Constants.FECHA).toLocalDateTime();
                    // Puedes seguir obteniendo otros datos según sea necesario

                    // Luego, puedes realizar la actualización del campo "validate"
                    if (validate == 1) {
                        return Either.left(new ApiError(Constants.USUARIO_YA_ACTIVADO, LocalDateTime.now()));
                    }
                    if (Duration.between(fecha, LocalDateTime.now()).toMinutes() > 5) {
                        try (PreparedStatement updateStatement = con.prepareStatement(SQLQueries.UPDATE_VALIDATION_CODE_DATE_WHERE_CODE)) {
                            updateStatement.setString(1, nuevocodigo);
                            updateStatement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
                            updateStatement.setString(3, codigo);

                            int updateResult = updateStatement.executeUpdate();
                            if (updateResult == 0) {
                                return Either.left(new ApiError(Constants.ERROR_ACTIVANDO_USUARIO, LocalDateTime.now()));
                            } else {
                                return Either.left(new ApiError(Constants.EL_CODIGO_DE_EXPIRACION_HA_CADUCADO_HAS_RECIBIDO_UN_NUEVO_CORREO_EN + email, LocalDateTime.now()));
                            }
                        }
                    }
                    try (PreparedStatement updateStatement = con.prepareStatement(SQLQueries.UPDATE_CREDENTIALS_SET_VALIDATE_WHERE_CODE)) {
                        updateStatement.setString(1, codigo);

                        int updateResult = updateStatement.executeUpdate();
                        if (updateResult == 0) {
                            return Either.left(new ApiError(Constants.ERROR_ACTIVANDO_USUARIO, LocalDateTime.now()));
                        } else {
                            result = Either.right(1);
                        }
                    }
                } else {
                    // El código de activación no coincide con ningún usuario
                    return Either.left(new ApiError(Constants.CODIGO_DE_ACTIVACION_INVALIDO, LocalDateTime.now()));
                }
            }
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
            throw new ErrorConnectingDatabaseException(ex.getMessage());
        }
        return result;
    }
}
