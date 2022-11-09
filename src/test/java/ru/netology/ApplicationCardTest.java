package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ApplicationCardTest {
    WebDriver driver;
    private static ChromeOptions options;

    @BeforeAll
    static void setUpAll() {
        options = new ChromeOptions();
        options.addArguments("--headless");
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver(options);
        open("http://localhost:9999/");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
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
    public void testSendFormWithZero() {
        $("[data-test-id=name] input").setValue("Миронов Артур");
        $("[data-test-id=phone] input").setValue("+70000000000");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }
}
