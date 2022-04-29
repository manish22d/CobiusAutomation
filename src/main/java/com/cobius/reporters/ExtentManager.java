package com.cobius.reporters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static ExtentReports extent;

    public synchronized static ExtentReports getReporter() {
        if (extent == null) {

            String workingDir = System.getProperty("user.dir");
            ExtentSparkReporter htmlReporter = new ExtentSparkReporter(workingDir + "\\Reports\\ExtentReportResults.html");
            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);

        }
        return extent;
}
    }