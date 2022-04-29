package com.cobius.pages;

import com.cobius.constant.Constants;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Map;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class CompliancePage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(css = "i.glyphicon-plus")
    WebElement addCompliance;

    @FindBy(css = "i.glyphicon-search")
    WebElement searchEvent;

    @FindBy(id = "EventNumber")
    WebElement eventNumber;

    @FindBy(id = "btnSearch")
    WebElement searchButton;

    @FindBy(css = "tr.k-master-row td:first-child a")
    List<WebElement> eventIds;
    @FindBy(id = "DeleteEventButton")
    WebElement deleteEventButton;

    @FindBy(id = "EventDate")
    WebElement eventDate;

    @FindBy(id = "DiscoveryDate")
    WebElement discoveryDate;

    @FindBy(id = "OrganizationId")
    WebElement organizationId;

    @FindBy(id = "EventSummary")
    WebElement eventSummary;

    @FindBy(id = "OwnerId")
    WebElement ownerId;

    @FindBy(css = "div.k-multiselect-wrap")
    WebElement selectedAssignedToIds;

    @FindBy(css = "ul#SelectedAssignedToIds_listbox li")
    List<WebElement> assignIds;

    @FindBy(id = "StatusId")
    WebElement statusId;

    @FindBy(id = "Duedate")
    WebElement duedate;

    @FindBy(id = "AccountableParty")
    WebElement accountableParty;

    @FindBy(name = "SaveAndClose")
    WebElement saveAndClose;

    public CompliancePage(WebDriver driver){
        this.driver=driver;
        wait = new WebDriverWait(driver, Constants.EXPLICIT_WAIT);
        PageFactory.initElements(driver, this);
    }

    public CompliancePage addCompliance(){
        wait.until(elementToBeClickable(addCompliance));
        addCompliance.click();
        return this;
    }

    public CompliancePage navigateToSearchPage(){
        wait.until(elementToBeClickable(searchEvent));
        searchEvent.click();
        return  this;
    }

    public boolean checkIfEventAvailable(String eventId){
        return eventIds.stream().map(WebElement::getText).anyMatch(event->event.equalsIgnoreCase(eventId));
    }
    public CompliancePage searchEvent(String eventId){
        wait.until(elementToBeClickable(eventNumber));
        eventNumber.sendKeys(eventId);
        searchButton.click();
        return  this;
    }
    public void fillComplianceForm(Map<String, String> complianceData){
        System.out.println(complianceData);
        wait.until(elementToBeClickable(eventDate));
        eventDate.sendKeys(complianceData.get("EventDate"));
        discoveryDate.sendKeys(complianceData.get("DiscoveryDate"));


        new Select(organizationId).selectByVisibleText(complianceData.get("Organization").trim());
        eventSummary.sendKeys(complianceData.get("Event_Summary"));

        new Select(ownerId).selectByVisibleText(complianceData.get("Owner").trim());

        wait.until(elementToBeClickable(statusId));

        new Select(statusId).selectByVisibleText(complianceData.get("Status").trim());
        duedate.sendKeys(complianceData.get("DueDate"));
        accountableParty.sendKeys(complianceData.get("Accountable_Party"));

        selectedAssignedToIds.click();
        wait.until(visibilityOfAllElements(assignIds));
        assignIds.stream().filter(ele->ele.getText().trim().equalsIgnoreCase(complianceData.get("Assigned To").trim())).findFirst().orElseThrow().click();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        saveAndClose.click();

    }

    public CompliancePage openEventDetails(String eventId) {
        wait.until(visibilityOfAllElements(eventIds));
        eventIds.forEach(WebElement::getText);
        eventIds.stream().filter(event->event.getText().equalsIgnoreCase(eventId)).findFirst().get().click();
        return this;
    }

    public CompliancePage deleteEvent() {
        wait.until(elementToBeClickable(deleteEventButton));
        deleteEventButton.click();
        return this;
    }
}
