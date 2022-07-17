package ru.netology.database;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHelper {
    static String url = "jdbc:mysql://localhost:3306/app";
//    static String url = "jdbc:postgresql://localhost:5432/app";
    static String user = "app";
    static String password = "pass";

    //    Создаем connection к БД
    @SneakyThrows
    public static Connection getConnection() {
        return DriverManager.getConnection(url, user, password);
    }

    //    Получаем ID из таблицы order_entity
    public static String getId() throws SQLException {
        String PaymentId = null;
//        Выбираем последнюю запись из таблицы чтобы найти нужный ID'шник
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

    //    Метод принимает ID, по которому можно найти запись в таблице
    public static String getCreditCardStatus(String paymentId) throws SQLException {
        String creditCardStatus = null;
        var statusSQL = "SELECT status FROM credit_request_entity where bank_id = ?";
        try (var conn = getConnection();
             var statusStmt = conn.prepareStatement(statusSQL)) {
            statusStmt.setString(1, paymentId);
            try (var rs = statusStmt.executeQuery()) {
                if (rs.next()) {
                    creditCardStatus = rs.getString("status");
                }
            }
        }
        return creditCardStatus;
    }

    //     Метод принимает ID, по которому можно найти запись в таблице
    public static String getDebitCardStatus(String paymentId) throws SQLException {
        String debitCardStatus = null;
        var statusSQL = "SELECT status FROM payment_entity where transaction_id = ?";
        try (var conn = getConnection();
             var statusStmt = conn.prepareStatement(statusSQL)) {
            statusStmt.setString(1, paymentId);
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
        var amountSQL = "SELECT amount FROM payment_entity WHERE transaction_id = ?";
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