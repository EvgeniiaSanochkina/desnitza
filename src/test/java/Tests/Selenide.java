package Tests;

import Data.DataHelper;
import Pages.MainPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byTagName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class Selenide {
    String title = DataHelper.generateTile();
    String description = DataHelper.generateDescription();
    String link = DataHelper.generateLink();
    String partnerName = DataHelper.generatePartnerName();

    String filePath = "C:\\Users\\s_jan\\IdeaProjects\\admin_excursion\\src\\test\\resources\\1.jpg";
    String filePath2 = "C:\\Users\\s_jan\\IdeaProjects\\admin_excursion\\src\\test\\resources\\2.jpg";

    private final SelenideElement addButton = $(byText("Добавить"));
    private final SelenideElement addCover = $("input[name = 'Обложка']");
    private final SelenideElement addMorePhotos = $("input[name = 'Загрузить ещё фото']");
    private final SelenideElement addLink = $$(byTagName("input")).get(2);
    private final SelenideElement addTitle = $$(byTagName("input")).get(3);
    private final SelenideElement addDescription = $(byTagName("textarea"));
    private final SelenideElement saveButton = $(byText("Сохранить"));
    private final SelenideElement addFile = $("input[type = 'file']");
    private final SelenideElement addName = $$(byTagName("input")).get(1);
    private final SelenideElement text = $("input[type = 'text']");
    @Test
    public void testPostNewsSelenide() {
        var AdminPage = new MainPage().openAdminPage();
        AdminPage.openNewsPage();
        addButton.click();
        addCover.sendKeys(filePath);
        addMorePhotos.sendKeys(filePath2);
        addLink.sendKeys(link);
        addTitle.sendKeys(title);
        addDescription.sendKeys(description);
        saveButton.click();
        $(byText(link)).shouldBe(visible);
        $(byText(title)).shouldBe(visible);
        $(byText(description)).shouldBe(visible);
    }

    @Test
    public void testPostPartnersSelenide() {
        var AdminPage = new MainPage().openAdminPage();
        AdminPage.openPartnersPage();
        addButton.click();
        addFile.sendKeys(filePath);
        addName.sendKeys(partnerName);
        addLink.sendKeys(link);
        saveButton.click();
        $(byText(partnerName)).shouldBe(visible);
        // страница выдает 500 ошибку, в админке работает раз через раз
    }

    @org.junit.jupiter.api.Test
    public void testNewExcursionSelenide() {
        var AdminPage = new MainPage().openAdminPage();
        AdminPage.openToursPage();
        addButton.click();
        addFile.sendKeys(filePath);
        addMorePhotos.sendKeys(filePath2);
        text.sendKeys(title);
        addDescription.sendKeys(description);
        saveButton.click();
        $(byText(title)).shouldBe(visible);
        $(byText(description)).shouldBe(visible);
    }
}
