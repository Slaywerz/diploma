package ru.netology.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

public class PaymentInfo {
    private final SelenideElement cardNumber = Selenide.$("input[type='text'][placeholder='0000 0000 0000 0000']");
    private final SelenideElement monthValue = Selenide.$("input[type='text'][placeholder='08']");
    private final SelenideElement yearValue = Selenide.$("input[type='text'][placeholder='22']");
    private final SelenideElement cardHolderName = Selenide.$("fieldset div:nth-child(3) > span > span:nth-child(1) input");
    private final SelenideElement cvcCode = Selenide.$("input[type='text'][placeholder='999']");
    private final SelenideElement continueButton = Selenide.$(Selectors.byText("Продолжить"));
    private final SelenideElement errorNotification = Selenide.$(".notification_status_error");
    private final SelenideElement successNotification = Selenide.$(".notification_status_ok");
    private final SelenideElement invalidMonthNotification = Selenide.$(Selectors.byText("Неверно указан срок действия карты"));
    private final SelenideElement invalidYearNotification = Selenide.$(Selectors.byText("Неверно указан срок действия карты"));
    private final SelenideElement invalidFormatNotification = Selenide.$(Selectors.byText("Неверный формат"));
    private final SelenideElement necessarilyFieldNotification = Selenide.$(Selectors.byText("Поле обязательно для заполнения"));

    public void validFields(DataHelper.CardInfo cardInfo) {
        cardNumber.setValue(cardInfo.getCardNumber());
        monthValue.setValue(cardInfo.getMonth());
        yearValue.setValue(cardInfo.getYear());
        cardHolderName.setValue(cardInfo.getCardHolder());
        cvcCode.setValue(cardInfo.getCvcCode());
        continueButton.click();
        successNotification.shouldBe(Condition.visible);
    }

    public void setSuccessNotification() {
        successNotification.shouldBe(Condition.visible, Duration.ofSeconds(30));
    }

    public void setErrorNotification() {
        errorNotification.shouldBe(Condition.visible, Duration.ofSeconds(30));
    }

    public void setInvalidMonthNotification() {
        invalidMonthNotification.shouldBe(Condition.visible);
    }

    public void setInvalidYearNotification() {
        invalidYearNotification.shouldBe(Condition.visible);
    }

    public void setInvalidFormatNotification() {
        invalidFormatNotification.shouldBe(Condition.visible);
    }

    public void setNecessarilyFieldNotification() {
        necessarilyFieldNotification.shouldBe(Condition.visible);
    }
}
