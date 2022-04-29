package com.cobius.pages;

import com.cobius.constant.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class LoginPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(id = "UserName")
    WebElement userIdTextbox;

    @FindBy(id = "Password")
    WebElement pwdTextbox;

    @FindBy(id = "Client")
    WebElement clientTextbox;

    @FindBy(css = "input[value='Log On']")
    WebElement logOnButton;

    public LoginPage(WebDriver driver){
        this.driver=driver;
        wait = new WebDriverWait(driver, Constants.EXPLICIT_WAIT);
        PageFactory.initElements(driver, this);
    }

    public DashboardPage performLogin(String userId, String pwd, String client){
        wait.until(elementToBeClickable(userIdTextbox));
        userIdTextbox.sendKeys(userId);
        pwdTextbox.sendKeys(pwd);
        clientTextbox.sendKeys(client);
        logOnButton.click();
        return new DashboardPage(driver);
    }


}
