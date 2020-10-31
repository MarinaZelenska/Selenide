package citrus.fragments;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;


public class SearchFragment {

    SelenideElement popUPAdvertise = $x("//i[@class='el-dialog__close el-icon el-icon-close']");

    public SearchFragment fillSearchFieldProductName(String productNameSecondSearch) {
        $x("//div[@class='swiper-block-container promotions']").shouldBe(Condition.exist);
        $x("//input[@id='search-input']").click();
        if(popUPAdvertise.isDisplayed()){
            popUPAdvertise.click();
        }
        $x("//input[@id='search-input']").val(productNameSecondSearch);
        return this;
    }

    public SearchFragment clickSearchButton() {
        $x("//button[@class='global-search__submit']").click();
        return this;
    }

    public SearchFragment closePopUP() {
        if(popUPAdvertise.isDisplayed()){
            popUPAdvertise.click();
        }
        return this;
    }


}
