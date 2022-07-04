package ru.netology.database;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHelper {
    static String url = System.getProperty("db.url");
    static String user = System.getProperty("db.user");
    static String password = System.getProperty("db.password");

    @SneakyThrows
    public static Connection getConnection() {
        return DriverManager.getConnection(url, user, password);
    }

    public static String getId() throws SQLException {
        String PaymentId = null;
        var idSQL = "SELECT payment_id FROM order_entity order by created DESC limit 1";
        try (var conn = getConnection();
             var statusStmt = conn.prepareStatement(idSQL)) {
            try (var rs = statusStmt.executeQuery()) {
                if (rs.next()) {
                    PaymentId = rs.getString("payment_id");
                }
            }
        }
        return PaymentId;
    }

    public static String getCreditCardStatus(String paymentId) throws SQLException {
        String creditCardStatus = null;
        var statusSQL = "SELECT status FROM credit_request_entity where bank_id = ?";
        try (var conn = getConnection();
             var statusStmt = conn.prepareStatement(statusSQL)) {
            try (var rs = statusStmt.executeQuery()) {
                if (rs.next()) {
                    creditCardStatus = rs.getString("status");
                }
            }
        }
        return creditCardStatus;
    }

    public static String getDebitCardStatus(String paymentId) throws SQLException {
        String debitCardStatus = null;
        var statusSQL = "SELECT status FROM payment_entity where transaction_id =?";
        try (var conn = getConnection();
             var statusStmt = conn.prepareStatement(statusSQL)) {
            try (var rs = statusStmt.executeQuery()) {
                if (rs.next()) {
                    debitCardStatus = rs.getString("status");
                }
            }
        }
        return debitCardStatus;
    }

    public static Integer getSQLAmount(String paymentId) throws SQLException {
        Integer amount = null;
        var amountSQL = "SELECT amount FROM payment_entity WHERE transactions_id = ?";
        try (var conn = getConnection();
             var amountStmt = conn.prepareStatement(amountSQL)) {
            amountStmt.setString(1, paymentId);
            try (var rs = amountStmt.executeQuery()) {
                if (rs.next()) {
                    amount = rs.getInt("amount");
                }
            }
        }
        return amount;
    }
}
