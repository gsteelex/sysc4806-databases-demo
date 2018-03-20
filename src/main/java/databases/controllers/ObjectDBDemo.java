package databases.controllers;

import databases.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
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
        Album album = new Album(1985, new ArrayList<>());

        database.getTransaction().begin();

        database.persist(album);

        database.getTransaction().commit();

        TypedQuery<Album> albumsQuery = database.createQuery("SELECT album FROM Album album", Album.class);

        return albumsQuery.getResultList();
    }

}
