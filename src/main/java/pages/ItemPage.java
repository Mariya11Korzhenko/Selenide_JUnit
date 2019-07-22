package pages;

import com.codeborne.selenide.SelenideElement;
import elements.ItemElement;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public abstract class ItemPage {

    private SelenideElement newToDo = $(".new-todo");
    SelenideElement activeButton = $(By.xpath(".//a[text()='Active']"));
    SelenideElement allButton = $(By.xpath(".//a[text()='All']"));
    public SelenideElement completedButton = $(By.xpath(".//a[text()='Completed']"));
    private SelenideElement clearCompletedButton = $(By.xpath(".//button[@class='clear-completed']"));
    private SelenideElement elementsLeftLabel = $(By.xpath(".//span[@class='todo-count']"));
    private SelenideElement toggleAll = $(By.xpath(" .//label[@for='toggle-all']"));


    //clickButtonMethods
    @Step("click toggle all on the All Items Page")
    public ItemPage clickToggleAll() {
        toggleAll.click();
        return this;
    }
    @Step("click the Active filter button")
    public abstract ItemPage clickActiveButton() ;
    @Step("click the Active filter button")
    public abstract ItemPage clickCompletedButton();

    @Step
    public ItemPage clickClearCompletedButton() {
        clearCompletedButton.click();
    return this;}

    @Step("mark first {0} items as completed")
    public ItemPage markAsCompleted(int quantity) {

        for (int i = 0; i < quantity; i++) {
            getAllItemList().get(i).clickTheToggle();
        }
        return this;
    }

    @Step("clear all items from list using toggle all button")
    public ItemPage clearAllItems() {
        clickToggleAll();
        clickClearCompletedButton();
        return this;
    }

    //addItemMethods
    @Step("add item {0}")
    public ItemPage addItem(String itemName) {
        newToDo.setValue(itemName).pressEnter();
        return this;
    }

    @Step("add items from {0}0 to {0}{1}")
    public ItemPage addItems(String body, int quantity) {
        for (int i = 0; i < quantity; i++) {
            addItem(body + i);
        }
        return this;
    }

    @Step("add item that consists from {0} that concatenated to have size {1}")

    public ItemPage addItemAllItemPageComposite(String part, int maxSize) {
        String currentItem = "";
        while (currentItem.length() < maxSize) {
            currentItem += part;
        }
        addItem(currentItem);
        return this;
    }

    //ElementIsDisplayedMethods
    @Step("the label Items Left is equal to {0}")
    public ItemPage getItemsLeftIsEqual(int expected) {
        int value = Integer.parseInt(elementsLeftLabel.getText().split(" ")[0]);
        Assert.assertEquals(value, expected);
        return this;
    }

    @Step("Clear Completed Button is displayed")
    public ItemPage checkClearCompletedButtonIsDisplayed() {
        boolean isDisplayed = false;
        if (clearCompletedButton.isDisplayed()) {
            isDisplayed = true;
        }
        Assert.assertTrue(isDisplayed);
        return this;
    }

    @Step("displayed items list contains {0}0 to {0}{1} elements")
    public ItemPage itemsAreEqual(String body, int quantity) {
        List<String> expectedList = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            expectedList.add(body + i);
        }
        for (int i = 0; i < expectedList.size(); i++) {
            boolean elementsAreEqual = getAllItemList().get(i).getItemText().equals(expectedList.get(i));
            Assert.assertTrue(elementsAreEqual);
        }
        return this;
    }

    @Step("displayed items text is list of items that contains {0}{1} to {0}{2} elements")
    public ItemPage itemsAreEqual(String body, int numberFrom, int NumberTo) {
        List<String> expectedList = new ArrayList<>();
        for (int i = numberFrom; i < NumberTo; i++) {
            expectedList.add(body + i);
        }
        for (int i = 0; i < expectedList.size(); i++) {
            boolean elementsAreEqual = getAllItemList().get(i).getItemText().equals(expectedList.get(i));
            Assert.assertTrue(elementsAreEqual);
        }
        return this;
    }

    @Step("displayed items text is {0} list of items")
    public ItemPage itemsAreEqual(List<String> expectedList) {
        for (int i = 0; i < expectedList.size(); i++) {
            boolean elementsAreEqual = getAllItemList().get(i).getItemText().equals(expectedList.get(i));
            Assert.assertTrue(elementsAreEqual);
        }
        return this;

    }

    @Step("item with the text {0} is displayed in the list {1} times")
    public ItemPage itemIsDisplayedInTheList(String item, int times) {
        int count = 0;
        for (int i = 0; i < getAllItemList().size(); i++) {
            if (getAllItemList().get(i).getItemText().equals(item)) {
                count++;
            }
        }
        Assert.assertEquals(times, count);
        return this;
    }

    @Step("displayed completed items is list of items that contains {0}0 to {0}{1} elements")
    public ItemPage itemsCompletedAreEqual(String body, int quantity) {
        List<String> expectedList = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            expectedList.add(body + i);
        }
        for (int i = 0; i < expectedList.size(); i++) {
            boolean elementsAreEqual = getAllCompletedItemList().get(i).getItemText().equals(expectedList.get(i));
            Assert.assertTrue(elementsAreEqual);
        }
        return this;
    }
    @Step("edit item by increasing item {0} text size to {1}")
    public ItemPage editItem(int ItemNumber, int ExpectedLength) {
        int itemActualLength = getAllItemList().get(ItemNumber).getItemText().length();
        String expectedAddingToItem = "";

        while (expectedAddingToItem.length() < ExpectedLength - itemActualLength) {
            expectedAddingToItem += "a @$%";
        }
        getAllItemList().get(ItemNumber).editItemText(expectedAddingToItem);
        return this;
    }
    @Step("completed items list size is {0}")
    public ItemPage itemListSize(int expectedListSize){
        Assert.assertEquals(getAllCompletedItemList().size(),expectedListSize);
        return this;
    }
    @Step("mark item {0} is uncompleted")
    public ItemPage markAsUncompletedByOrder(int orderNumber){
        getAllCompletedItemList().get(orderNumber).clickTheToggle();
        return this;
    }

    @Step("item {0} text length is {1}")
    public ItemPage itemTextLengthIsExpected(int itemNumber, int expectedLength) {
        Assert.assertEquals(getAllItemList().get(itemNumber).getItenTextLength(), expectedLength);
        return this;
    }

    //deleteMethods
    @Step("delete first {0} items")
    public ItemPage deleteItems(int quantity) {
        for (int i = 0; i < quantity; i++) {
            getAllItemList().get(0).removeItem();
        }
        return this;
    }

    @Step("delete Item number {0} by it editind")
    public ItemPage deleteItemByEditing(int itemNumber) {
        getAllItemList().get(itemNumber).deleteItemByEdit();
        return this;
    }


    //staticMethodsWithLists
    static List<ItemElement> getAllItemList() {
        List<ItemElement> itemElementList = new ArrayList<>();
        List<SelenideElement> selenideElementList = $$(By.xpath(".//div[@class='view']/parent::li"));

        for (int i = 0; i < selenideElementList.size(); i++) {
            itemElementList.add(new ItemElement(selenideElementList.get(i)));
        }
        return itemElementList;
    }

    static List<ItemElement> getAllCompletedItemList() {
        List<ItemElement> itemElementList = new ArrayList<>();
        List<SelenideElement> selenideElementList = $$(By.xpath(".//li[@class='completed']/div"));

        for (int i = 0; i < selenideElementList.size(); i++) {
            itemElementList.add(new ItemElement(selenideElementList.get(i)));
        }
        return itemElementList;
    }

}


