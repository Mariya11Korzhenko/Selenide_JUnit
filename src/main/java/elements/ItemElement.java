package elements;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;


public class ItemElement {

    private SelenideElement element;

    public ItemElement(SelenideElement element) {
        this.element = element;
    }

    public void clickTheToggle() {
        element.find(".toggle").getWrappedElement().click();
    }

    public String getItemText() {
        return element.find(By.xpath(".//label")).getText();
    }
    public int getItenTextLength(){
        return element.find(By.xpath(".//label")).getText().length();
    }

    public void removeItem() {
        new Actions(getWebDriver()).moveToElement(element).build().perform();
        element.find(".destroy").click();
    }

    public void editItemText(String expectedText){
        element.doubleClick();
        element.find(".edit").getWrappedElement().sendKeys(expectedText);
        element.find(".edit").sendKeys(Keys.ENTER);
    }

    public void deleteItemByEdit(){
        int elementSymbolsCount =element.find(By.xpath(".//label")).getText().length();
        element.doubleClick();
        for (int i = 0; i < elementSymbolsCount; i++) {
            element.find(".edit").sendKeys(Keys.BACK_SPACE);
        }
        element.find(".edit").sendKeys(Keys.ENTER);
    }

}
