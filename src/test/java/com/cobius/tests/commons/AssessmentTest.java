package com.cobius.tests.commons;

import com.cobius.utils.ExcelUtils;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.MatcherAssert.assertThat;

public class AssessmentTest extends BaseTest{

    @BeforeTest
    public void login(){
        dashboardPage = loginpage.performLogin(userId,pwd,client);
    }

    @Test
    public void validateAllLinks(){
        System.out.println("number of broken Links->" +dashboardPage.getBrokenLinks().size());
        assertThat(dashboardPage.getBrokenLinks().size(),is(greaterThan(0)));
    }

    @Test
    public void addComplianceTest() throws IOException {
        dashboardPage.navigateToCompliancePage().addCompliance().fillComplianceForm(ExcelUtils.getComplianceData());
    }

    @Test
    public void verifyEventAvailable(){
        assertThat("event not available", dashboardPage.navigateToCompliancePage().navigateToSearchPage().searchEvent("22-067").checkIfEventAvailable("22-067"));
    }

    @Test
    public void deleteEvent(){
        dashboardPage.navigateToCompliancePage().navigateToSearchPage().searchEvent("22-005").openEventDetails("22-005").deleteEvent();
    }

    @AfterTest
    public void logout() throws InterruptedException {
        dashboardPage.logout();
    }
}
