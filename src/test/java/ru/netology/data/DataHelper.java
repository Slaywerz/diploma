package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
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
        DateFormat dateFormat = new SimpleDateFormat("yy");
        return dateFormat.format(Calendar.getInstance().getTime());
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

    @Value
    public static class CardInfo {
        private String cardNumber;
        private String month;
        private String year;
        private String cardHolder;
        private String cvcCode;
    }

    public static CardInfo validValues (){
        return new CardInfo(getApprovedCard(), getMonth(), getYear(), getName(), getCVC());
    }
}
