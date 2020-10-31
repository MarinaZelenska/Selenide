package citrus;

import citrus.pages.Homepage;
import citrus.pages.ProductListPage;
import citrus.pages.ProductPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.*;

public class CitrusBasketTest {
    Homepage homepage;
    ProductListPage productList;
    ProductPage productPage;
    String productName = "Apple iPhone 11 128Gb Black (MWM02)";
    String productNameSecondSearch = "Apple iPhone 11";
    String productNameSecond = "Apple iPhone 11 256Gb Black (MWM72)";
    String searchByName = "Apple iPhone";
    String Url = "https://www.citrus.ua/search?query=Apple%20Iphone";

    @BeforeClass
    public void startUp() {
        Configuration.baseUrl = "https://www.citrus.ua/";
        open("");
        homepage = new Homepage();
        productList = new ProductListPage();
        productPage = new ProductPage();
    }

    @BeforeMethod
    public void CleanBasket() {
        clearBrowserLocalStorage();
    }

    @Test
    public void addProductToBasketViaMenu() {
        homepage.hoverMenuLine("Смартфоны")
                .clickLinkInMenu("Apple");
        productList.clickOnProductByName(productName);
        String productPrice = productPage.getProductPrice();
        productPage.clickByButton();
        productPage.getBasketFragment().getBasket().shouldBe(Condition.visible);
        productPage.getBasketFragment().getProductNameFromBasket().shouldHaveSize(1);
        productPage.getBasketFragment().getProductNameFromBasket().get(0).shouldHave(Condition.text(productName));
        productPage.getBasketFragment().getBasketProductPrice().get(0).shouldHave(Condition.text(productPrice));

    }


    @Test
    public void addProductToBasketViaSearch() {
        homepage.getSearchFragment().fillSearchFieldProductName(productNameSecondSearch)
                .clickSearchButton();
        String priceSecond = productList.getProductPriceOnList();
        productList.clickOnBasketIcon();
        productPage.getBasketFragment().getBasket().shouldBe(Condition.visible);
        productPage.getBasketFragment().getProductNameFromBasket().shouldHaveSize(1);
        productPage.getBasketFragment().getProductNameFromBasket().get(0).shouldHave(Condition.text(productNameSecond));
        productPage.getBasketFragment().getBasketProductPrice().get(0).shouldHave(Condition.text(priceSecond));


    }

    @Test
    public void addTwoProductToBasket() throws IOException {
        homepage.getSearchFragment().fillSearchFieldProductName(searchByName)
                .clickSearchButton();
        String findFirstProductName = homepage.findFirstProductName();
        String productPriceFirst = productList.getFirstProductPriceOnList();
        productList.clickFirstBasketIcon();
        download(Url);
        String findSecondProductName = homepage.findSecondProductName();
        String productPriceSecond = productList.getSecondProductPriceOnList();
        productList.clickSecondBasketIcon();
        productPage.countProductNameOnBasket().shouldHaveSize(2);
        productPage.getBasketFragment().getFirstProductNameFromBasket().shouldHave(Condition.text(findFirstProductName));
        productPage.getBasketFragment().getProductNameFromBasket().get(1).shouldHave(Condition.text(findSecondProductName));
        productPage.getBasketFragment().getBasketProductPrice().get(0).shouldHave(Condition.text(productPriceFirst));
        productPage.getBasketFragment().getBasketProductPrice().get(1).shouldHave(Condition.text(productPriceSecond));


    }

    @Test
    public void addTwoProductToBasketViaCompare() {
        homepage.getSearchFragment().fillSearchFieldProductName(searchByName)
                .clickSearchButton();
        String findFirstProductName = homepage.findFirstProductName();
        String productPriceFirst = productList.getFirstProductPriceOnList();
        productList.clickFirstCompareIcon();
        String findSecondProductName = homepage.findSecondProductName();
        String productPriceSecond = productList.getSecondProductPriceOnList();
        productList.clickSecondCompareIcon();
        productList.clickCompareIconInHeader();
        productPage.clickFirstBasketIcon();
        productPage.closeWindowBasket();
        productPage.clickSecondBasketIcon();
        int countPrice =  Integer.parseInt(productPriceFirst)  + Integer.parseInt(productPriceSecond);
        productPage.countProductNameOnBasket().shouldHaveSize(2);
        productPage.getBasketFragment().getFirstProductNameFromBasket().shouldHave(Condition.text(findFirstProductName));
        productPage.getBasketFragment().getProductNameFromBasket().get(1).shouldHave(Condition.text(findSecondProductName));
        productPage.getBasketFragment().getBasketProductPrice().get(0).shouldHave(Condition.text(productPriceFirst));
        productPage.getBasketFragment().getBasketProductPrice().get(1).shouldHave(Condition.text(productPriceSecond));
        productPage.getTotalPrice().matches(String.valueOf(countPrice));

    }




}
