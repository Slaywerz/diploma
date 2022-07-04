package ru.netology.database;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHelper {
    static String url = System.getProperty("db.url");
    static String user = System.getProperty("db.user");
    static String password = System.getProperty("db.password");

    public static void CleanData() throws SQLException {
        var runner = new QueryRunner();
        try (
                var conn = DriverManager.getConnection(url, user, password)) {
            runner.update(conn, "DELETE FROM credit_request_entity");
            runner.update(conn, "DELETE FROM order_entity");
            runner.update(conn, "DELETE FROM payment_entity");
        }
    }

    public static String getStatus(String query) throws SQLException {
        var runner = new QueryRunner();
        try (
                var conn = DriverManager.getConnection(url, user, password)
        ) {
            return runner.query(conn, query, new ScalarHandler<String>());
        }
    }

    @SneakyThrows
    public static Integer getSQLAmount() {
        var runner = new QueryRunner();
        try (var conn = DriverManager.getConnection(url, user, password)) {
            var amount = "SELECT amount FROM payment_entity";
            return runner.query(conn, amount, new ScalarHandler<>());
        }
    }

    public static String getDebitStatus() throws SQLException {
        var debitSQLStatus = "SELECT status FROM payment_entity";
        return getStatus(debitSQLStatus);
    }

    public static String getCreditStatus() throws SQLException {
        var creditSQLStatus = "SELECT status FROM credit_request_entity";
        return getStatus(creditSQLStatus);
    }
}
