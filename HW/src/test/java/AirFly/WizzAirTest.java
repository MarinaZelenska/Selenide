package AirFly;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

public class WizzAirTest {

    @Test
    public void searchTicketsWizzair(){
        Configuration.startMaximized = true;
        Configuration.baseUrl = "https://wizzair.com";
        open("/");
        Configuration.timeout = 50000;



        $(By.id("search-departure-station")).val("Vienna");
        $(By.xpath("//mark[contains(text(), 'Vienna')]")).click();

        $(By.id("search-arrival-station")).val("Kyiv");
        $(By.xpath("//mark[contains(text(), 'Kyiv')]")).click();


        $(By.id("search-departure-date")).click();
        $(By.xpath("//button[@data-pika-year='2020'][@data-pika-month='10'][@data-pika-day='19']")).click();
        $(By.xpath("//button[@data-pika-year='2020'][@data-pika-month='10'][@data-pika-day='22']")).click();
        $(By.xpath("//div[@class='calendar__button']//button[2]")).click();

        $(By.id("search-passenger")).click();
        $$x("//button[@class='stepper__button stepper__button--add']").first().click();
        $$x("//button[@class='base-button base-button--medium base-button--primary']").last().click();
        $(By.xpath("//button[@data-test='flight-search-submit']")).click();

        switchTo().window(1);

        $$(".flight-select__fare-selector").shouldHaveSize(2);
        $$(".flight-select__flight-info__day").get(0).shouldHave(Condition.text(" Thu, 19 Nov 2020 "));
        $$(".flight-select__flight-info__day").get(1).shouldHave(Condition.text(" Sun, 22 Nov 2020 "));

        $$x("//span[@class='station']").get(0).shouldHave(Condition.text("Vienna"));
        $$x("//span[@class='station']").get(1).shouldHave(Condition.text("Kyiv - Zhulyany"));















    }
}
