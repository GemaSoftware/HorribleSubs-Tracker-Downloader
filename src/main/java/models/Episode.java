package models;

import java.util.HashMap;
import java.util.UUID;

public class Episode {

    private Integer animeID;

    //Unique id for episode.
    private UUID episodeID;

    private String episodeNumber;

    private String episodeReleaseDate;

    private HashMap<String, String> episodeMagnetLink;

    public Episode(Anime anime, String episodeNumber) {
        episodeID = UUID.randomUUID();
        this.animeID = anime.getAnimeHSID();
        this.episodeNumber = episodeNumber;
        this.episodeMagnetLink = new HashMap<String, String>();
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

    public void addLink(String id, String attr) {
        this.episodeMagnetLink.put(id,attr);
    }

    public HashMap<String, String> getEpisodeMagnetLink() {
        return episodeMagnetLink;
    }
}
