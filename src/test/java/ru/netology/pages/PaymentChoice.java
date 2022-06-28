package ru.netology.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

public class PaymentChoice {
    private final SelenideElement heading = Selenide.$(Selectors.byText("Путешествие дня"));
    private final SelenideElement creditPayment = Selenide.$(Selectors.byText("Купить в кредит"));
    private final SelenideElement debitPayment = Selenide.$(Selectors.byText("Купить"));
    private final SelenideElement debitCardText = Selenide.$(Selectors.byText("Оплата по карте"));
    private final SelenideElement creditCardText = Selenide.$(Selectors.byText("Кредит по данным карты"));

    public PaymentChoice() {
        heading.shouldBe(Condition.visible);
    }

    public PaymentInfo debitPayment() {
        debitPayment.click();
        debitCardText.shouldBe(Condition.visible);
        return new PaymentInfo();
    }

    public PaymentInfo creditPayment() {
        creditPayment.click();
        creditCardText.shouldBe(Condition.visible);
        return new PaymentInfo();
    }
}
