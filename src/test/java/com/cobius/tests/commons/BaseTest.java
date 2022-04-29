package com.cobius.tests.commons;

import com.cobius.core.WebDriverManager;
import com.cobius.listeners.TestListener;
import com.cobius.pages.CompliancePage;
import com.cobius.pages.DashboardPage;
import com.cobius.pages.LoginPage;
import com.cobius.utils.PropertyUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.util.Properties;


@Listeners(TestListener.class)
public class BaseTest {
    public Properties property;
    protected WebDriver driver;
    protected String appUrl;
    protected String userId;
    protected String pwd;
    protected String client;


    public LoginPage loginpage;
    public DashboardPage dashboardPage;

    public CompliancePage compliancePage;




    @BeforeSuite
    public void loadConfig() {
        property = PropertyUtils.getProperty();
        appUrl = property.getProperty("app.url");
        userId = property.getProperty("userId");
        pwd = property.getProperty("pwd");
        client = property.getProperty("client");

        driver = WebDriverManager.getDriver();
        driver.navigate().to(appUrl);


        loginpage = new LoginPage(driver);
        System.out.println("before class");
    }

    @BeforeClass(alwaysRun = true)
    public void classSetup(ITestContext context) {
        context.setAttribute("webdriver", driver);
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        driver.close();
        driver.quit();
    }

}
