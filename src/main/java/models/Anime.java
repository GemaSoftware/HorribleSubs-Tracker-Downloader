package models;

import java.util.ArrayList;

public class Anime {

    private String animeLink;

    private String animeTitle;

    private Integer animeHSID;

    private ArrayList<Episode> animeEpisodeList;

    public Anime(String link, String title) {
        this.animeLink = link;
        this.animeTitle = title;
        animeEpisodeList = new ArrayList<Episode>();
    }

    //Future - add information about specific anime found on horrible subs. With location if downloaded, horriblesubsID, episodes, etc


    public String getAnimeLink() {
        return animeLink;
    }

    public void setAnimeLink(String animeLink) {
        this.animeLink = animeLink;
    }

    public String getAnimeTitle() {
        return animeTitle;
    }

    public void setAnimeTitle(String animeTitle) {
        this.animeTitle = animeTitle;
    }

    public Integer getAnimeHSID() {
        return animeHSID;
    }

    public void setAnimeHSID(Integer animeHSID) {
        this.animeHSID = animeHSID;
    }


    public void addAnimeToList(Episode episode) {
        this.animeEpisodeList.add(episode);
    }

    public ArrayList<Episode> getAnimeEpisodeList() {
        return animeEpisodeList;
    }

    @Override
    public String toString() {
        return "Anime{" +
                "animeLink='" + animeLink + '\'' +
                ", animeTitle='" + animeTitle + '\'' +
                ", animeHSID=" + animeHSID +
                ", animeEpisodeList=" + animeEpisodeList +
                "}";
    }
}
