package Requests;

import GUI.Person;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

public class LoginRequest {
    public static void main(String[] args) {
        try{
            LoginRequestMake("da@da.com", "Qwertyz01");
        }
        catch (Exception e){
            System.out.println("kek");
        }

    }

    public static boolean LoginRequestMake(String email, String password) throws Exception{

        int status = 0;

        HttpResponse<JsonNode> postRequest = Unirest.post("http://localhost:8000/rest-auth/login/").field(
                "email", email).field("password", password).asJson();
        status = postRequest.getStatus();

        Person.token = postRequest.getBody().getObject().getString("key");
        System.out.println(Person.token);

        if (status == 200){
            return true;
        }
        return false;
    }
}
