package ohtu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
import java.util.Random;

public class Tester {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        goodLogin();
        failedLogin();
        noSuchUser();
        goodNewUser();
    }

    private static void goodLogin(){
        WebDriver driver = new ChromeDriver();

        driver.get("http://localhost:4567");

        sleep(1);


        clickLinkWithText("login", driver);

        sleep(1);

        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));

        sleep(1);
        element.submit();

        sleep(2);

        driver.quit();
    }

    private static void failedLogin(){
        WebDriver driver = new ChromeDriver();

        driver.get("http://localhost:4567");

        sleep(1);

        clickLinkWithText("login", driver);

        sleep(1);

        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("keppa");
        element = driver.findElement(By.name("login"));

        sleep(1);
        element.submit();

        sleep(2);

        driver.quit();
    }

    private static void noSuchUser(){
        WebDriver driver = new ChromeDriver();

        driver.get("http://localhost:4567");

        sleep(1);

        clickLinkWithText("login", driver);

        sleep(1);

        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys("hermanni");
        element = driver.findElement(By.name("password"));
        element.sendKeys("keppa");
        element = driver.findElement(By.name("login"));

        sleep(1);
        element.submit();

        sleep(2);

        driver.quit();
    }

    private static void goodNewUser(){
        WebDriver driver = new ChromeDriver();

        driver.get("http://localhost:4567");

        sleep(1);

        clickLinkWithText("register new user", driver);

        sleep(1);
        Random r = new Random();

        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys("matti"+r.nextInt(100000));
        element = driver.findElement(By.name("password"));
        element.sendKeys("ohtu2017");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("ohtu2017");
        element = driver.findElement(By.name("signup"));

        sleep(1);
        element.submit();

        sleep(1);
        clickLinkWithText("continue to application mainpage", driver);

        sleep(1);
        clickLinkWithText("logout", driver);

        sleep(1);
        driver.quit();
    }


    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }

    private static void clickLinkWithText(String text, WebDriver driver) {
        int trials = 0;
        while( trials++<5 ) {
            try{
                WebElement element = driver.findElement(By.linkText(text));
                element.click();
                break;
            } catch(Exception e) {
                System.out.println(Arrays.toString(e.getStackTrace()));
            }
        }
    }
}
