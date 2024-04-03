package Pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class AdminPage {

    private SelenideElement news = $("a[href = '/admin/news']");
    private SelenideElement partners = $("a[href = '/admin/partners']");
    private SelenideElement tours = $("a[href = '/admin/tours']");

    public void openNewsPage() {
        news.click();
    }

    public void openPartnersPage() {
        partners.click();
    }

    public void openToursPage() {
        tours.click();
    }

}
