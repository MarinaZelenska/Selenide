package citrus.pages;

import citrus.fragments.BasketFragment;
import citrus.fragments.SearchFragment;
import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class Homepage {

    BasketFragment basketFragment = new BasketFragment();
    SearchFragment searchFragment = new SearchFragment();



    public Homepage hoverMenuLine(String menuText) {
        $x("//div[@class='menu--desktop static-menu']//span[contains(text(), '" + menuText + "')]").hover();
        return this;
    }


    public Homepage clickLinkInMenu(String linkText) {
        $x("//span[contains(text(), '" + linkText + "')]").click();
        return this;
    }


    public SearchFragment getSearchFragment() {
        return searchFragment;
    }

    public BasketFragment getBasketFragment() {
        return basketFragment;
    }



    public String findFirstProductName() {
        $x("//div[@class='title-itm']/h5[1]").shouldBe(Condition.visible);
        return  $x("//div[@class='title-itm']/h5[1]").getText();

    }

    public String findSecondProductName() {
        return  $$x("//div[@class='title-itm']/following::div/h5").first().getText();

    }

    public Homepage clickOnProductByNameHP() {
        $x("//div[@class='title-itm']/h5[1]").click();
        return this;
    }
}
