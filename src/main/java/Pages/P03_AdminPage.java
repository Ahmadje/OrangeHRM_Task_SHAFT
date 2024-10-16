package Pages;

import com.shaft.driver.SHAFT;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class P03_AdminPage {
    SHAFT.GUI.WebDriver driver;
    static String empolyeeName;

    public P03_AdminPage(SHAFT.GUI.WebDriver driver) {
        this.driver = driver;
    }


    By userRoleDropdown = By.xpath("(//label[@class='oxd-label oxd-input-field-required'] [contains(.,'User Role') or contains(.,'Rol de Usuario')]//following::div)[1]");
    By userRoleSelect = By.xpath("//div[@role='option'] [contains(.,'Admin') or contains(.,'Administrador')]");
    By statusDropdown = By.xpath("(//label[@class='oxd-label oxd-input-field-required'] [contains(.,'Status') or contains(.,'Estatus')]//following::div)[1]");
    By statusSelect = By.xpath("//div[@role='option'] [contains(.,'Enabled') or contains(.,'Habilitado')]");
    By employeeNameInput = By.xpath("(//label[text()='Employee Name' or text()='Nombre del Empleado']//following::input)[1]");
    By getEmployeeNameEle = By.xpath("(//div[@class=\"oxd-table-cell oxd-padding-cell\"])[4]");
    By userNameInput = By.xpath("(//label[text()='Username' or text()='Nombre de usuario']//following::input)[1]");
    By passwordInput = By.xpath("(//label[text()='Password' or text()='Contraseña']//following::input)[1]");
    By confirmPasswordInput = By.xpath("//label[text()='Confirm Password' or text()='Confirme contraseña']//following::input");
    By getSaveBtn = By.xpath("//button[@type=\"submit\"] [contains(.,'Save') or contains(.,'Guardar')]");
    By getSearchBtn = By.xpath("//button[@type=\"submit\"] [contains(.,'Search') or contains(.,'Buscar')]");
    By getDeleteBtn = By.xpath("//i[@class=\"oxd-icon bi-trash\"]/parent::button");
    By confirmDeleteBtn = By.xpath("//button[@type=\"button\"] [contains(.,' Yes, Delete ')]");
    By getAddBtn = By.xpath("//i[@class=\"oxd-icon bi-plus oxd-button-icon\"]/parent::button");
    By getNumberOfRecordsEle = By.xpath("//span[contains(.,'Records ')]");


    public By employeeNameSelect(String value) {
        return By.xpath("//span[contains(.,'" + value + "')]");

    }

    public P03_AdminPage clickOnAddBtn() {
        driver.element().click(getAddBtn);
        return this;
    }

    public P03_AdminPage fillRequiredFields(String userName, String password) throws InterruptedException {
        driver.element().click(userRoleDropdown).
                and().click(userRoleSelect).
                and().click(statusDropdown).
                and().click(statusSelect).
                and().type(employeeNameInput, empolyeeName);
        Thread.sleep(2500);
        driver.element().keyPress(employeeNameInput, Keys.ARROW_DOWN).keyPress(employeeNameInput,Keys.ENTER).
//        driver.element().waitUntilPresenceOfAllElementsLocatedBy(employeeNameSelect(empolyeeName)).click(employeeNameSelect(empolyeeName)).
        and().type(userNameInput, userName).
                and().type(passwordInput, password).
                and().type(confirmPasswordInput, password);
        return this;
    }

    public void clickOnSaveBtn() throws InterruptedException {
        driver.element().click(getSaveBtn);
    }

    public String getEmployeeName() {
        return driver.element().getText(getEmployeeNameEle);
    }


    public int getNumberOfRecords() {
        int currentRecordsNumber = Integer.parseInt(driver.element().getText(getNumberOfRecordsEle).replaceAll("[^0-9]", "").trim());
        empolyeeName = getEmployeeName();
        return currentRecordsNumber;
    }


    public P03_AdminPage searchWithUserName(String userName) {
        driver.element().type(userNameInput, userName).
                and().click(getSearchBtn);
        return this;
    }

    public P03_AdminPage deleteNewUserName() {
        driver.element().click(getDeleteBtn).
                and().click(confirmDeleteBtn);
        return this;
    }
}
