package citrus.fragments;

import citrus.pages.ComprassionPage;

import static com.codeborne.selenide.Selenide.$x;

public class CompareFragment {


    public CompareFragment buttonAdd() {
        $x("//button[contains(text(), 'Добавить')]").click();
        return this;
    }

    public CompareFragment addNewProductToComparison() {
        $x("//span[@class='img-container flex-column']").click();
        $x("//label[@class='el-checkbox']").click();
        return this;
    }
}
