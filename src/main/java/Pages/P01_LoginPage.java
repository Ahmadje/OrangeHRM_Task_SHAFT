package Pages;

import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;

public class P01_LoginPage {
    SHAFT.GUI.WebDriver driver;

    public P01_LoginPage(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }


    By getUserNameInput = By.xpath("//input[@name='username']");
    By getPasswordInput = By.xpath("//input[@name='password']");
    By getLoginBtn = By.xpath("//button[@type='submit']");

    public P01_LoginPage enterValidInputs(String userName, String password) {
        driver.element().type(getUserNameInput,userName).
                and().type(getPasswordInput,password);
        return this;
    }

    public P02_DashboardPage clickOnLoginBtn() {
        driver.element().click(getLoginBtn);
        return new P02_DashboardPage(driver);
    }
}
