package databases.controllers.requestModels;

import java.util.List;

public class AlbumRequest {


    private Integer year;
    private List<Integer> songs;

    public AlbumRequest(){}

    public AlbumRequest(Integer year, List<Integer> songs) {
        this.year = year;
    }


    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public List<Integer> getSongs() {
        return songs;
    }

    public void setSongs(List<Integer> songs) {
        this.songs = songs;
    }
}
