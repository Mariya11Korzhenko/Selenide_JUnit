package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
public class ItemsStartPage {



    private List<SelenideElement> listOfDisplayedElements = $$(By.xpath(".//ul[@class='todo-list']//label"));
    private SelenideElement newToDo = $(By.cssSelector(".new-todo"));
    private SelenideElement activeButton = $(By.xpath(".//a[text()='Active']"));
    private SelenideElement allButton = $(By.xpath(".//a[text()='All']"));
    private List<SelenideElement> listOfTogles = $$(By.xpath(".//input[@class='toggle']"));
    private SelenideElement completedButton = $(By.xpath(".//a[text()='Completed']"));
    private SelenideElement clearCompletedButton = $(By.xpath(".//button[@class='clear-completed']"));
    private SelenideElement elementsLeftLabel = $(By.xpath(".//span[@class='todo-count']"));
    private List<SelenideElement> listOfRemoveX = $$(By.xpath(".//button[@class='destroy']"));
    private List<SelenideElement> completedItemsLabelList = $$(By.xpath(".//li[@class='completed']/div"));
    private SelenideElement toggleAll = $(By.xpath(" .//label[@for='toggle-all']"));
    private List<SelenideElement> editElements = $$(By.xpath(".//input[@class='edit']"));


    public void addItem(String itemName) {
        newToDo.setValue(itemName).pressEnter();
    }

    public List<SelenideElement> getAllEditElementList(){
        return editElements;
    }

    public List<SelenideElement> getAllItemsList(){
        return listOfDisplayedElements;
    }

    public boolean containsItem(int count, String expected) {
        String itemText = listOfDisplayedElements.get(count).getText();
        boolean elementsAreEqual = true;
        if (!itemText.equals(expected)) {
            elementsAreEqual = false;
        }
        return elementsAreEqual;
    }

    public void clickActiveButton() {
        activeButton.click();
    }

    public void clickTogleAll() {
        toggleAll.click();
    }

    public void clickAllButton() {
        allButton.click();
    }

    public void clickClearCompletedButton() {
        clearCompletedButton.click();
    }

    public boolean checkClearCompletedButtonIsDisplayed() {
        boolean isDisplayed = false;
        if (clearCompletedButton.isDisplayed()) {
            isDisplayed = true;
        }
        return isDisplayed;
    }

    public void clickCompletedButton() {
        completedButton.click();
    }

    public void clickTogle(int togleNumber) {
        listOfTogles.get(togleNumber).getWrappedElement().click();
    }

    public String getElementsLeft() {
        return elementsLeftLabel.getText();
    }

    public boolean getCompletedItems(int count, String expected) {
        String completedItemText = completedItemsLabelList.get(count).getText();
        boolean elementsAreEqual = true;
        if (!completedItemText.equals(expected)) {
            elementsAreEqual = false;
        }
        return elementsAreEqual;
    }

    public List<String> getAllItemsTextList() {
        List<String> itemsTextList = new ArrayList<String>();
        for (int i = 0; i < listOfDisplayedElements.size(); i++) {
            itemsTextList.add(listOfDisplayedElements.get(i).getText());
        }
        return itemsTextList;
    }

    public void removeItem(int count) {
        WebDriver driver = getWebDriver();
        for (int i = 0; i < count; i++) {
            new Actions(driver).moveToElement(listOfDisplayedElements.get(0)).build().perform();
            (listOfRemoveX.get(0)).click();
        }
    }
}


