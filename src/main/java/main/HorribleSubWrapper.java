package main;

import models.Anime;
import utilities.WebUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HorribleSubWrapper {

    private ArrayList<Anime> globalAnimeList;
    public HorribleSubWrapper(){
        globalAnimeList = WebUtils.fetchHorribleAnime();
    }

    public List<Anime> getGlobalAnimeList() {
        return globalAnimeList;
    }

    public void searchAnime(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Search for an anime:");

        String query = scanner.nextLine();
        ArrayList<Anime> animeList = WebUtils.searchAnimeList(query, this.globalAnimeList);
        System.out.println("\nSearch Results:\n------------------");

        for (int i=0; i<animeList.size(); i++) {
            System.out.println(i+1 + ". "+ animeList.get(i).getAnimeTitle());
        }

        System.out.println("\nWhich show would you like to access:");
        String option = scanner.nextLine();

        try{
            int choice = Integer.parseInt(option);
            showAnimeDetails(animeList.get(choice-1));
            System.out.println("go to show details from here.");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void showAnimeDetails(Anime anime) {

        WebUtils.fetchAnimeID(anime);
        WebUtils.fetchAnimeDetails(anime);

    }
}
