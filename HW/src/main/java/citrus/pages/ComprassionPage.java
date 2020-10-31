package citrus.pages;

import citrus.fragments.CompareFragment;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class ComprassionPage {

    CompareFragment compareFragment = new CompareFragment();

    ElementsCollection countProduct = $$x("//div[@class='relative']");


    public ElementsCollection countProduct() {
        return countProduct;
    }

    public ElementsCollection titleOnComparePage() {
       return  $$x("//div[@class='title-itm']/h5");
    }

    public ElementsCollection priceComparePage() {
       return  $$x("//div[@class='base-price']/span");
    }


    public String rememberProductNameThird() {
        return $$x("//p[@class='product-name']").first().getText();
    }

    public String productPriceThird() {
        return $$x("//label[@class='el-checkbox']//span[@class='price-number']").first().getText();
    }

    public CompareFragment getCompareFragment() {
        return compareFragment;
    }


}
