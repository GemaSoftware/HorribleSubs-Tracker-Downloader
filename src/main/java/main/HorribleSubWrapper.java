package main;

import models.Anime;
import models.Episode;
import utilities.WebUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class HorribleSubWrapper {

    private ArrayList<Anime> globalAnimeList;
    private ArrayList<Anime> currentAiringList;
    public HorribleSubWrapper(){
        System.out.println("Creating wrapper.");
        globalAnimeList = WebUtils.fetchHorribleAnime("/shows/");
        currentAiringList = WebUtils.fetchHorribleAnime("/current-season/");
    }

    public List<Anime> getGlobalAnimeList() {
        return globalAnimeList;
    }

    public ArrayList<Anime> getCurrentAiringList() {
        return currentAiringList;
    }


    //TBI - add ability to just search using string that can be called from anywhere without scanner. i,e searchAnime(showname)
    public ArrayList<Anime> searchAnime(String animeName){
        try{
            return WebUtils.searchAnimeList(animeName, this.globalAnimeList);
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    //will eventually return list of episodes or anime with all details added.
    private void showAnimeDetails(Anime anime) {

        WebUtils.fetchAnimeID(anime);
        WebUtils.fetchAnimeEpisodes(anime);


        //testing episode adder.
        for (Episode e :
                anime.getAnimeEpisodeList()) {
            System.out.println(e.getEpisodeReleaseDate() + " " + e.getEpisodeNumber() + " episode links - " + Arrays.asList(e.getEpisodeMagnetLink()));
        }

    }
}
