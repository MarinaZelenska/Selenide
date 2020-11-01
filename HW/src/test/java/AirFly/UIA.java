package AirFly;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;

import static com.codeborne.selenide.Selenide.*;

public class UIA {

    @Test
    public void UkraineInternationalAirlinesTest() throws AWTException {
        Configuration.startMaximized = true;
        Configuration.baseUrl = "https://www.flyuia.com";
        open("/");
        Configuration.timeout = 10000;

        $$(".ng-untouched.ng-pristine.ng-valid").first().shouldBe(Condition.visible);
        sleep(3000);
        $$(".ng-untouched.ng-pristine.ng-valid").first().click();
        $$(".ng-untouched.ng-pristine.ng-valid").first().click();


        Robot robot = new Robot();

        robot.keyPress(KeyEvent.VK_V);
        robot.keyPress(KeyEvent.VK_I);
        robot.keyPress(KeyEvent.VK_E);
        robot.keyPress(KeyEvent.VK_N);
        sleep(3000);
        robot.keyPress(KeyEvent.VK_ENTER);
        $$(".ng-untouched.ng-pristine.ng-valid").get(0).click();
        Robot robotNext = new Robot();
        robotNext.keyPress(KeyEvent.VK_K);
        robotNext.keyPress(KeyEvent.VK_Y);
        robotNext.keyPress(KeyEvent.VK_I);
        robotNext.keyPress(KeyEvent.VK_V);

        sleep(3000);
        robotNext.keyPress(KeyEvent.VK_ENTER);
        $x("//span[@class='obe-sw-icon-calendar-departure']").click();
        $$x("//span[@class='prev-next-btn']").last().click();
        $x("//button[contains(text(), '19')]").click();
        $x("//span[@class='obe-sw-icon-calendar-arrivals']").click();
        $x("//button[contains(text(), '22')]").click();
        $x("//span[@class='obe-sw-icon-passenger']").click();
        $$x("//button[contains(text(), '+ ')]").get(0).click();
        $x("//span[@class='obe-sw-icon-promo-code']").click();
        $x("//form[@class='search-flights-button']").click();



        switchTo().window("Результаты поиска");
        //close popup with promocode
        $x("//i[@class='icon-close']").click();
        $$x("//div[@class='product__title']").shouldHaveSize(2);
        $x("//button[@class='date-container nonclickable ng-star-inserted']/div[contains(text(), '19')]").shouldBe(Condition.visible);
        $x("//button[@class='date-container nonclickable ng-star-inserted']/div[contains(text(), '22')]").shouldBe(Condition.visible);
        $x("//div[@class='product__title'][contains(text(), 'Варианты вылета: VIENNA - Kyiv')]").shouldBe(Condition.visible);
        $x("//div[@class='product__title'][contains(text(), 'Kyiv - VIENNA')]").shouldBe(Condition.visible);


    }
}
