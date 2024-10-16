package Pages;

import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;

public class P02_DashboardPage {
    SHAFT.GUI.WebDriver driver;

    public P02_DashboardPage(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }


    By getUserDropDown = By.cssSelector(".oxd-userdropdown-tab");
    By getAdminBtn = By.xpath("//ul[@class=\"oxd-main-menu\"]/li [contains(.,'Admin')]");


    public P02_DashboardPage userAccount_Verifications(){
        driver.verifyThat().element(getUserDropDown).exists().perform();
        return this;
    }

    public P03_AdminPage clickOnAdminBtn() {
        driver.element().click(getAdminBtn);
        return new P03_AdminPage(driver);
    }
}
