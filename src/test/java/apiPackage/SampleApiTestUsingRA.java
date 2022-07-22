package apiPackage;

import io.restassured.http.ContentType;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class SampleApiTestUsingRA {

    //@Test
    public void getComment(){
        given().contentType(ContentType.JSON).log().all()
                .when().get("https://jsonplaceholder.typicode.com/comments/5")
                .then().log().all().statusCode(200).body("name", equalTo("vero eaque aliquid doloribus et culpa"))
                .body("email", equalTo("Hayden@althea.biz"));
    }
    @Test
    public void postComment(){
        HashMap<String, String> postBody = new HashMap<>();
        postBody.put("postId", "1");
        postBody.put("name", "My first comment");
        postBody.put("email", "hussaincstp@gmail.com");
        postBody.put("body", "I am so happy with the comment my friend made");

        given().contentType(ContentType.JSON).log().all().with().body(postBody)
                .when().post("https://jsonplaceholder.typicode.com/comments")
                .then().log().all().statusCode(201)
                .body("name", equalTo("My first comment"))
                .body("email", equalTo("hussaincstp@gmail.com"))
                .body("body", equalTo("I am so happy with the comment my friend made"));
    }
}
