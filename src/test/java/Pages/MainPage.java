package Pages;

import Data.DataHelper;
import com.codeborne.selenide.SelenideElement;
import io.github.cdimascio.dotenv.Dotenv;

import static com.codeborne.selenide.Selenide.*;

public class MainPage {
    static Dotenv dotenv = Dotenv.load();
    public static String userName = dotenv.get("USER");
    public static String passwordName = dotenv.get("PASSWORD");

    private final SelenideElement login = $("input[type = 'text']");
    private final SelenideElement password = $("input[type = 'password']");
    private final SelenideElement enter = $$("button").get(0);

    public AdminPage openAdminPage() {
        open(DataHelper.baseURLAdmin);
        login.sendKeys(userName);
        password.sendKeys(passwordName);
        enter.click();
        return new AdminPage();
    }

}
