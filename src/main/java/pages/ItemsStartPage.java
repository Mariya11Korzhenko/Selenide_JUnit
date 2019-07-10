package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
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

    @Step
    public void addItem(String itemName) {
        newToDo.setValue(itemName).pressEnter();
    }
    @Step
    public List<SelenideElement> getAllEditElementList(){
        return editElements;
    }
    @Step
    public List<SelenideElement> getAllItemsList(){
        return listOfDisplayedElements;
    }
    @Step
    public boolean containsItem(int count, String expected) {
        String itemText = listOfDisplayedElements.get(count).getText();
        boolean elementsAreEqual = true;
        if (!itemText.equals(expected)) {
            elementsAreEqual = false;
        }
        return elementsAreEqual;
    }
    @Step
    public void clickActiveButton() {
        activeButton.click();
    }
    @Step
    public void clickTogleAll() {
        toggleAll.click();
    }
    @Step
    public void clickAllButton() {
        allButton.click();
    }
    @Step
    public void clickClearCompletedButton() {
        clearCompletedButton.click();
    }
    @Step
    public boolean checkClearCompletedButtonIsDisplayed() {
        boolean isDisplayed = false;
        if (clearCompletedButton.isDisplayed()) {
            isDisplayed = true;
        }
        return isDisplayed;
    }
    @Step
    public void clickCompletedButton() {
        completedButton.click();
    }
    @Step
    public void clickTogle(int togleNumber) {
        listOfTogles.get(togleNumber).getWrappedElement().click();
    }
    @Step
    public String getElementsLeft() {
        return elementsLeftLabel.getText();
    }
    @Step
    public boolean getCompletedItems(int count, String expected) {
        String completedItemText = completedItemsLabelList.get(count).getText();
        boolean elementsAreEqual = true;
        if (!completedItemText.equals(expected)) {
            elementsAreEqual = false;
        }
        return elementsAreEqual;
    }
    @Step
    public List<String> getAllItemsTextList() {
        List<String> itemsTextList = new ArrayList<String>();
        for (int i = 0; i < listOfDisplayedElements.size(); i++) {
            itemsTextList.add(listOfDisplayedElements.get(i).getText());
        }
        return itemsTextList;
    }
    @Step
    public void removeItem(int count) {
        WebDriver driver = getWebDriver();
        for (int i = 0; i < count; i++) {
            new Actions(driver).moveToElement(listOfDisplayedElements.get(0)).build().perform();
            (listOfRemoveX.get(0)).click();
        }
    }
}


