package net.javaguides.usermanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.javaguides.usermanagement.model.User;

public class UserDAO {

    // =========================================================
    // SQL QUERIES
    // =========================================================

    // PostgreSQL table name is userss
    private static final String INSERT_USERS_SQL =
            "INSERT INTO userss (name, email, country) VALUES (?, ?, ?)";

    private static final String SELECT_USER_BY_ID =
            "SELECT id, name, email, country FROM userss WHERE id = ?";

    private static final String SELECT_ALL_USERS =
            "SELECT id, name, email, country FROM userss";

    private static final String DELETE_USERS_SQL =
            "DELETE FROM userss WHERE id = ?";

    private static final String UPDATE_USERS_SQL =
            "UPDATE userss SET name = ?, email = ?, country = ? WHERE id = ?";


    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public UserDAO() {
    }


    // =========================================================
    // DATABASE CONNECTION
    // =========================================================

    protected Connection getConnection() throws SQLException {

        try {

            // Load PostgreSQL JDBC Driver
            Class.forName("org.postgresql.Driver");

            // Get Railway PostgreSQL environment variables
            String url = System.getenv("DATABASE_URL");
            String username = System.getenv("PGUSER");
            String password = System.getenv("PGPASSWORD");

            // Check if DATABASE_URL exists
            if (url == null || url.trim().isEmpty()) {
                throw new SQLException(
                        "DATABASE_URL environment variable is missing"
                );
            }

            // Railway may provide:
            // postgresql://...
            //
            // JDBC driver requires:
            // jdbc:postgresql://...
            if (url.startsWith("postgresql://")) {
                url = "jdbc:" + url;
            }

            System.out.println("DATABASE URL: " + url);
            System.out.println("DATABASE USER: " + username);

            // Create database connection
            Connection connection = DriverManager.getConnection(
                    url,
                    username,
                    password
            );

            System.out.println("DATABASE CONNECTED SUCCESSFULLY!");

            return connection;

        } catch (ClassNotFoundException e) {

            throw new SQLException(
                    "PostgreSQL JDBC Driver not found",
                    e
            );
        }
    }


    // =========================================================
    // INSERT USER
    // =========================================================

    public void insertUser(User user) throws SQLException {

        try (
            Connection connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(INSERT_USERS_SQL)
        ) {

            System.out.println(
                    "DATABASE CONNECTION: " + connection
            );

            preparedStatement.setString(
                    1,
                    user.getName()
            );

            preparedStatement.setString(
                    2,
                    user.getEmail()
            );

            preparedStatement.setString(
                    3,
                    user.getCountry()
            );

            int result = preparedStatement.executeUpdate();

            System.out.println(
                    "ROWS INSERTED: " + result
            );

        } catch (SQLException e) {

            printSQLException(e);

            throw e;
        }
    }


    // =========================================================
    // SELECT USER BY ID
    // =========================================================

    public User selectUser(int id) {

        User user = null;

        try (
            Connection connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(SELECT_USER_BY_ID)
        ) {

            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");

                user = new User(
                        id,
                        name,
                        email,
                        country
                );
            }

        } catch (SQLException e) {

            printSQLException(e);
        }

        return user;
    }


    // =========================================================
    // SELECT ALL USERS
    // =========================================================

    public List<User> selectAllUsers() {

        List<User> users = new ArrayList<>();

        try (
            Connection connection = getConnection();

            PreparedStatement preparedStatement =
                    connection.prepareStatement(SELECT_ALL_USERS);

            ResultSet rs =
                    preparedStatement.executeQuery()
        ) {

            while (rs.next()) {

                int id = rs.getInt("id");

                String name =
                        rs.getString("name");

                String email =
                        rs.getString("email");

                String country =
                        rs.getString("country");

                User user = new User(
                        id,
                        name,
                        email,
                        country
                );

                users.add(user);
            }

            System.out.println(
                    "TOTAL USERS FOUND: " + users.size()
            );

        } catch (SQLException e) {

            printSQLException(e);
        }

        return users;
    }


    // =========================================================
    // DELETE USER
    // =========================================================

    public boolean deleteUser(int id) throws SQLException {

        boolean rowDeleted;

        try (
            Connection connection = getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(DELETE_USERS_SQL)
        ) {

            statement.setInt(1, id);

            rowDeleted =
                    statement.executeUpdate() > 0;

        }

        return rowDeleted;
    }


    // =========================================================
    // UPDATE USER
    // =========================================================

    public boolean updateUser(User user) throws SQLException {

        boolean rowUpdated;

        try (
            Connection connection = getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(UPDATE_USERS_SQL)
        ) {

            statement.setString(
                    1,
                    user.getName()
            );

            statement.setString(
                    2,
                    user.getEmail()
            );

            statement.setString(
                    3,
                    user.getCountry()
            );

            statement.setInt(
                    4,
                    user.getId()
            );

            rowUpdated =
                    statement.executeUpdate() > 0;
        }

        return rowUpdated;
    }


    // =========================================================
    // PRINT SQL ERRORS
    // =========================================================

    private void printSQLException(SQLException ex) {

        for (Throwable e : ex) {

            if (e instanceof SQLException) {

                e.printStackTrace(System.err);

                System.err.println(
                        "SQLState: " +
                        ((SQLException) e).getSQLState()
                );

                System.err.println(
                        "Error Code: " +
                        ((SQLException) e).getErrorCode()
                );

                System.err.println(
                        "Message: " +
                        e.getMessage()
                );
            }
        }
    }
}
