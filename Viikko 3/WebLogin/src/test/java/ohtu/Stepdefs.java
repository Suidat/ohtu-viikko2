package ohtu;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Stepdefs {
    WebDriver driver = new ChromeDriver();
    String baseUrl = "http://localhost:4567";
    
    @Given("^login is selected$")
    public void login_selected() throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("login"));       
        element.click();          
    } 

    @When("^username \"([^\"]*)\" and password \"([^\"]*)\" are given$")
    public void username_and_password_are_given(String username, String password) throws Throwable {
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();  
    }

    @Then("^system will respond \"([^\"]*)\"$")
    public void system_will_respond(String pageContent) throws Throwable {
        assertTrue(driver.getPageSource().contains(pageContent));
    }
    
    @When("^correct username \"([^\"]*)\" and password \"([^\"]*)\" are given$")
    public void username_correct_and_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }

    @When("^correct username \"([^\"]*)\" and incorrect password \"([^\"]*)\" are given$")
    public void username_and_incorrect_password_are_given(String username, String password) throws Throwable {
        logInWith(username, password);
    }
    
    @Then("^user is logged in$")
    public void user_is_logged_in() throws Throwable {
        pageHasContent("Ohtu Application main page");
    }
    
    @Then("^user is not logged in and error message is given$")
    public void user_is_not_logged_in_and_error_message_is_given() throws Throwable {
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");
    }

    @When("^nonexistent username \"([^\"]*)\" and password \"([^\"]*)\" are given$")
    public void nonexistentUsernameAndPasswordAreGiven(String username, String password) throws Throwable {
        logInWith(username,password);
    }

    @Given("^command new user is selected$")
    public void commandNewUserIsSelected() throws Throwable {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
    }

    @When("^a valid username \"([^\"]*)\" and password \"([^\"]*)\" and matching password confirmation are entered$")
    public void aValidUsernameAndPasswordAndMatchingPasswordConfirmationAreEntered(String username, String password) throws Throwable {
        createNewUser(username, password, true);
    }

    @Then("^a new user is created$")
    public void aNewUserIsCreated() throws Throwable {
        assertTrue(driver.getPageSource().contains("info for newbie"));
    }

    @When("^entered username \"([^\"]*)\" is too short and valid password \"([^\"]*)\"$")
    public void enteredUsernameIsTooShortAndValidPassword(String username, String password) throws Throwable {
        createNewUser(username, password, true);
    }

    @Then("^user is not created and error \"([^\"]*)\" is reported$")
    public void userIsNotCreatedAndErrorIsReported(String message) throws Throwable {
        assertTrue(driver.getPageSource().contains(message));

    }

    @When("^a valid username \"([^\"]*)\" and too short password \"([^\"]*)\" are entered$")
    public void aValidUsernameAndTooShortPasswordAreEntered(String username, String password) throws Throwable {
        createNewUser(username, password,true);
    }

    @When("^an existing username \"([^\"]*)\" and valid password \"([^\"]*)\" are entered$")
    public void anExistingUsernameAndValidPasswordAreEntered(String username, String password) throws Throwable {
        createNewUser(username, password, true);
    }

    @When("^a valid username \"([^\"]*)\" and password \"([^\"]*)\" but password confirmation does not match$")
    public void aValidUsernameAndPasswordButPasswordConfirmationDoesNotMatch(String username, String password) throws Throwable {
        createNewUser(username, password, false);
    }
     //helper methods

    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }

    private void logInWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Give your credentials to login"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();
    }

    private void createNewUser(String username, String password, boolean matches){
        {
            assertTrue(driver.getPageSource().contains("Create username and give password"));
            WebElement element = driver.findElement(By.name("username"));
            element.sendKeys(username);
            element = driver.findElement(By.name("password"));
            element.sendKeys(password);

            if(matches) {
                element = driver.findElement(By.name("passwordConfirmation"));
                element.sendKeys(password);
            }
            else{
                element = driver.findElement(By.name("passwordConfirmation"));
                element.sendKeys("not"+password);
            }

            element = driver.findElement(By.name("signup"));
            element.submit();
        }
    }

    @After
    public void tearDown(){
        driver.quit();
    }


    @Given("^user with username \"([^\"]*)\" with password \"([^\"]*)\" is successfully created$")
    public void userWithUsernameWithPasswordIsSuccessfullyCreated(String username, String password) throws Throwable {
        commandNewUserIsSelected();
        createNewUser(username, password, true);
        WebElement element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();

    }

    @Given("^user with username \"([^\"]*)\" and password \"([^\"]*)\" is tried to be created$")
    public void userWithUsernameAndPasswordIsTriedToBeCreated(String username, String password) throws Throwable {
        commandNewUserIsSelected();
        createNewUser(username, password, true);
        WebElement element = driver.findElement(By.linkText("back to home"));
        element.click();
    }


}
