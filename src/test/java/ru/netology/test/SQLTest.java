package ru.netology.test;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.database.DatabaseHelper;
import ru.netology.pages.PaymentChoice;
import ru.netology.pages.PaymentInfo;

public class SQLTest {

    @SneakyThrows
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        DatabaseHelper.clearDb();
    }

    @BeforeEach
    void setUp() {
        Selenide.open("http://localhost:8080/");
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @SneakyThrows
    @Test
    @Severity(SeverityLevel.CRITICAL)
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
    @Severity(SeverityLevel.CRITICAL)
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
    @Severity(SeverityLevel.CRITICAL)
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
    @Severity(SeverityLevel.CRITICAL)
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
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Tour sum in db coincidence with the page sum")
    void shouldBeCoincidenceSum() {
        var paymentChoice = new PaymentChoice();
        paymentChoice.debitPayment();
        var paymentInfo = new PaymentInfo();
        paymentInfo.fieldsForDB(DataHelper.validValues());
        var id = DatabaseHelper.getId();
        Assertions.assertEquals(paymentInfo.getTravelSum(), DatabaseHelper.getSQLAmount(id));
    }

    @SneakyThrows
    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Key ID in order_entity is not null")
    void shouldNotNullKeyIdInOrderEntity() {
        var paymentChoice = new PaymentChoice();
        paymentChoice.debitPayment();
        var paymentInfo = new PaymentInfo();
        paymentInfo.fieldsForDB(DataHelper.validValues());
        var id = DatabaseHelper.getId();
        Assertions.assertNotNull(DatabaseHelper.getKeyIdInOrderEntity(id));
    }

    @SneakyThrows
    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Credit_id in order_entity is not null")
    void shouldNotNullCreditIdInOrderEntity() {
        var paymentChoice = new PaymentChoice();
        paymentChoice.creditPayment();
        var paymentInfo = new PaymentInfo();
        paymentInfo.fieldsForDB(DataHelper.declinedValues());
        var id = DatabaseHelper.getId();
        Assertions.assertNotNull(DatabaseHelper.getCreditId(id));

    }

    @SneakyThrows
    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Time in order_entity matched with PC")
    void shouldBeMatchedTimeInDb() {
        var paymentChoice = new PaymentChoice();
        paymentChoice.debitPayment();
        var paymentInfo = new PaymentInfo();
        paymentInfo.fieldsForDB(DataHelper.validValues());
        var id = DatabaseHelper.getId();
        Assertions.assertEquals(DataHelper.getUctTimeNow(), DatabaseHelper.getCreatedTimeInOrderEntity(id));
    }

    @SneakyThrows
    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("ID column in table credit_request_entity is not null")
    void shouldNotBeNullKeyIdInCreditEntityTable() {
        var paymentChoice = new PaymentChoice();
        paymentChoice.creditPayment();
        var paymentInfo = new PaymentInfo();
        paymentInfo.fieldsForDB(DataHelper.validValues());
        var id = DatabaseHelper.getId();
        Assertions.assertNotNull(DatabaseHelper.getKeyIdInCreditEntity(id));
    }

    @SneakyThrows
    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Time in table credit_request_entity matched with PC")
    void shouldBeMatchedTimeWithPCInCreditEntity() {
        var paymentChoice = new PaymentChoice();
        paymentChoice.creditPayment();
        var paymentInfo = new PaymentInfo();
        paymentInfo.fieldsForDB(DataHelper.declinedValues());
        var id = DatabaseHelper.getId();
        Assertions.assertEquals(DataHelper.getUctTimeNow(), DatabaseHelper.getCreatedTimeInCreditEntity(id));
    }

    @SneakyThrows
    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("ID column in table payment_entity is not null")
    void shouldNotBeNullIdColumnInPaymentEntityTable() {
        var paymentChoice = new PaymentChoice();
        paymentChoice.debitPayment();
        var paymentInfo = new PaymentInfo();
        paymentInfo.fieldsForDB(DataHelper.validValues());
        var id = DatabaseHelper.getId();
        Assertions.assertNotNull(DatabaseHelper.getKeyIdInPaymentEntity(id));
    }

    @SneakyThrows
    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Time matched with PC in payment_entity table")
    void shouldBeMatchedTimeWithPCInPaymentEntity() {
        var paymentChoice = new PaymentChoice();
        paymentChoice.debitPayment();
        var paymentInfo = new PaymentInfo();
        paymentInfo.fieldsForDB(DataHelper.validValues());
        var id = DatabaseHelper.getId();
        Assertions.assertEquals(DataHelper.getUctTimeNow(), DatabaseHelper.getCreatedTimeInPaymentEntity(id));
    }
}