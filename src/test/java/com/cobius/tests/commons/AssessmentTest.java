package com.cobius.tests.commons;

import com.cobius.utils.ExcelUtils;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.MatcherAssert.assertThat;

public class AssessmentTest extends BaseTest{

    String eventNumber;
    @BeforeTest
    public void login(){
        dashboardPage = loginpage.performLogin(userId,pwd,client);
    }

    @Test
    public void validateAllLinks(){
        System.out.println("number of broken Links->" +dashboardPage.getBrokenLinks().size());
        assertThat(dashboardPage.getBrokenLinks().size(),is(greaterThan(0)));
    }

    @Test(dependsOnMethods = "validateAllLinks")
    public void addComplianceTest() throws IOException {
        dashboardPage.navigateToCompliancePage().addCompliance().fillComplianceForm(ExcelUtils.getComplianceData());
        String eventMsg = dashboardPage.getNotificationMsg();
        System.out.println(eventMsg);
        Pattern pattern = Pattern.compile("[a-z A-Z.]");
        Matcher matcher = pattern.matcher(eventMsg);
        eventNumber = matcher.replaceAll("");
        System.out.println("event Number is :" + eventNumber);
    }

    @Test(dependsOnMethods = "addComplianceTest")
    public void verifyEventAvailable(){
        assertThat("event not available", dashboardPage.navigateToCompliancePage().navigateToSearchPage().searchEvent(eventNumber).checkIfEventAvailable(eventNumber));
    }

    @Test(dependsOnMethods = "verifyEventAvailable")
    public void deleteEvent(){
        dashboardPage.navigateToCompliancePage().navigateToSearchPage().searchEvent(eventNumber).openEventDetails(eventNumber).deleteEvent();
    }

    @AfterTest(alwaysRun = true)
    public void logout() throws InterruptedException {
        dashboardPage.logout();
    }
}
