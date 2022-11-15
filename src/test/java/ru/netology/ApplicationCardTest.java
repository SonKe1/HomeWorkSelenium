package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class ApplicationCardTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999/");
    }

    @Test
    public void testFieldsFilledCorrectly() {
        $("[data-test-id=name] input").setValue("Миронов Артур");
        $("[data-test-id=phone] input").setValue("+79898568421");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));

    }

    @Test
    public void testPositiveWithHyphenatedName() {
        $("[data-test-id=name] input").setValue("Мария Миронова-Вердер");
        $("[data-test-id=phone] input").setValue("+79898568421");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    public void testPhoneNumberConsistsZero() {
        $("[data-test-id=name] input").setValue("Миронов Артур");
        $("[data-test-id=phone] input").setValue("+70000000000");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }
}
