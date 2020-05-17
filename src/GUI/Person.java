package GUI;

import Parsers.Parser;

import java.util.List;

public class Person {
    public static String token;

    public static List<String[]> ria_news;
    public static List<String[]> ny_news;
    public static List<String[]> kg_news;

    public static void Refresh(){
        ria_news = Parser.getRia_news();
        ny_news = Parser.getNyTimesNews();
        kg_news = Parser.get24kgNews();

    }

}
