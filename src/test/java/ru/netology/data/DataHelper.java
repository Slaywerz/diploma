package ru.netology.data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.util.Random;

public class DataHelper {
    private DataHelper() {
    }


    private static String getMonth() {
        LocalDate localDate = LocalDate.now();
        int month = localDate.getMonthValue();
        return String.format("%02d", month);
    }

    private static String getYear() {
        LocalDate localDate = LocalDate.now();
        return String.format("%02d", localDate.getYear());
    }

    private static String getApprovedCard() {
        return "4444 4444 4444 4441";
    }

    private static String getCVC() {
        Random random = new Random();
        return String.format("%03d", random.nextInt(100 - 1));
    }

    private static String getName() {
    Faker faker = new Faker();
    return faker.name().firstName() + " " + faker.name().lastName();
    }
}
