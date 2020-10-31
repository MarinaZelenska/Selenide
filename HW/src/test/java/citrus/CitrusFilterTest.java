package citrus;

import citrus.pages.Homepage;
import citrus.pages.ProductListPage;
import citrus.pages.ProductPage;
import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.clearBrowserLocalStorage;
import static com.codeborne.selenide.Selenide.open;

public class CitrusFilterTest {
    Homepage homepage;
    ProductListPage productList;
    ProductPage productPage;


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
    public void filterPriceTest() throws Exception {
        homepage.hoverMenuLine("Смартфоны")
                .clickLinkInMenu("Samsung");
        productList.addMinPrice();
        productList.addMaxPice();
        productList.clickForDownloadProductSelectedFilter();
        productList.checkContainsTextTitle("Samsung");
        productList.checkDiapasonPrice();
    }


    @Test
    public void filterMemorySizeTest() {
        homepage.hoverMenuLine("Смартфоны")
                .clickLinkInMenu("Xiaomi");
        productList.scrollToElementMemory();
        productList.selectFirstMemorySize();
        productList.selectSecondMemorySize();
        productList.checkContainsTextTitle("Xiaomi");
        productList.checkDiapasonMemory();
    }


    @Test
    public void filterChooseMaterialTest() {
        homepage.hoverMenuLine("Смартфоны")
                .clickLinkInMenu("Xiaomi");
        productList.scrollToElementMaterial();
        productList.addMaterialCheckBox();
        productList.checkContainsTextTitle("Xiaomi");
        productList.checkMaterial();
    }


}
