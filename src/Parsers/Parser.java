package Parsers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    public static void main(String[] args) {
        for (String[] arr: getRia_news()){
            System.out.println(arr[0]);
            System.out.println(arr[1]);
            System.out.println(arr[2]);
            System.out.println(arr[3]);
        }
    }

    public static List<String[]> getRia_news() {
        List<String[]> ria_news = new ArrayList<>();

        final String url = "https://ria.ru/lenta/";

        String site_url = "https://ria.ru";

        try{
            final Document document = Jsoup.connect(url).get();

            String previous_title = "";
            String previous_link = "";

            for (Element element: document.select("div.list div")){

                String[] result_array = new String[4];

                String title = element.select(
                        ".list-item__title").text();
                String link = element.select(
                        ".list-item__title").attr("href");
                String date_published = element.select(".list-item__date").text();
                String image_link = element.select(".list-item__image picture img").attr("src");


                if (! (link.equals("") || link.equals(previous_link))){
                    previous_link = link;
                    result_array[1] = site_url + link;
                }

                if (! date_published.equals("") ){
                    result_array[2] = date_published;
                }

                if (image_link.equals("")){
                    result_array[3] = "none";
                }
                else {
                    result_array[3] = image_link;
                }

                if (!title.equals("") && !title.equals(previous_title) ){
                    previous_title = title;
                    result_array[0] = title;
                    ria_news.add(result_array);
                }

            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return ria_news;
    }

    public static List<String[]> getNyTimesNews(){
        List<String[]> ny_times_news = new ArrayList<>();

        final String url = "https://www.nytimes.com/section/world";

        String site_url = "https://www.nytimes.com";

        try{
            final Document document = Jsoup.connect(url).get();

            for (Element element: document.select("div.css-13mho3u ol li")){
                String[] result_array = new String[4];

                final String title = element.select(
                        ".e1xfvim30.css-1dq8tca").text();
                final String description = element.select(
                        ".e1xfvim31.css-1echdzn").text();
                final String link = element.select(".css-4jyr1y a").attr("href");
                final String image_link = element.select(".css-79elbk img").attr("itemid");

                result_array[0] = title;
                result_array[1] = description;
                result_array[2] = site_url + link;
                if (image_link.isEmpty()){
                    result_array[3] = "https://pixel.nymag.com/imgs/daily/grub/2016/05/23/23-new-york-times-logo.w1200.h630.jpg";
                }
                else {
                    result_array[3] = image_link;
                }
                ny_times_news.add(result_array);
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return ny_times_news;
    }
    public static List<String[]> get24kgNews(){
        List<String[]> kg24_news = new ArrayList<>();

        final String url = "https://24.kg";

        try{
            final Document document = Jsoup.connect(url).get();


            String related_date = "";
            String previous_title = "";

            for (Element element: document.select("div.row.lineNews div.col-xs-12 div")){

                String[] result_array = new String[3];

                String date = element.select(".lineDate").text();

                if (! date.equals("")) {
                    related_date = date;
                }
                String title = element.select("div.one div.title").text();
                String time = element.select("div.one div.time").text();
                String link = element.select("div.one div.title a").attr("href");


                if (! time.equals("")){
                    result_array[1] = related_date + " " + time;
                }
                if (! link.equals("")){
                    result_array[2] = url + link;
                }
                if (!title.equals("") && !title.equals(previous_title) ){
                    previous_title = title;
                    result_array[0] = title;
                    kg24_news.add(result_array);
                }

            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return kg24_news;
    }

}