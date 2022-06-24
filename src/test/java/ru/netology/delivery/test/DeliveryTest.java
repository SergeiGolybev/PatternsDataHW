package ru.netology.delivery.test;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.delivery.data.UserInfo;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static ru.netology.delivery.data.DataGenerator.Registration.generateDate;
import static ru.netology.delivery.data.DataGenerator.Registration.generateInfo;

public class DeliveryTest {

    @BeforeEach
    void setUpTest() {
        open("http://localhost:9999");
    }

    @Test
    void shouldSendForm() {
        UserInfo data = generateInfo();
        String setDate = generateDate(3);
        String changeDate = generateDate(5);
        $("[data-test-id='city'] input").val(data.getCity());
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id='date'] input").val(setDate);
        $("[data-test-id='name'] input").val(data.getName());
        $("[data-test-id='phone'] input").val(data.getPhone());
        $("[data-test-id='agreement']").click();
        $(withText("Запланировать")).click();
        $(".notification__content").shouldHave(Condition.text("Встреча успешно запланирована на " + setDate), Duration.ofSeconds(15));
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.DELETE);;
        $("[data-test-id='date'] input").val(changeDate);
        $(withText("Запланировать")).click();
        $(withText("Необходимо подтверждение")).shouldBe(Condition.visible, Duration.ofSeconds(15));
        $(withText("Перепланировать")).click();
        $(".notification__content").shouldHave(Condition.text("Встреча успешно запланирована на " + changeDate), Duration.ofSeconds(15));
    }
}