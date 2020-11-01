package AirFly;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

public class RyanairTest {


    @Test
    public void searchTicketsRyanair(){
        Configuration.startMaximized = true;
        Configuration.baseUrl = "https://www.ryanair.com/uk/uk";
        open("/");
        Configuration.timeout = 10000;



        $(By.id("input-button__departure")).sendKeys("Vienna");
        $(".list__clear-selection.ry-button--anchor-blue").click();
        $(By.id("input-button__departure")).sendKeys("Vienna");
        $x("//span[contains(text(),' Austria ')]").click();
        $(By.xpath("//span[contains(text(),' Vienna ')]")).click();
        $(By.id("input-button__destination")).sendKeys("Kyiv");
        $(By.xpath("//span[contains(text(),' Ukraine ')]")).click();
        $(By.xpath("//span[contains(text(),' Kyiv ')]")).click();

        $(By.xpath("//div[@data-id='2020-11-19']")).click();
        $(By.xpath("//div[@data-id='2020-11-21']")).click();

        $$x("//div[@class='counter__button-wrapper--enabled']").first().click();
        $(".passengers__confirm-button.ry-button--anchor-blue").click();

        $(By.xpath("//button[@data-ref='flight-search-widget__cta']")).click();

        $$(".card-header.b2").shouldHaveSize(2);
        $(By.xpath("//button[@data-ref='2020-11-19']//div[@class='date-item__info date-item__info--selected']")).shouldBe(Condition.visible);
        $(By.xpath("//button[@data-ref='2020-11-21']//div[@class='date-item__info date-item__info--selected']")).shouldBe(Condition.visible);
        $$x("//h3[@class='header__title']").get(0).shouldHave(Condition.text(" Vienna to Kyiv  "));
        $$x("//h3[@class='header__title']").get(1).shouldHave(Condition.text(" Kyiv to Vienna  "));




    }
}
