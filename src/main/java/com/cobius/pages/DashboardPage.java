package com.cobius.pages;

import com.cobius.constant.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class DashboardPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(css = "a.dropdown-toggle i.fa-user")
    WebElement profileDropdown;

    @FindBy(css = "a i.fa-sign-out")
    WebElement logoutButton;

    @FindBy(css = "a h4")
    WebElement compliancePageLink;

    @FindBy(tagName = "a")
    List<WebElement> links;


    public DashboardPage(WebDriver driver){
        this.driver=driver;
        wait = new WebDriverWait(driver, Constants.EXPLICIT_WAIT);
        PageFactory.initElements(driver, this);
    }

    public DashboardPage logout(){
        wait.until(elementToBeClickable(profileDropdown));
        profileDropdown.click();
        wait.until(elementToBeClickable(logoutButton));
        logoutButton.click();
        return this;
    }

    public List getBrokenLinks(){
         return links.stream().filter(url->url.getAttribute("href")==null||url.getAttribute("href").isEmpty()).map(WebElement::getText).collect(Collectors.toList());
    }

    public CompliancePage navigateToCompliancePage() {
        wait.until(elementToBeClickable(compliancePageLink));
        compliancePageLink.click();
        return new CompliancePage(driver);
    }
}
