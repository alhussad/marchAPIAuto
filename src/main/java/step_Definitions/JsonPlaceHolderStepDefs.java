package step_Definitions;

import com.jayway.jsonpath.DocumentContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import utilities.RequestBodyService;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class JsonPlaceHolderStepDefs extends BaseSteps{
    Response responseForGetComment, responseForPostComment;
    RequestBodyService requestBodyService;
    @Given("service is up and running")
    public void service_is_up_and_running() {
        setHeadersWithContentType();
        setEndpointPath(serviceUrl);
        getCall();
        Response responseForGetService = getResponse();
        assertThat(responseForGetService.statusCode(), is(200));
    }
    @When("I search for {string} of a comment with a Get method")
    public void i_search_for_of_a_comment_with_a_get_method(String id) {
        setHeadersWithContentType();
        setEndpointPath(makeACommentEndPoint+id);
        getCall();
        responseForGetComment = getResponse();

    }
    @Then("I should get the correct {string}, {string}, {string} and {string} returned with status code of {int}")
    public void i_should_get_the_correct_and_returned_with_status_code_of(String id, String name, String email, String body, Integer sCode) {
        assertThat(responseForGetComment.statusCode(), is(sCode));
        assertThat(responseForGetComment.body().jsonPath().get("id"), is(Integer.parseInt(id)));
        assertThat(responseForGetComment.body().jsonPath().get("name"), is(name));
        assertThat(responseForGetComment.body().jsonPath().get("email"), is(email));
        assertThat(responseForGetComment.body().jsonPath().get("body"), is(body));
    }
    @When("I create a new request for the following details {string}, {string}, {string} and {string}")
    public void i_create_a_new_request_for_the_following_details_and(String postId, String name, String email, String body) {
        setHeadersWithContentType();
        setEndpointPath(makeACommentEndPoint);
        requestBodyService = new RequestBodyService();
        DocumentContext requestBody = loadJsonTemplate(makeACommentPayload);
        requestBodyService.setRequestBodyForComment(requestBody, postId, name, email, body);
        getPostCall();
        responseForPostComment = getResponse();
        //System.out.println("Here is the problem: "+responseForPostComment.statusCode());
    }
    @Then("I should get the correct {string}, {string}, {string} and {string} returned and with status code of {int}")
    public void i_should_get_the_correct_and_returned_and_with_status_code_of(String postId, String name, String email, String body, Integer sCode) {
        assertThat(responseForPostComment.statusCode(), is(sCode));
        assertThat(responseForPostComment.body().jsonPath().get("postId"), is(postId));
        assertThat(responseForPostComment.body().jsonPath().get("name"), is(name));
        assertThat(responseForPostComment.body().jsonPath().get("email"), is(email));
        assertThat(responseForPostComment.body().jsonPath().get("body"), is(body));
    }

    @When("I create a new request for the following details {string}, {string}, {string} and {string} for users")
    public void iCreateANewRequestForTheFollowingDetailsAndForUsers(String arg0, String arg1, String arg2, String arg3) {
        
    }

//    @Then("I should get the correct {string}, {string}, {string} and {string} returned and with status code of {int} for users")
//    public void iShouldGetTheCorrectAndReturnedAndWithStatusCodeOfForUsers(String arg0, String arg1, String arg2, String arg3, int arg4) {
//
//    }
}
