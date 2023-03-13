import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardTest {

    @BeforeEach
    void setUp(){
        open("http://localhost:9999");
    }

    @Test
    void shouldRequest(){
        $("[data-test-id=name] input").setValue("Григорьев Игорь");
        $("[data-test-id=phone] input").setValue("+79999999999");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=order-success]").shouldHave(exactText(" Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));



    }
    @Test
    void shouldRequestNoName(){
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("+79999999999");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText(" Поле обязательно для заполнения"));


    }
    @Test
    void shouldRequestNotCorrectName(){
        $("[data-test-id=name] input").setValue("Igor");
        $("[data-test-id=phone] input").setValue("+79999999999");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }
    @Test
    void shouldRequestNoPhone(){
        $("[data-test-id=name] input").setValue("Григорьев Игорь");
        $("[data-test-id=phone] input").setValue("");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }
    @Test
    void shouldRequestNotCorrectPhone(){
        $("[data-test-id=name] input").setValue("Григорьев Игорь");
        $("[data-test-id=phone] input").setValue("011оустад");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldRequestNoAgreement(){
        $("[data-test-id=name] input").setValue("Григорьев Игорь");
        $("[data-test-id=phone] input").setValue("+79999999999");
        $("button").click();
        $("[data-test-id=agreement].input_invalid .checkbox__text").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }


}
