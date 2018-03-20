package databases;

import javax.persistence.*;
import java.util.List;

@Entity
public class Album {

    @Id
    @GeneratedValue
    private Integer id;

    private Integer year;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Song> songs;

    public Album(Integer year, List<Song> songs) {
        this.year = year;
        this.songs = songs;
    }


    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
}
