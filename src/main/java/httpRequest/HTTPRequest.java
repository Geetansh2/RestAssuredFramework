package httpRequest;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Level;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeMethod;
import utility.LogsUtility;

import java.io.File;

public class HTTPRequest {

    //TODO change string to resposne
    public Response get(String baseURI, String apiPath){
        RestAssured.baseURI = baseURI;
        RequestSpecification httpRequest =RestAssured.given();
        Response response = httpRequest.request(Method.GET, apiPath);
        return response;
    }

    public Response post(String baseUrI, JSONObject payLoad, String apiPath){
        RestAssured.baseURI = baseUrI;
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Content-Type", "application/json");
        httpRequest.header("api_key", "Geetansh");
        httpRequest.body(payLoad.toJSONString());
        Response response = httpRequest.request(Method.POST, apiPath);
        return response;
    }

    public Response postImageOrFile(String baseUrI, File filePath, String apiPath){
        RestAssured.baseURI = baseUrI;
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Content-Type", "multipart/form-data; boundary=----webkitformboundary2fvqbgcbynvtvptx");
        httpRequest.header("accept", "application/json");
        /*
        curl -X 'POST' \
          'https://petstore.swagger.io/v2/pet/1/uploadImage' \
          -H 'accept: application/json' \
          -H 'Content-Type: multipart/form-data' \
          -F 'additionalMetadata=Geetansh' \
          -F 'file=@400app.PNG;type=image/png'
         */
        httpRequest.multiPart("additionalMetadata", "Geetansh");
        httpRequest.multiPart("file", filePath);

        Response response = httpRequest.request(Method.POST, apiPath);
        return response;
    }
}
