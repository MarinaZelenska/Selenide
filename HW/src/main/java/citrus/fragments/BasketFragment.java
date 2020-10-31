package citrus.fragments;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.Map;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class BasketFragment {

    SelenideElement bascketWidget = $x("//div[@class='el-dialog el-dialog--medium']");
    ElementsCollection firstProductName = $$x("//div[@class='el-dialog el-dialog--medium']//a[contains(text(), 'Apple iPhone 11 128Gb Black ')]");

    public SelenideElement getBasket() {
        return bascketWidget;
    }

    public ElementsCollection getProductNameFromBasket() {
        return firstProductName;

    }


    public ElementsCollection getBasketProductPrice() {
        return $$x("//span[@class='ctrs-main-price']");
    }

    public SelenideElement getFirstProductNameFromBasket() {
        return $x("//a[@class='ex-active active ctrs-basket-product__name']");
    }

    public SelenideElement getSecondBasketProductPrice() {
        return $x("//a[@class='ctrs-basket-product__name']");
    }
}
