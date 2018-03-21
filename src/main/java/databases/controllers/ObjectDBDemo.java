package databases.controllers;

import databases.AcousticSong;
import databases.Album;
import databases.Song;
import databases.controllers.requestModels.AlbumRequest;
import databases.controllers.requestModels.SongRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@RestController
@RequestMapping("/objectDB")
public class ObjectDBDemo {

    private EntityManager database;

    public ObjectDBDemo(@Autowired EntityManagerFactory databaseFactory) {
        this.database = databaseFactory.createEntityManager();
    }

    @RequestMapping(value = "/sampleGetAll", method= RequestMethod.GET)
    public List<Album> sampleGetAllAlbums() {
        Album album = new Album(1985);

        database.getTransaction().begin();

        database.persist(album);

        database.getTransaction().commit();

        TypedQuery<Album> albumsQuery = database.createQuery("SELECT album FROM Album album", Album.class);

        return albumsQuery.getResultList();
    }


    @RequestMapping(value = "/albums", method = RequestMethod.POST)
    public Album createAlbum(@RequestBody AlbumRequest albumRequest) {
        Album album = new Album(albumRequest.getYear());

        for (Integer songId: albumRequest.getSongs()) {
            TypedQuery<Song> songQuery = database.createQuery("SELECT song FROM Song song WHERE song.id = " + songId, Song.class);

            album.addSong(songQuery.getSingleResult());
        }

        database.getTransaction().begin();
        database.persist(album);
        database.getTransaction().commit();

        return album;
    }

    @RequestMapping(value = "/albums", method = RequestMethod.GET)
    public List<Album> getAlbums() {
        TypedQuery<Album> albumsQuery = database.createQuery("SELECT album FROM Album album", Album.class);
        return albumsQuery.getResultList();
    }

    @RequestMapping(value= "/albums/count", method = RequestMethod.GET)
    public Long getAlbumCount() {
        Query albumsQuery = database.createQuery("SELECT COUNT(album.year) FROM Album album");
        return (Long) albumsQuery.getSingleResult();
    }

    @RequestMapping(value = "/songs", method = RequestMethod.POST)
    public Song createSong(@RequestBody SongRequest songRequest) {
        Song song = new Song(songRequest.getName());

        database.getTransaction().begin();
        database.persist(song);
        database.getTransaction().commit();

        return song;
    }

    @RequestMapping(value = "/songs", method = RequestMethod.GET)
    public List<Song> getSongs() {
        TypedQuery<Song> songsQuery = database.createQuery("SELECT song FROM Song song", Song.class);
        return songsQuery.getResultList();
    }

    @RequestMapping(value = "/songs/acoustic", method = RequestMethod.POST)
    public AcousticSong createSampleAcousticSong() {
        AcousticSong song = new AcousticSong();
        song.setInstrument("banjo");
        song.setName("sample");

        database.getTransaction().begin();
        database.persist(song);
        database.getTransaction().commit();

        return song;
    }

    @RequestMapping(value = "/songs/acoustic", method = RequestMethod.GET)
    public List<AcousticSong> getAcousticSongs() {
        TypedQuery<AcousticSong> songsQuery = database.createQuery("SELECT song FROM AcousticSong song", AcousticSong.class);
        return songsQuery.getResultList();
    }
}
