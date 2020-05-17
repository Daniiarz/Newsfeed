package Parsers;

import GUI.Person;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.Random;

public class FirstPageLogic {
    public static void main(String[] args) {
        getFirstArticle();
    }


    public static String[] getFirstArticle(){
        Random objGenerator = new Random();
        int randomNumber = objGenerator.nextInt(3);
        String[] result = new String[3];
        switch (randomNumber){
            case 0:
                for (String[] arr: Person.ria_news){
                    if (!arr[3].equals("none")){
                        result[0] = arr[3];
                        result[1] = arr[0];
                        result[2] = arr[1];
                        break;
                    }
                    else {
                        result[0] = "https://imrussia.org/images/stories/IMR_in_the_Media/Welcomes_Pardon/ria-novosti_hd.jpg";
                        result[1] = arr[0];
                        result[2] = arr[1];
                        break;
                    }
                }
                break;
            case 1:
                for (String[] arr: Person.ny_news){
                    try {
                        final Document document = Jsoup.connect(arr[2]).get();
                       result[0] = document.selectFirst("div.css-bsn42l img").attr("src");
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                    result[1] = arr[0];
                    result[2] = arr[2];
                    break;
                }
                break;
            case 2:
                for (String[] arr: Person.kg_news) {
                    result[0] = "https://gdb.rferl.org/B80F2868-B60A-413E-A52D-B89388B9EC09_w1200_r1_s.jpg";
                    result[1] = arr[0];
                    result[2] = arr[2];
                    break;
                }
                break;

        }
        return result;
    }

}
