package ru.netology.test;

import com.codeborne.selenide.Selenide;
import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.database.DatabaseHelper;
import ru.netology.pages.PaymentChoice;
import ru.netology.pages.PaymentInfo;

public class SQLTest {

    @BeforeEach
    void setUp() {
        Selenide.open("http://localhost:8080/");
    }

    @AfterAll
    static void showDown() {
        Selenide.closeWindow();
    }

    @SneakyThrows
    @Test
    @DisplayName("Approved status for valid credit card")
    void shouldBeApprovedStatusForCreditCardInDb() {
        var paymentChoice = new PaymentChoice();
        paymentChoice.creditPayment();
        var paymentInfo = new PaymentInfo();
        paymentInfo.fieldsForDB(DataHelper.validValues());
        ;
        var id = DatabaseHelper.getId();
        Assertions.assertEquals("APPROVED", DatabaseHelper.getCreditCardStatus(id));
    }

    @SneakyThrows
    @Test
    @DisplayName("Approved status for valid debit card")
    void shouldBeApprovedStatusForDebitCardInDb() {
        var paymentChoice = new PaymentChoice();
        paymentChoice.debitPayment();
        var paymentInfo = new PaymentInfo();
        paymentInfo.fieldsForDB(DataHelper.validValues());
        var id = DatabaseHelper.getId();
        Assertions.assertEquals("APPROVED", DatabaseHelper.getDebitCardStatus(id));
    }

    @SneakyThrows
    @Test
    @DisplayName("Decline status for reject credit card")
    void shouldBeDeclineStatusForRejectCreditCard() {
        var paymentChoice = new PaymentChoice();
        paymentChoice.creditPayment();
        var paymentInfo = new PaymentInfo();
        paymentInfo.fieldsForDB(DataHelper.declinedValues());
        var id = DatabaseHelper.getId();
        Assertions.assertEquals("DECLINED", DatabaseHelper.getCreditCardStatus(id));
    }

    @SneakyThrows
    @Test
    @DisplayName("Decline status for reject debit card")
    void shouldBeDeclineStatusForRejectDebitCard() {
        var paymentChoice = new PaymentChoice();
        paymentChoice.debitPayment();
        var paymentInfo = new PaymentInfo();
        paymentInfo.fieldsForDB(DataHelper.declinedValues());
        var id = DatabaseHelper.getId();
        Assertions.assertEquals("DECLINED", DatabaseHelper.getDebitCardStatus(id));
    }

    @SneakyThrows
    @Test
    @DisplayName("Tour sum in db coincidence with the page sum")
    void shouldBeCoincidenceSum() {
        var paymentChoice = new PaymentChoice();
        paymentChoice.debitPayment();
        var paymentInfo = new PaymentInfo();
        paymentInfo.fieldsForDB(DataHelper.validValues());
        var id = DatabaseHelper.getId();
        Assertions.assertEquals(paymentInfo.getTravelSum(), DatabaseHelper.getSQLAmount(id));
    }
}