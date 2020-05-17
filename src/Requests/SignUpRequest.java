package Requests;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;

public class SignUpRequest {

    public static String  SignUpRequestMake(String email, String first_name, String last_name, String password1, String password2)
    throws Exception{
        HttpResponse<String> postRequest = Unirest.post("http://0.0.0.0:8000/rest-auth/registration/").field(
                "email", email).field("first_name", first_name).field("last_name", last_name).field("password1", password1).field("password2", password2).asString();
        int status = postRequest.getStatus();

        if (status == 201){
            return "true";
        }
        else return postRequest.getBody();
    }
}
