package com.project1.testcases.pet;

import com.project1.apis.APIsPath;
import constants.Constants;
import httpRequest.HTTPRequest;
import io.restassured.response.Response;
import org.apache.log4j.Level;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utility.LogsUtility;
import utility.XLUtility;

import java.io.File;
import java.io.IOException;

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

    @Test(dataProvider = "petDataProvider")
    public void addPetInStore( String name, String id) throws ParseException {
        LogsUtility.logger.setLevel(Level.INFO);
        LogsUtility.logger.info("Adding pets in the store");
        JSONObject payload = petDetails(name, id);
        response = httpRequest.post(APIsPath.baseURI, payload, APIsPath.addPet);
        LogsUtility.logger.info("Response code is: " + response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), Constants.successStatusCode, "Status not matched..");
        LogsUtility.logger.info("Response string is: " + response.getBody().asString());

    }



    @DataProvider(name="petDataProvider")
    public Object[][] getPetData(){
        Object[][] petDataParam = {{"dog1", "5"}, { "dog2", "6"}};
        return petDataParam;
    }

    public JSONObject petDetails( String name, String id) throws ParseException {
        LogsUtility.logger.setLevel(Level.INFO);
        String petDetailJson = "{\n" +
                "  \"id\": 0,\n" +
                "  \"category\": {\n" +
                "    \"id\": 0,\n" +
                "    \"name\": \"doag\"\n" +
                "  },\n" +
                "  \"name\": \"doggie1\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"string\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonPetDetails = (JSONObject) jsonParser.parse(petDetailJson);

        ((JSONObject) jsonPetDetails.get("category")).put("name", name);
        ((JSONObject) jsonPetDetails.get("category")).put("id", id);
        LogsUtility.logger.info("Json payload: " + jsonPetDetails);

        return jsonPetDetails;
    }

    @Test(dataProvider = "getDataFromExcelSheet")
    public void addPetFromExcelSheet(String name, String id) throws ParseException {
        LogsUtility.logger.setLevel(Level.INFO);
        LogsUtility.logger.info("Adding pet from excel sheet (Data provider)");
        JSONObject payLoad = petDetails(name, id);
        response = httpRequest.post(APIsPath.baseURI, payLoad, APIsPath.addPet);
        LogsUtility.logger.info("Response code is: " + response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), Constants.successStatusCode, "Status mismatch");
        LogsUtility.logger.info("Response body is: " + response.getBody().asString());


    }

    @DataProvider(name = "getDataFromExcelSheet")
    public Object[][] getPetDataFromExcel() throws IOException {
        LogsUtility.logger.setLevel(Level.INFO);
        String pathOfExcelSheet = "C:\\Users\\geeta\\Desktop\\Pet.xlsx";
        XLUtility xlUtility = new XLUtility(pathOfExcelSheet);
        int row = xlUtility.getRowCount("PetDetails");
        int column = xlUtility.getCellCount("PetDetails", 1);
        LogsUtility.logger.info("Row is: " + row);
        LogsUtility.logger.info("Column is: " + column);
        Object[][] petdata = new Object[row][column];
        for(int i=1; i<=row; i++){
            for (int j=0; j<column; j++){
                petdata[i-1][j] = xlUtility.getCellData("PetDetails", i, j);
            }
        }
        LogsUtility.logger.info("Pet Data is: " + petdata);
        return petdata;
    }

}
