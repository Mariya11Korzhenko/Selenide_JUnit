package TodosSpecTest;

import io.qameta.allure.Description;
import org.junit.Before;
import org.junit.Test;
import pages.AllItemsPage;

import static com.codeborne.selenide.Selenide.*;


public class CheckTheEditItemFunctionality extends TestBase {
    @Before
    public void beforeMethod() {
        open("http://todomvc.com/examples/react/#/", AllItemsPage.class)
                .addItem("@#$%^")
                .addItemAllItemPageComposite("a a", 30)
                .itemTextLengthIsExpected(1, 30)
                .addItemAllItemPageComposite("a @$%", 70)
                .itemTextLengthIsExpected(2, 70);
    }

    @Description("check the Edit and remove by Editing functionality")
    @Test
    public void checkTheEditFunctionality() {
        new AllItemsPage()
                .deleteItemByEditing(0)
                .itemIsDisplayedInTheList("@#$%^",0)
                .addItemAllItemPageComposite("a a", 30)
                .addItemAllItemPageComposite("a a", 30)
                .itemIsDisplayedInTheList("a aa aa aa aa aa aa aa aa aa a", 3)
                .editItem(1, 300)
                .itemTextLengthIsExpected(1, 300)
                .clickActiveButton();
    }

}
