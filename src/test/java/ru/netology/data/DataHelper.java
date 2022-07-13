package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;

public class DataHelper {
    private DataHelper() {
    }

    private static String getApprovedCard() {
        return "4444 4444 4444 4441";
    }

    private static String getDeclinedCard() {
        return "4444 4444 4444 4442";
    }

    //    Карта не должна проходить валидацию т.к. её нет в gate-simulator
    private static String getErrorCard() {
        return "4444 4444 0258 4236";
    }

    private static String getIncompleteCardNumber() {
        return "4444 4444 4444";
    }

    private static String getMonth() {
        LocalDate localDate = LocalDate.now();
        return String.format("%02d", localDate.getMonthValue());
    }

    private static String getLastMonth() {
        LocalDate localDate = LocalDate.now();
        return String.format("%02d", localDate.minusMonths(1).getMonthValue());
    }

    private static String getYear() {
        DateFormat df = new SimpleDateFormat("yy");
        return df.format(Calendar.getInstance().getTime());
    }

    private static String getLastYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -1);
        DateFormat df = new SimpleDateFormat("yy");
        return df.format(calendar.getTime());
    }

    private static final Faker faker = new Faker();

    private static String getName() {
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    private static String getNameWithoutSurname() {
        return faker.name().firstName();
    }

    private static String getNameWithSymbols() {
        return faker.name().firstName() + " " + faker.name().lastName() + "!";
    }

    //    Т.к. имя должно быть написано на латинице, то добавляем метод, который генерирует имя на Китайском языке
    private static String getChineseName() {
        Faker faker1 = new Faker(new Locale("CN"));
        return faker1.name().firstName() + " " + faker1.name().lastName();
    }

    private static final Random random = new Random();

    //    Рандомное трехзначное число
    private static String getCvc() {
        return String.format("%03d", random.nextInt(1000 - 1));
    }

    //    Рандомное двухзначное число
    private static String getIncorrectCvc() {
        return String.format("%02d", random.nextInt(100 - 1));
    }

    @Value
    public static class CardInfo {
        private String cardNumber;
        private String month;
        private String year;
        private String cardHolder;
        private String cvcCode;
    }

    public static CardInfo validValues() {
        return new CardInfo(getApprovedCard(), getMonth(), getYear(), getName(), getCvc());
    }

    public static CardInfo declinedValues() {
        return new CardInfo(getDeclinedCard(), getMonth(), getYear(), getName(), getCvc());
    }

    public static CardInfo errorCardValues() {
        return new CardInfo(getErrorCard(), getMonth(), getYear(), getName(), getCvc());
    }

    public static CardInfo incompleteCardNumber() {
        return new CardInfo(getIncompleteCardNumber(), getMonth(), getYear(), getName(), getCvc());
    }

    public static CardInfo lastMonthNumber() {
        return new CardInfo(getDeclinedCard(), getLastMonth(), getYear(), getName(), getCvc());
    }

    public static CardInfo lastYearNumber() {
        return new CardInfo(getDeclinedCard(), getMonth(), getLastYear(), getName(), getCvc());
    }

    public static CardInfo nameWithSymbol() {
        return new CardInfo(getApprovedCard(), getMonth(), getYear(), getNameWithSymbols(), getCvc());
    }

    public static CardInfo chineseName() {
        return new CardInfo(getApprovedCard(), getMonth(), getYear(), getChineseName(), getCvc());
    }

    public static CardInfo symbolicName() {
        return new CardInfo(getDeclinedCard(), getMonth(), getYear(), "!@$#$%# ()()()*?*?", getCvc());
    }

    public static CardInfo numericName() {
        return new CardInfo(getApprovedCard(), getMonth(), getYear(), "12345 54321", getCvc());
    }

    public static CardInfo nameWithoutSurname() {
        return new CardInfo(getDeclinedCard(), getMonth(), getYear(), getNameWithoutSurname(), getCvc());
    }

    public static CardInfo invalidCvcNumber() {
        return new CardInfo(getApprovedCard(), getMonth(), getYear(), getName(), getIncorrectCvc());
    }

    //    Методы для отправки заявки с незаполненными полями
    public static CardInfo emptyCardField() {
        return new CardInfo(null, getMonth(), getYear(), getName(), getCvc());
    }

    public static CardInfo emptyMonthField() {
        return new CardInfo(getDeclinedCard(), null, getYear(), getName(), getCvc());
    }

    public static CardInfo emptyYearField() {
        return new CardInfo(getDeclinedCard(), getMonth(), null, getName(), getCvc());
    }

    public static CardInfo emptyNameField() {
        return new CardInfo(getDeclinedCard(), getMonth(), getYear(), null, getCvc());
    }

    public static CardInfo emptyCvcField() {
        return new CardInfo(getApprovedCard(), getMonth(), getYear(), getName(), null);
    }
}
