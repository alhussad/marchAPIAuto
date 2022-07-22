package step_Definitions;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.cucumber.gherkin.internal.com.eclipsesource.json.Json;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class BaseSteps {
    public String serviceUrl;
    public String makeAPostPayload, makeACommentPayload;
    public String makeAPostEndPoint, makeACommentEndPoint, createAUserEndPoint;
    public Headers headers;
    private String endpointPath;
    private Response response;
    public DocumentContext requestBodyJson;

    public BaseSteps(){
        makeACommentPayload = "/template/CommentOnPost.json";
        makeAPostPayload = "/template/MakeAPost.json";
        serviceUrl = "https://jsonplaceholder.typicode.com/";
        makeAPostEndPoint = serviceUrl + "posts/";
        makeACommentEndPoint = serviceUrl + "comments";
        createAUserEndPoint = serviceUrl + "users/";
    }

    public void setHeaders(Headers value){
        restHeaders();
        headers = value;
    }
    public void restHeaders(){
        headers = null;
    }
    public void setHeadersWithContentType(){
        headers = new Headers(new Header("Content-Type", "application/json"));
        setHeaders(headers);
    }
    public void setHeadersWithManyHeaders(){
        headers = new Headers(
                new Header("Content-Type", "application/json"),
                new Header("Authorisation", "89289789jkjkhjkhu9njnknkjh98"),
                new Header("Token", "token value"));
        setHeaders(headers);
    }
    protected URL getURL(){
        try {
            return new URL(endpointPath);
        }catch (MalformedURLException e){
            throw new RuntimeException();
        }
    }
    public Response getCall(){
        response = RestAssured.given().log().all()
                .relaxedHTTPSValidation()
                .headers(headers)
                .when()
                .get(getURL())
                .then()
                .log().all()
                .extract()
                .response();
        return response;
    }
    public Response getPostCall(){
        HashMap<String, String> postBody = new HashMap<>();
        postBody.put("postId", "8");
        postBody.put("name", "My new Comment");
        postBody.put("email", "hussaini@gmail.com");
        postBody.put("body", "I like this new post so much");

        response = RestAssured.given().log().all()
                .relaxedHTTPSValidation()
                .headers(headers)
                .body(requestBodyJson.jsonString())
                .when()
                .post(getURL())
                .then()
                .log().all()
                .extract()
                .response();
        return response;
    }
    public void setEndpointPath(String endpointPath){
        this.endpointPath = endpointPath;
    }
    public Response getResponse(){
        return response;
    }
    /*public void setRequestBody(DocumentContext requestBody){
        this.requestBodyJson = requestBody;
    }*/
    public DocumentContext loadJsonTemplate(String path){
        requestBodyJson = JsonPath.parse(this.getClass().getResourceAsStream(path));
        return requestBodyJson;
    }
}
