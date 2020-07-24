package utilities;

import models.Anime;
import models.Episode;
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

    public static ArrayList<Anime> fetchHorribleAnime(String type) {
        ArrayList<Anime> animeArrayList = new ArrayList<>();
        String url = "https://horriblesubs.info" + type;
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

    //Gets horrible subs ID for show and sets anime object ID.
    //Could be obsolete if adding DB. Will just save titles and id for future use.
    public static void fetchAnimeID(Anime anime){
        String url = "https://horriblesubs.info/shows" + anime.getAnimeLink();
        System.out.println("Grabing shows Hs ID");
        try{
            Document htmlPage = Jsoup.connect(url).get();
            //Grabs HS ID (int only) from the URL Script tag using JSoup and CSS Selectors.
            Element hs = htmlPage.select(".entry-content > script:nth-child(2)").first();
            String numberOnly= hs.data().replaceAll("[^0-9]", "");
            anime.setAnimeHSID(Integer.parseInt(numberOnly));
        } catch (Exception e){
            System.out.println("hs id not found");
            e.printStackTrace();
        }
    }

    public static ArrayList<Anime> searchAnimeList(String searchQuery, ArrayList<Anime> animeArrayList) {
        final String sQ = searchQuery.toLowerCase();
        return new ArrayList<Anime>(animeArrayList.stream().filter(anime -> anime.getAnimeTitle().toLowerCase().contains(sQ)).collect(Collectors.toList()));
    }


    //Reach HorribleSubs API using url with hsubID attached to it.
    public static void fetchAnimeEpisodes(Anime anime) {
        //To be implemented.

        String apiURL = "https://horriblesubs.info/api.php";
        int  pageLimit = 5; //max 60 episodes for now.

        for (int i=0; i<pageLimit; i++){
            String apiEpiURL = apiURL + "?method=getshows&showid=" + anime.getAnimeHSID() + "&nextid=" + i +"&type=show";

            try {
                Document episodePage = Jsoup.connect(apiEpiURL).get();
                if (episodePage.html().contains("DONE")){
                    System.out.println("Reached end of episode list");
                    break;
                } else {
                    Elements episodes = episodePage.select("div.rls-info-container");
                    for (Element epi :
                            episodes) {
                        Episode episode = new Episode(anime, epi.attr("id"));

                        //test date string
                        episode.setEpisodeReleaseDate(epi.select("span.rls-date").first().text());

                        //TBI - add all episode magnet links for each quality

                        anime.addAnimeToList(episode);
                    }

                }
            } catch (IOException e) {
                System.out.println("Could not open episodes page.");
                e.printStackTrace();
            }


        }

        //Test - checking for hshow ID:
        System.out.println(anime.getAnimeHSID()+ " show ID");
    }
}
