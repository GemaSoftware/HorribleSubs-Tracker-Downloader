package models;

import java.util.UUID;

public class Episode {

    private Integer animeID;

    //Unique id for episode.
    private UUID episodeID;

    private String episodeNumber;

    private String episodeReleaseDate;



    public Episode(Anime anime, String episodeNumber) {
        episodeID = UUID.randomUUID();
        this.animeID = anime.getAnimeHSID();
        this.episodeNumber = episodeNumber;
    }

    //Add model keys for episode details found on HSUB API: Magnetlink, quality, episode number, etc.


    public Integer getAnimeID() {
        return animeID;
    }

    public UUID getEpisodeID() {
        return episodeID;
    }

    public String getEpisodeNumber() {
        return episodeNumber;
    }

    public String getEpisodeReleaseDate() {
        return episodeReleaseDate;
    }

    public void setEpisodeNumber(String episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public void setEpisodeReleaseDate(String episodeReleaseDate) {
        this.episodeReleaseDate = episodeReleaseDate;
    }
}
