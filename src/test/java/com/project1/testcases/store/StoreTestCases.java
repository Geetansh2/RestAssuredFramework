package com.project1.testcases.store;

import com.project1.apis.APIsPath;
import com.project1.base.BaseTest;
import com.project1.listeners.Listener;
import constants.Constants;
import httpRequest.HTTPRequest;
import io.restassured.response.Response;
import org.apache.log4j.Level;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utility.LogsUtility;

@Listeners(Listener.class)
public class StoreTestCases extends BaseTest {

    HTTPRequest httpRequest;
    Response response;
    @BeforeClass
    public void beforeClass(){
        httpRequest=new HTTPRequest();
    }

    @Test
    public void getStoreInventoryTest(){
        LogsUtility.logger.setLevel(Level.INFO);
        LogsUtility.logger.info("Getting store inventory");
        response = httpRequest.get(APIsPath.baseURI, APIsPath.storeGetInventory);
        int statusCode = response.getStatusCode();
        LogsUtility.logger.info("Response code is: " + statusCode);
        Assert.assertEquals(statusCode, Constants.successStatusCode, "Status code is not matching.");

        String statusLine = response.statusLine();
        LogsUtility.logger.info("Status line is: " + statusLine);
        Assert.assertEquals(statusLine,Constants.successHTTPStatusLine, "Status line is not matching");

        String reponseBodyAsString = response.getBody().asString();
        LogsUtility.logger.info("Response body is: " + reponseBodyAsString);

        String headerContentType = response.getHeader("content-type");
        LogsUtility.logger.info("Header content type: " + headerContentType);
        Assert.assertEquals(headerContentType, "application/json", "Response header in not matching");
    }

    @Test
    public void placeOderForPetInStore(){
        LogsUtility.logger.setLevel(Level.INFO);
        LogsUtility.logger.info("Placing an order for a pet from the store");
        JSONObject payLoad = new JSONObject();
        payLoad.put("id", 0);
        payLoad.put("petId", 0);
        payLoad.put("quantity", 2);
        payLoad.put("shipDate", "2021-07-21T06:00:22.753Z");
        payLoad.put("status", "placed");
        payLoad.put("complete", "true");
        response = httpRequest.post(APIsPath.baseURI, payLoad, APIsPath.storePlaceOrder);
        LogsUtility.logger.info("Response is: " + response.getBody().asString());

        int statusCode = response.getStatusCode();
        LogsUtility.logger.info("Status code is: " + statusCode);
        Assert.assertEquals(statusCode, Constants.successStatusCode, "Status code is not expected");

        Long id = response.jsonPath().get("id");
        LogsUtility.logger.info("Id is: " + id);
    }
}
