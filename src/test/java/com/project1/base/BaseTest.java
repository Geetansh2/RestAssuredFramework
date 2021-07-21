package com.project1.base;

import httpRequest.HTTPRequest;
import lombok.extern.java.Log;
import org.apache.log4j.Level;
import org.testng.annotations.BeforeMethod;
import utility.LogsUtility;

public class BaseTest extends HTTPRequest {
    @BeforeMethod
    public void testing(){
        LogsUtility.logger.setLevel(Level.INFO);
        LogsUtility.logger.info("Inside base Test before method");
    }
}
