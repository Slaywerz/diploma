package ru.netology.test;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.pages.PaymentChoice;
import ru.netology.pages.PaymentInfo;


public class CardTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    void setUp() {
        Selenide.open("http://localhost:8080/");
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    @DisplayName("Success notification for approved debit card")
    void shouldBeShowSuccessNotification() {
        var paymentChoice = new PaymentChoice();
        paymentChoice.debitPayment();
        var paymentInfo = new PaymentInfo();
        paymentInfo.fields(DataHelper.validValues());
        paymentInfo.setSuccessNotification();
    }

    @Test
    @DisplayName("Error notification for declined debit card")
    void shouldBeShowErrorNotification() {
        var paymentChoice = new PaymentChoice();
        paymentChoice.debitPayment();
        var paymentInfo = new PaymentInfo();
        paymentInfo.fields(DataHelper.declinedValues());
        paymentInfo.setErrorNotification();
    }

    @Test
    @DisplayName("Success notification for approved Credit card")
    void shouldBeSuccessNotificationForCreditCard() {
        var paymentChoice = new PaymentChoice();
        paymentChoice.creditPayment();
        var paymentInfo = new PaymentInfo();
        paymentInfo.fields(DataHelper.validValues());
        paymentInfo.setSuccessNotification();
    }

    @Test
    @DisplayName("Error notification for declined credit card")
    void shouldBeErrorNotificationForCreditCard() {
        var paymentChoice = new PaymentChoice();
        paymentChoice.creditPayment();
        var paymentInfo = new PaymentInfo();
        paymentInfo.fields(DataHelper.declinedValues());
        paymentInfo.setErrorNotification();
    }

    @Test
    @DisplayName("Error notification for error debit card number")
    void shouldBeNotificationForErrorDebitCardNumber() {
        var paymentChoice = new PaymentChoice();
        paymentChoice.debitPayment();
        var paymentInfo = new PaymentInfo();
        paymentInfo.fields(DataHelper.errorCardValues());
        paymentInfo.setErrorNotification();
    }

    @Test
    @DisplayName("Error notification for error credit card number")
    void shouldBeNotificationForErrorCreditCardNumber() {
        var paymentChoice = new PaymentChoice();
        paymentChoice.creditPayment();
        var paymentInfo = new PaymentInfo();
        paymentInfo.fields(DataHelper.errorCardValues());
        paymentInfo.setErrorNotification();
    }

    @Test
    @DisplayName("Invalid format notification for incomplete card number")
    void shouldBeNotificationForIncompleteCardNumber() {
        var paymentChoice = new PaymentChoice();
        paymentChoice.debitPayment();
        var paymentInfo = new PaymentInfo();
        paymentInfo.fields(DataHelper.incompleteCardNumber());
        paymentInfo.setInvalidFormatNotification();
    }

    @Test
    @DisplayName("Invalid month notification for expired card in last month")
    void shouldBeNotificationForExpiredCardInLastMonth() {
        var paymentChoice = new PaymentChoice();
        paymentChoice.creditPayment();
        var paymentInfo = new PaymentInfo();
        paymentInfo.fields(DataHelper.lastMonthNumber());
        paymentInfo.setInvalidMonthNotification();
    }

    @Test
    @DisplayName("Invalid year notification for expired card in last year")
    void shouldBeNotificationForExpiredCardInLastYear() {
        var paymentChoice = new PaymentChoice();
        paymentChoice.debitPayment();
        var paymentInfo = new PaymentInfo();
        paymentInfo.fields(DataHelper.lastYearNumber());
        paymentInfo.setInvalidYearNotification();
    }

    @Test
    @DisplayName("Invalid format notification for name with symbol")
    void shouldBeErrorNotificationForNameWithSymbols() {
        var paymentChoice = new PaymentChoice();
        paymentChoice.creditPayment();
        var paymentInfo = new PaymentInfo();
        paymentInfo.fields(DataHelper.nameWithSymbol());
        paymentInfo.setInvalidFormatNotification();
    }

    @Test
    @DisplayName("Invalid format notification for chinese name")
    void shouldBeErrorNotificationForChineseName() {
        var paymentChoice = new PaymentChoice();
        paymentChoice.debitPayment();
        var paymentInfo = new PaymentInfo();
        paymentInfo.fields(DataHelper.chineseName());
        paymentInfo.setNecessarilyFieldNotification();
    }

    @Test
    @DisplayName("Invalid format notification for symbolic name")
    void shouldBeErrorNotificationForSymbolicName() {
        var paymentChoice = new PaymentChoice();
        paymentChoice.creditPayment();
        var paymentInfo = new PaymentInfo();
        paymentInfo.fields(DataHelper.symbolicName());
        paymentInfo.setNecessarilyFieldNotification();
    }

