package pages;

public class ActiveItemsPage extends ItemPage {
    @Override
    public ItemPage clickActiveButton() {
        return this;
    }


    public ItemPage clickCompletedButton() {
        completedButton.click();
        return new CompletedItemsPage();
    }
}
