package ru.netology.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
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

    private final SelenideElement invalidYearNotification = Selenide.$(Selectors.byText("Истёк срок действия карты"));

    private final SelenideElement invalidFormatNotification = Selenide.$(Selectors.byText("Неверный формат"));

    private final SelenideElement necessarilyFieldNotification = Selenide.$(Selectors.byText("Поле обязательно для заполнения"));

    public void fields(DataHelper.CardInfo cardInfo) {
        cardNumber.setValue(cardInfo.getCardNumber());
        monthValue.setValue(cardInfo.getMonth());
        yearValue.setValue(cardInfo.getYear());
        cardHolderName.setValue(cardInfo.getCardHolder());
        cvcCode.setValue(cardInfo.getCvcCode());
        continueButton.click();
    }

    public void fieldsForDB(DataHelper.CardInfo cardInfo) {
        cardNumber.setValue(cardInfo.getCardNumber());
        monthValue.setValue(cardInfo.getMonth());
        yearValue.setValue(cardInfo.getYear());
        cardHolderName.setValue(cardInfo.getCardHolder());
        cvcCode.setValue(cardInfo.getCvcCode());
        continueButton.click();
        try {
            Thread.sleep(8500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void setSuccessNotification() {
        successNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public void setHiddenSuccessNotification() {
        successNotification.shouldBe(Condition.hidden);
    }

    public void setHiddenErrorNotification() {
        errorNotification.shouldBe(Condition.hidden);
    }

    public void setErrorNotification() {
        errorNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public void setInvalidFormatNotification() {
        invalidFormatNotification.shouldBe(Condition.visible);
    }

    public void setInvalidMonthNotification() {
        invalidMonthNotification.shouldBe(Condition.visible);
    }

    public void setInvalidYearNotification() {
        invalidYearNotification.shouldBe(Condition.visible);
    }

    public void setNecessarilyFieldNotification() {
        necessarilyFieldNotification.shouldBe(Condition.visible);
    }

    private static final String sumStartForThousand = "Всего ";
    private static final String sumStartForHundred = "Всего 45 ";
    private static final String sumEndForThousand = " 000 руб.!";
    private static final String sumEndForHundred = " руб.!";

    public Integer getTravelSum() {
        SelenideElement travelSum = Selenide.$(Selectors.withText("руб."));
        var text = travelSum.text();
        var start = text.indexOf(sumStartForThousand);
        var finish = text.indexOf(sumEndForThousand);
        var extractThousandValue = text.substring(start + sumStartForThousand.length(), finish);
        var thousandValue = Integer.parseInt(extractThousandValue);
        var start1 = text.indexOf(sumStartForHundred);
        var finish1 = text.indexOf(sumEndForHundred);
        var extractHundredValue = text.substring(start1 + sumStartForHundred.length(), finish1);
        var hundredValue = Integer.parseInt(extractHundredValue);
        return thousandValue * 1000 + hundredValue;
    }

    public void clearFields() {
        cardNumber.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
        monthValue.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
        yearValue.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
        cardHolderName.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
        cvcCode.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
    }
}
