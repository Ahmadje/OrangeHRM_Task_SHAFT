package Tests;

import Pages.P01_LoginPage;
import Pages.P02_DashboardPage;
import Pages.P03_AdminPage;
import com.shaft.driver.SHAFT;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class T01_e2e_Scenario {
    SHAFT.GUI.WebDriver driver;
    SHAFT.TestData.JSON testData;
    public static int recordNumber, currentRecordsNumber;

    @Test
    public void test() throws InterruptedException {
        driver.browser().navigateToURL("https://opensource-demo.orangehrmlive.com/");
        new P01_LoginPage(driver).enterValidInputs(testData.getTestData("username"), testData.getTestData("password")).
                clickOnLoginBtn().
                userAccount_Verifications().
                clickOnAdminBtn();
        recordNumber = new P03_AdminPage(driver).getNumberOfRecords();
        System.out.println("Number of Records found: " + recordNumber);
        new P03_AdminPage(driver).clickOnAddBtn().
                fillRequiredFields(testData.getTestData("newUserName"), testData.getTestData("newPassword")).
                clickOnSaveBtn();
        currentRecordsNumber = new P03_AdminPage(driver).getNumberOfRecords();
        System.out.println("Number of Records found: " + currentRecordsNumber);
        SHAFT.Validations.assertThat().object(currentRecordsNumber - recordNumber).isEqualTo(1).perform();
        new P03_AdminPage(driver).searchWithUserName(testData.getTestData("newUserName")).
                deleteNewUserName();
        new P02_DashboardPage(driver).clickOnAdminBtn();
        currentRecordsNumber = new P03_AdminPage(driver).getNumberOfRecords();
        System.out.println("Number of Records found: " + currentRecordsNumber);
        SHAFT.Validations.assertThat().object(currentRecordsNumber).isEqualTo(recordNumber).perform();
    }

    @BeforeClass
    public void beforeClass() {
        driver = new SHAFT.GUI.WebDriver();
        testData = new SHAFT.TestData.JSON("simpleJSON.json");
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        driver.quit();
    }
}
