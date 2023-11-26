package testsuite;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;
import java.util.List;

public class LoginTest extends Utility
{
    String baseUrl = "https://www.saucedemo.com/";
    @Before
    public void setUp()
    {
        openBrowser(baseUrl);
    }
    @Test
    public void userShouldloginSuccessfullyWithValidCredentials() throws InterruptedException
    {

        driver.findElement(By.cssSelector("input#user-name")).sendKeys("standard_user");
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("input#password")).sendKeys("secret_sauce");
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("input[type='submit'][id='login-button']"));
        Thread.sleep(1000);

        String expectedText = "Products";
        WebElement actualTextElement = driver.findElement(By.cssSelector(".title"));
        String actualText = actualTextElement.getText();
        Assert.assertEquals("Products", expectedText, actualText);
    }
    @Test
    public void verifyThatSixProductsAreDisplayedOnPage()
    {
        driver.findElement(By.cssSelector("input[placeholder='Username']")).sendKeys("standard_user");
        driver.findElement(By.cssSelector("input[placeholder='Password']")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector("input#login-button")).click();

        List<WebElement> productsDisplayed = driver.findElements(By.xpath("//div[@class='inventory_item']"));                       //first item inspect and take Class
        int count = productsDisplayed.size();
        System.out.println("Number of items displayed in the page : " +count);

        List<WebElement> productNames = driver.findElements(By.xpath("//div[@class='inventory_item_description']//div/a"));         //Traverse from the class
        for (WebElement products :productNames)
        {
            System.out.println( products.getText());
        }
    }
    @After
    public void tearDown()
    {
        closeBrowser();
    }
}
