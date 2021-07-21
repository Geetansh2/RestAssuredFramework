package com.project1.testcases.pet;

import com.project1.apis.APIsPath;
import constants.Constants;
import httpRequest.HTTPRequest;
import io.restassured.response.Response;
import org.apache.log4j.Level;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utility.LogsUtility;

import java.io.File;

public class PetTestCases {

    HTTPRequest httpRequest;
    Response response;

    @BeforeClass
    public void beforeClass(){
        httpRequest = new HTTPRequest();
    }

    @Test
    public void uploadAnImageForThePet(){
        LogsUtility.logger.setLevel(Level.INFO);
        LogsUtility.logger.info("Upload an image for the pet 1");
        String petImageUploadUrlPath = APIsPath.petUploadImage.replace("{petId}", "1");
        LogsUtility.logger.info("Pet upload image url path for pet 1: " + petImageUploadUrlPath);
        File file = new File("C:\\Users\\geeta\\Desktop\\petImage.jpg");
        response = httpRequest.postImageOrFile(APIsPath.baseURI, file, petImageUploadUrlPath);
        LogsUtility.logger.info("Response string is : " + response.getBody().asString());
        LogsUtility.logger.info("Response statusCode:" + response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), Constants.successStatusCode);
    }
}
