package utilities;

import models.Anime;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WebUtils {

    //Future - add utility methods to scrap site and find information of all shows/specific showIDs

    public static ArrayList<Anime> fetchHorribleAnime() {
        ArrayList<Anime> animeArrayList = new ArrayList<>();
        String url = "https://horriblesubs.info/shows/";
        try{
            Document htmlpage = Jsoup.connect(url).get();
            Elements shows = htmlpage.select("div.ind-show");

            for (Element show :
                    shows) {
                Element showData = show.select("a").first();
                animeArrayList.add(new Anime(showData.attr("href"), showData.attr("title")));
            }
            return animeArrayList;

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Error grabbing data from horrible subs.");
        return null;
    }

    public static ArrayList<Anime> searchAnimeList(String searchQuery, ArrayList<Anime> animeArrayList) {
        final String sQ = searchQuery.toLowerCase();
        return new ArrayList<Anime>(animeArrayList.stream().filter(anime -> anime.getAnimeTitle().toLowerCase().contains(sQ)).collect(Collectors.toList()));
    }

}
