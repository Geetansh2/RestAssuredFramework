package com.project1.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.log4j.Level;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import utility.LogsUtility;

public class Listener extends TestListenerAdapter {

    public ExtentSparkReporter htmlReporter;
    public ExtentReports extentReports;
    public ExtentTest test;

    public void onStart(ITestContext testContext){
        htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/src/test/java/com/project1/reports/extentReport.html");
        htmlReporter.config().setDocumentTitle("Automation Report");
        htmlReporter.config().setReportName("Rest Assured API Report");
        htmlReporter.config().setTheme(Theme.DARK);
        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
        extentReports.setSystemInfo("Project Name", "RestAssuredFramework");
        extentReports.setSystemInfo("Host name", "localhost");
        extentReports.setSystemInfo("Environment", "staging");
        extentReports.setSystemInfo("user", "Geetansh");
    }
    public void onTestSuccess(ITestResult result) {
        LogsUtility.logger.setLevel(Level.INFO);
        LogsUtility.logger.info("onTestSuccess Method: " +result.getName());
        test = extentReports.createTest(result.getName());
        test.log(Status.PASS, "Test case passed: " + result.getName());

    }

    public void onTestFailure(ITestResult result) {
        LogsUtility.logger.setLevel(Level.INFO);
        LogsUtility.logger.info("onTestFailure Method: " +result.getName());
        test = extentReports.createTest(result.getName());
        test.log(Status.FAIL, "Test case failed is: " + result.getName());
        test.log(Status.FAIL, result.getThrowable());
    }

    public void onTestSkipped(ITestResult result) {
        LogsUtility.logger.setLevel(Level.INFO);
        LogsUtility.logger.info("onTestSkipped Method: " +result.getName());
        test = extentReports.createTest(result.getName());
        test.log(Status.SKIP, "Test case skipped is: " + result.getName());
    }

    public void onFinish(ITestContext iTestContext){
        extentReports.flush();
    }


}
