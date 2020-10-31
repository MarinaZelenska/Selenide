package citrus.pages;

import citrus.fragments.BasketFragment;
import citrus.fragments.SearchFragment;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static javatests.TestSupport.assertEquals;
import static javatests.TestSupport.assertThat;

public class ProductListPage {

    BasketFragment basketFragment = new BasketFragment();
    SearchFragment searchFragment = new SearchFragment();
    ProductPage productPage = new ProductPage();

    SelenideElement priceOnProductList = $x("//a[contains(text(), 'Apple iPhone 11 256Gb Black')]/following-sibling::div//div[@class='ctrs-basket-mini-product__price']");
    SelenideElement basketIconOnProductList = $$x("//a[@title='Apple iPhone 11 256Gb Black (MWM72)']/following-sibling::div[@class='itm-footer-desc']//i").first();

    SelenideElement filterMinSizeField = $x("//input[@class='el-input__inner']");
    SelenideElement filterMaxSizeField = $x("//div[@class='price el-input']/following::input");
    String minPrice = "5000";
    String maxPrice = "15000";

    public ProductListPage clickOnProductByName(String productName) {
        $x("//span[contains(text(), '" + productName + "')]").click();
        return this;
    }

    public String getProductPriceOnList() {
        return priceOnProductList.getText();

    }

    public ProductListPage clickOnBasketIcon() {
        basketIconOnProductList.click();
        return this;
    }

    public SearchFragment getSearchFragment() {
        return searchFragment;
    }

    public BasketFragment getBasketFragment() {
        return basketFragment;
    }

    public String getFirstProductPriceOnList() {
        return $$x("//span[@class='price-number']").first().getText();
    }

    public ProductListPage clickFirstBasketIcon() {
        $$x("//i[@class='icon-new-citrus-cart el-tooltip item']").first().click();
        return this;
    }

    public String getSecondProductPriceOnList() {
        return $$x("//div[@class='base-price']/following::span[@class='price-number']").first().getText();
    }

    public ProductListPage clickSecondBasketIcon() {
        $$x("//div[@class='itm-footer-desc']/following::i[@class='icon-new-citrus-cart el-tooltip item']").first().click();
        return this;
    }

    public ProductListPage clickFirstCompareIcon() {
        $$x("//i[@class='icon-comparison2 el-tooltip item']").first().click();
        return this;
    }

    public ProductListPage clickSecondCompareIcon() {
        $$x("//div[@class='itm-footer-desc']/following::i[@class='icon-comparison2 el-tooltip item']").first().click();
        return this;
    }

    public ProductListPage clickCompareIconInHeader() {
        $x("//div[@class='user-actions__compare tips-parent']/i").click();
        return this;
    }


    public ProductListPage addMinPrice() {
        filterMinSizeField.clear();
        filterMinSizeField.sendKeys(productPage.minPrice);
        return this;

    }

    public ProductListPage addMaxPice() {
        filterMaxSizeField.click();
        filterMaxSizeField.clear();
        filterMaxSizeField.sendKeys(productPage.maxPrice);
        return this;

    }

    public ProductListPage clickForDownloadProductSelectedFilter() {
        $x("//div[@class='main-content__promotion-main']").click();
        return this;
    }

    public ElementsCollection productPrice() {
       return  $$x("//div[@class='prices__price']//span[@class='price']");


    }

    public ElementsCollection titleProduct() {
        return  $$x("//div[@class='product-card__name']/a/span");
    }

    public ProductListPage checkContainsTextTitle(String productName) {
        $$x("//div[@class='product-card product-card--mini']").first().isDisplayed();
        for(SelenideElement element: titleProduct()){
            element.shouldHave(Condition.text(productName));
        }
        return this;
    }

    public ProductListPage checkDiapasonPrice() throws Exception {
        for (int priceCounter = 0; priceCounter < productPrice().size();priceCounter++){
            String strPrice = productPrice().get(priceCounter).getText().replaceAll("\\s", "");
            int IntPrice = Integer.parseInt(strPrice);
            if(IntPrice < 5000 || IntPrice > 15000 ){
                throw new Exception("Incorrect Price");
            }
        }
        return this;
    }

    public ProductListPage scrollToElementMemory() {
        $x("//div[contains(text(), 'Объем встроенной памяти')]").scrollTo();
        return this;
    }

    public ProductListPage selectFirstMemorySize() {
        $x("//span[@class='el-checkbox__label']//a[contains(text(), '32')]").click();
        return this;
    }

    public ProductListPage selectSecondMemorySize() {
        $x("//span[@class='el-checkbox__label']//a[contains(text(), '64')]").click();
        return this;
    }

    public ProductListPage checkDiapasonMemory() {

        int sumProduct = $$x("//a[@title='32 GB']").size() + $$x("//a[@title='64 GB']").size();
        $$x("//div[@class='product-card product-card--mini']").shouldHaveSize(sumProduct);
        return this;

    }


    public ProductListPage scrollToElementMaterial() {
        $x("//div[contains(text(), 'Материалы корпуса')]").scrollTo();
        return this;

    }

    public ProductListPage addMaterialCheckBox() {
        $x("//a[contains(text(), 'Металл')]").click();
        return this;
    }

    public ProductListPage checkMaterial() {
        $x("//a[@class='logo active']").scrollTo();

        List<SelenideElement> list = $$x("//div[@class='product-card product-card--mini']//li[@class='item']//span[@class='item__value']");
        for (int productCounter = 0; productCounter < list.size();productCounter++){
            list.get(productCounter).getText().contains("Металл");


        }
        return this;


    }

    public ProductListPage scrollToElementCompare() {
        $x("//div[contains(text(), ' Размер экрана') ]").scrollTo();
        return this;
    }

    public ProductListPage clickFirstCompareIconAcer() {
        $x("//div[@class='product-card product-card--mini']").hover();
        $x("//button[@class='product-card__to-compare']").should(Condition.exist).click();
        return this;
    }

    public ProductListPage clickSecondCompareIconAcer() {
        $x("//div[@class='product-card__actions']/following::button[@class='product-card__to-compare']").hover();
        $x("//div[@class='product-card__actions']/following::button[@class='product-card__to-compare']").should(Condition.exist).click();

        return this;
    }

    public String rememberProductNameFirst() {
        return $x("//div[@class='product-card__name']/a/span").getText();
    }

    public String rememberProductNameSecond() {
        return $x("//div[@class='product-card__review']/following::span[contains(text(), 'Acer')][2]").getText();
    }

    public String productPriceFirst() {
        return $x("//div[@class='prices__price']//span[@class='price']").getText();
    }

    public String productPriceSecond() {
        return $x("//div[@class='prices__price']/following::span[@class='price']").getText();
    }
}
