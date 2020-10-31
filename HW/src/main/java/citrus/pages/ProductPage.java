package citrus.pages;

import citrus.fragments.BasketFragment;
import citrus.fragments.SearchFragment;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class ProductPage {

    BasketFragment basketFragment = new BasketFragment();
    SearchFragment searchFragment = new SearchFragment();


    SelenideElement price = $$x("//div[@class='price']/span/span").first();
    SelenideElement buyButton = $$x("//button[contains(text(), 'Купить')]").first();

    String minPrice = "5000";
    String maxPrice = "15000";


    public String getProductPrice() {
        return price.getText();
    }

    public ProductPage clickByButton() {
        buyButton.shouldBe(Condition.enabled).click();
        return this;
    }


    public BasketFragment getBasketFragment() {
        return basketFragment;
    }

    public SearchFragment getSearchFragment() {
        return searchFragment;
    }

    public ProductPage closeWindowBasket() {
        $x("//button[@class='el-dialog__headerbtn']").click();
        return this;
    }


    public ProductPage clickOnIconBasketHeader() {
        $x("//button[@class='el-dialog__headerbtn']").click();
        return this;
    }

    public ElementsCollection countProductNameOnBasket() {
        return $$x("//div[@class='ctrs-basket-item__products']");
    }

    public ProductPage clickFirstBasketIcon() {
        $$x("//i[@class='icon-new-citrus-cart el-tooltip item']").first().click();
        return this;
    }

    public ProductPage clickSecondBasketIcon() {
        $$x("//div[@class='itm-footer-desc']/following::i[@class='icon-new-citrus-cart el-tooltip item']").first().click();
        return this;
    }

    public String getTotalPrice() {
        String total =  $x("//span[@class='ctrs-main-price ctrs-basket-footer__new-price']").getText();
        return total;

    }

    public String getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
    }

    public String getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(String maxPrice) {
        this.maxPrice = maxPrice;
    }
}
