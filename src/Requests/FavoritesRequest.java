package Requests;

import GUI.Person;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FavoritesRequest {

    public static List<String[]> FavoritesRequestMake(){
        List<String[]> result = new ArrayList<>();
        try{
            HttpResponse<JsonNode> request = Unirest.get("http://localhost:8000/articles/")
                    .header("Authorization", "Token " + Person.token)
                    .asJson();
            JSONArray myObj = request.getBody().getArray();
            for (Object kek: myObj){
                String[] arr = new String[5];

                JSONObject obj = new JSONObject(kek.toString());

                arr[0] = obj.getString("title");
                arr[1] = obj.getString("description_or_date_pub");
                arr[2] = obj.getString("link");
                arr[3] = obj.getString("image_link");
                arr[4] = obj.getString("url");

                result.add(arr);
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }
    public static boolean CheckFavorites(String new_title){
        try {
            HttpResponse<JsonNode> request = Unirest.get("http://localhost:8000/articles/")
                    .header("Authorization", "Token " + Person.token)
                    .asJson();
            JSONArray myObj = request.getBody().getArray();

            for (Object kek: myObj){
                JSONObject obj = new JSONObject(kek.toString());
                String title = obj.getString("title");

                if (title.equals(new_title)){
                    return true;
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
