package utilities;

import com.jayway.jsonpath.DocumentContext;

public class RequestBodyService {
    public void setRequestBodyForComment(DocumentContext requestBody, String postId, String name, String email, String body){
        requestBody.set("postId", postId);
        requestBody.set("name", name);
        requestBody.set("email", email);
        requestBody.set("body", body);
    }
}
