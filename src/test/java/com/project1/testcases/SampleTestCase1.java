package com.project1.testcases;

import com.project1.listeners.Listener;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Level;

import org.testng.Assert;
import org.testng.annotations.Test;
import utility.LogsUtility;

public class SampleTestCase1 {
    @Test
    public void testCase1() {
        LogsUtility.logger.setLevel(Level.INFO);
        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET, "/employee/1");
        String zyz = response.getBody().asString();
        LogsUtility.logger.info(zyz);
        int responseStatusCode = response.getStatusCode();
        LogsUtility.logger.info(responseStatusCode);
        Assert.assertEquals(5, 6);
    }

}
