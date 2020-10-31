package citrus;

import citrus.pages.ComprassionPage;
import citrus.pages.Homepage;
import citrus.pages.ProductListPage;
import citrus.pages.ProductPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.clearBrowserLocalStorage;
import static com.codeborne.selenide.Selenide.open;

public class CitrusComprassion {

    Homepage homepage;
    ProductListPage productList;
    ProductPage productPage;
    ComprassionPage comprassionPage;


    @BeforeClass
    public void startUp() {
        Configuration.baseUrl = "https://www.citrus.ua/";
        open("");
        homepage = new Homepage();
        productList = new ProductListPage();
        comprassionPage = new ComprassionPage();
    }


    @BeforeMethod
    public void CleanBasket() {
        clearBrowserLocalStorage();
    }


    @Test
    public void compareTest(){
        homepage.hoverMenuLine("Ноутбуки, планшеты, МФУ")
                .clickLinkInMenu("Acer");
        productList.scrollToElementCompare();
        productList.clickFirstCompareIconAcer();
        productList.clickSecondCompareIconAcer();
        String productNameFirst = productList.rememberProductNameFirst();
        String productNameSecond = productList.rememberProductNameSecond();
        String productPriceFirst = productList.productPriceFirst();
        String productPriceSecond = productList.productPriceSecond();
        productList.clickCompareIconInHeader();
        comprassionPage.countProduct().shouldHaveSize(2);
        comprassionPage.titleOnComparePage().get(0).shouldHave(Condition.text(productNameFirst));
        comprassionPage.titleOnComparePage().get(1).shouldHave(Condition.text(productNameSecond));
        comprassionPage.priceComparePage().get(0).shouldHave(Condition.text(productPriceFirst));
        comprassionPage.priceComparePage().get(2).shouldHave(Condition.text(productPriceSecond));
        comprassionPage.getCompareFragment().addNewProductToComparison();
        String productNameThird = comprassionPage.rememberProductNameThird();
        String productPriceThird = comprassionPage.productPriceThird();
        comprassionPage.getCompareFragment().buttonAdd();
        comprassionPage.countProduct().shouldHaveSize(3);
        comprassionPage.titleOnComparePage().get(0).shouldHave(Condition.text(productNameFirst));
        comprassionPage.titleOnComparePage().get(1).shouldHave(Condition.text(productNameSecond));
        comprassionPage.titleOnComparePage().get(2).shouldHave(Condition.text(productNameThird));
        comprassionPage.priceComparePage().get(0).shouldHave(Condition.text(productPriceFirst));
        comprassionPage.priceComparePage().get(2).shouldHave(Condition.text(productPriceSecond));
        comprassionPage.priceComparePage().get(4).shouldHave(Condition.text(productPriceThird));





    }
}
