package tests;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;

public class GoogleSheetsTestsAutomation extends BaseTest {

    @Test(priority = 1)
    public void loginToGoogleAccount() {
        driver.get("https://accounts.google.com/signin");

        // Enter email
        WebElement emailInput = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(By.id("identifierId")));
        emailInput.sendKeys("ravina.test.automationqa@gmail.com");
        driver.findElement(By.id("identifierNext")).click();

        // Enter password
        WebElement passwordInput = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(By.name("password")));
        passwordInput.sendKeys("Qwerty@123");
        driver.findElement(By.id("passwordNext")).click();

        // Wait until redirected to Google account page or apps
        new WebDriverWait(driver, Duration.ofSeconds(20))
        .until(ExpectedConditions.or(
            ExpectedConditions.urlContains("myaccount.google.com"),
            ExpectedConditions.urlContains("accounts.google.com")
        ));
    }

    @Test(priority = 2)
    public void addEditDeleteDataInCell() {
        WebElement cell = driver.findElement(By.xpath("//div[@data-row='1'][@data-col='1']"));
        cell.click();
        new Actions(driver).sendKeys("TestData").perform();
        new Actions(driver).doubleClick().sendKeys("Edit").perform();
        new Actions(driver).sendKeys(Keys.BACK_SPACE).perform();
    }

    @Test(priority = 3)
    public void applyBoldItalicColor() {
        driver.findElement(By.xpath("//div[@aria-label='Bold (Ctrl+B)']")).click();
        driver.findElement(By.xpath("//div[@aria-label='Italic (Ctrl+I)']")).click();
        driver.findElement(By.xpath("//div[@aria-label='Fill color']")).click();
        driver.findElement(By.xpath("//div[@aria-label='Light yellow']")).click();
    }

    @Test(priority = 4)
    public void useSumFormula() {
        WebElement cell = driver.findElement(By.xpath("//div[@data-row='2'][@data-col='1']"));
        cell.click();
        new Actions(driver).sendKeys("=SUM(1,2)").sendKeys(Keys.ENTER).perform();
    }

    @Test(priority = 5)
    public void sortAndFilterData() {
        driver.findElement(By.xpath("//div[text()='Data']")).click();
        driver.findElement(By.xpath("//div[text()='Sort sheet by column A (A to Z)']")).click();
        driver.findElement(By.xpath("//div[text()='Data']")).click();
        driver.findElement(By.xpath("//div[text()='Create a filter']")).click();
    }

    @Test(priority = 6)
    public void shareSheetTest() {
        driver.findElement(By.xpath("//span[text()='Share']")).click();
        driver.findElement(By.xpath("//input[@type='email']")).sendKeys("exampletest@gmail.com");
        driver.findElement(By.xpath("//span[text()='Done']")).click();
    }

    @Test(priority = 7)
    public void importLargeDataset() {
        driver.findElement(By.xpath("//div[text()='File']")).click();
        driver.findElement(By.xpath("//div[text()='Import']")).click();
        // Upload CSV (handle native dialog using Robot or AutoIT externally)
    }

    @Test(priority = 8)
    public void resizeColumnsRows() {
        WebElement col = driver.findElement(By.xpath("//div[@data-column-index='1']"));
        new Actions(driver).clickAndHold(col).moveByOffset(100, 0).release().perform();
        WebElement row = driver.findElement(By.xpath("//div[@data-row-index='1']"));
        new Actions(driver).clickAndHold(row).moveByOffset(0, 50).release().perform();
    }

    @Test(priority = 9)
    public void moveColumnsRows() {
        WebElement col = driver.findElement(By.xpath("//div[@data-column-index='1']"));
        new Actions(driver).dragAndDropBy(col, 100, 0).perform();
    }

    @Test(priority = 10)
    public void freezeTopRowColumn() {
        driver.findElement(By.xpath("//div[text()='View']")).click();
        driver.findElement(By.xpath("//div[text()='Freeze']")).click();
        driver.findElement(By.xpath("//div[text()='1 row']")).click();

        driver.findElement(By.xpath("//div[text()='View']")).click();
        driver.findElement(By.xpath("//div[text()='Freeze']")).click();
        driver.findElement(By.xpath("//div[text()='1 column']")).click();
    }
}
