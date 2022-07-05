package ru.netology.test;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.pages.PaymentChoice;
import ru.netology.pages.PaymentInfo;


public class Tests {

    @BeforeEach
    void setUp() {
        Selenide.open("http://localhost:8080/");
    }

    @AfterAll
    static void showDown() {
        Selenide.closeWindow();
    }

    @Test
    @DisplayName("Show success notification for approved debit card")
    void shouldBeShowSuccessNotification() {
        var paymentChoice = new PaymentChoice();
        paymentChoice.debitPayment();
        var paymentInfo = new PaymentInfo();
        paymentInfo.fields(DataHelper.validValues());
        paymentInfo.setSuccessNotification();
    }

    @Test
    @DisplayName("Show error notification for declined debit card")
    void shouldBeShowErrorNotification() {
        var paymentChoice = new PaymentChoice();
        paymentChoice.debitPayment();
        var paymentInfo = new PaymentInfo();
        paymentInfo.fields(DataHelper.declinedValues());
        paymentInfo.setErrorNotification();
    }

    @Test
    @DisplayName("Show success notification for approved Credit card")
    void shouldShowSuccessNotificationForCreditCard() {
        var paymentChoice = new PaymentChoice();
        paymentChoice.creditPayment();
        var paymentInfo = new PaymentInfo();
        paymentInfo.fields(DataHelper.validValues());
        paymentInfo.setSuccessNotification();
    }

    @Test
    @DisplayName("Show error notification for declined Credit card")
    void shouldShowErrorNotificationForCreditCard() {
        var paymentChoice = new PaymentChoice();
        paymentChoice.creditPayment();
        var paymentInfo = new PaymentInfo();
        paymentInfo.fields(DataHelper.declinedValues());
        paymentInfo.setErrorNotification();
    }
}