    @Test
    @DisplayName("Invalid format notification for numeric name")
    void shouldBeErrorNotificationForNumericName() {
        var paymentChoice = new PaymentChoice();
        paymentChoice.debitPayment();
        var paymentInfo = new PaymentInfo();
        paymentInfo.fields(DataHelper.numericName());
        paymentInfo.setSuccessNotification();
    }

    @Test
    @DisplayName("Necessary field notification when enter a first name without last name")
    void shouldBeNecessaryNotificationWithoutLastName() {
        var paymentChoice = new PaymentChoice();
        paymentChoice.creditPayment();
        var paymentInfo = new PaymentInfo();
        paymentInfo.fields(DataHelper.nameWithoutSurname());
        paymentInfo.setNecessarilyFieldNotification();
    }

    @Test
    @DisplayName("Invalid format cvc number (2 numbers instead 3")
    void shouldBeInvalidFormatForTwoNumbersInCvcField() {
        var paymentChoice = new PaymentChoice();
        paymentChoice.debitPayment();
        var paymentInfo = new PaymentInfo();
        paymentInfo.fields(DataHelper.invalidCvcNumber());
        paymentInfo.setInvalidFormatNotification();
    }

    @Test
    @DisplayName("Invalid format notification for empty card field")
    void shouldBeErrorNotificationForEmptyCardField() {
        var paymentChoice = new PaymentChoice();
        paymentChoice.creditPayment();
        var paymentInfo = new PaymentInfo();
        paymentInfo.fields(DataHelper.emptyCardField());
        paymentInfo.setInvalidFormatNotification();
    }

    @Test
    @DisplayName("Invalid format notification for empty month field")
    void shouldBeErrorNotificationForEmptyMonthField() {
        var paymentChoice = new PaymentChoice();
        paymentChoice.creditPayment();
        var paymentInfo = new PaymentInfo();
        paymentInfo.fields(DataHelper.emptyMonthField());
        paymentInfo.setInvalidFormatNotification();
    }

    @Test
    @DisplayName("Invalid format notification for empty year field")
    void shouldBeErrorNotificationForEmptyYearField() {
        var paymentChoice = new PaymentChoice();
        paymentChoice.creditPayment();
        var paymentInfo = new PaymentInfo();
        paymentInfo.fields(DataHelper.emptyYearField());
        paymentInfo.setInvalidFormatNotification();
    }

    @Test
    @DisplayName("Necessary field notification for empty name field")
    void shouldBeErrorNotificationForEmptyNameField() {
        var paymentChoice = new PaymentChoice();
        paymentChoice.creditPayment();
        var paymentInfo = new PaymentInfo();
        paymentInfo.fields(DataHelper.emptyNameField());
        paymentInfo.setNecessarilyFieldNotification();
    }

    @Test
    @DisplayName("Invalid format notification for empty CVC field")
    void shouldBeErrorNotificationForEmptyCvcField() {
        var paymentChoice = new PaymentChoice();
        paymentChoice.creditPayment();
        var paymentInfo = new PaymentInfo();
        paymentInfo.fields(DataHelper.emptyCvcField());
        paymentInfo.setInvalidFormatNotification();
    }

    @Test
    @DisplayName("Error notification field for zero month")
    void shouldBeErrorNotificationForZeroMonth() {
        var paymentChoice = new PaymentChoice();
        paymentChoice.debitPayment();
        var paymentInfo = new PaymentInfo();
        paymentInfo.fields(DataHelper.zeroMonth());
        paymentInfo.setInvalidMonthNotification();
    }

    @Test
    @DisplayName("Error notification field for zero year")
    void shouldBeErrorNotificationForZeroYear() {
        var paymentChoice = new PaymentChoice();
        paymentChoice.debitPayment();
        var paymentInfo = new PaymentInfo();
        paymentInfo.fields(DataHelper.zeroYear());
        paymentInfo.setInvalidYearNotification();
    }

    @Test
    @DisplayName("Hiding previous success notification")
    void shouldHiddenPreviousNotification() {
        var paymentChoice = new PaymentChoice();
        paymentChoice.creditPayment();
        var paymentInfo = new PaymentInfo();
        paymentInfo.fields(DataHelper.validValues());
        paymentInfo.setSuccessNotification();
        paymentInfo.clearFields();
        paymentInfo.fields(DataHelper.errorCardValues());
        paymentInfo.setErrorNotification();
        paymentInfo.setHiddenSuccessNotification();
    }

    @Test
    @DisplayName("Hiding previous error notification")
    void shouldHiddenPreviousErrorNotification() {
        var paymentChoice = new PaymentChoice();
        paymentChoice.debitPayment();
        var paymentInfo = new PaymentInfo();
        paymentInfo.fields(DataHelper.errorCardValues());
        paymentInfo.setErrorNotification();
        paymentInfo.clearFields();
        paymentInfo.fields(DataHelper.validValues());
        paymentInfo.setSuccessNotification();
        paymentInfo.setHiddenErrorNotification();
    }
}
