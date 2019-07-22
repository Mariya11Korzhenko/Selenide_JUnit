package pages;

import io.qameta.allure.Step;

public class AllItemsPage  extends ItemPage{

    @Step("click the Active filter button")
    public ActiveItemsPage clickActiveButton() {
        activeButton.click();
        return new ActiveItemsPage();
    }

    @Step("click the Completed filter button")
    public CompletedItemsPage clickCompletedButton() {
        completedButton.click();
        return new CompletedItemsPage();
    }
}
