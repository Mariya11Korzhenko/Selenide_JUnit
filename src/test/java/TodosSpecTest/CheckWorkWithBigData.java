package TodosSpecTest;

import io.qameta.allure.Description;
import org.junit.Before;
import org.junit.Test;
import pages.AllItemsPage;

import static com.codeborne.selenide.Selenide.*;


public class CheckWorkWithBigData extends TestBase {

    private static final int totalItems = 100;
    private static final int deleteItems = 30;
    private static final int completeItems = 50;


    @Before
    public void beforeMethod() {
        open("http://todomvc.com/examples/react/#/", AllItemsPage.class)
                .addItems("Item", totalItems);
    }

    @Description("check that application is able to work with big list of data")
    @Test
    public void checkTheBigDataWork() {
        new AllItemsPage()
                .deleteItems(deleteItems)
                .markAsCompleted(completeItems)
                .clickActiveButton()
                .itemsAreEqual("Item", deleteItems + completeItems, totalItems)
                .getItemsLeftIsEqual(totalItems - completeItems - deleteItems);
    }
}
