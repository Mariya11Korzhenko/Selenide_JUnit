package TodosSpecTest;

import io.qameta.allure.Description;
import org.junit.Before;
import org.junit.Test;
import pages.AllItemsPage;

import java.util.Arrays;

import static com.codeborne.selenide.Selenide.*;


public class CheckToggleAllAndActiveFilterFunctionality extends TestBase {


    @Before
    public void beforeMethod() {
        open("http://todomvc.com/examples/react/#/", AllItemsPage.class)
                .addItems("Item", 3);
    }

    @Description("check the Completed and Active filters functionality ")
    @Test
    public void checkTheCompletedAndActiveFilterFunctionality() {
        new AllItemsPage()
                .markAsCompleted(2)
                .clickCompletedButton()
                .itemsCompletedAreEqual("Item", 2)
                .itemListSize(2)
                .markAsUncompletedByOrder(1)
                .clickActiveButton()
                .itemsAreEqual(Arrays.asList("Item1", "Item2"));

    }

    @Description("check the toggle all button functionality")
    @Test
    public void checkToggleAllButtonFunctionality() {
        new AllItemsPage().
                clickToggleAll()
                .itemsAreEqual("Item", 3)
                .getItemsLeftIsEqual(0)
                .checkClearCompletedButtonIsDisplayed()
                .clickActiveButton()
                .clickToggleAll()
                .itemsAreEqual("Item", 3);
    }
}
