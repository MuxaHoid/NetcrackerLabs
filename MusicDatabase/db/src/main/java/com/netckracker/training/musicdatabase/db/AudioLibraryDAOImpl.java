package com.netckracker.training.musicdatabase.db;

import com.netcracker.training.musicdatabase.model.Album;
import com.netcracker.training.musicdatabase.model.Artist;
import com.netcracker.training.musicdatabase.model.Genre;
import com.netcracker.training.musicdatabase.model.Track;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by MuxaHoid on 19.11.2014.
 */
public class AudioLibraryDAOImpl implements AudioLibraryDAO{


    @Override
    public List<Track> getTracks(String title, String artist, String album, String genre) {
        List<Track>tracks = new ArrayList<Track>();;
        Session session = null;
        try {
            if(title.equals(""))title="%";
            if(album.equals(""))album="%";
            if(genre.equals(""))genre="%";
            if(artist.equals(""))artist="%";
            title.replaceAll("\\*","_");
            title.replaceAll("\\?","%");
            artist.replaceAll("\\*","_");
            artist.replaceAll("\\?","%");
            album.replaceAll("\\*","_");
            album.replaceAll("\\?","%");
            genre.replaceAll("\\*","_");
            genre.replaceAll("\\?","%");
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            tracks = session
                    .createQuery(" from Track " +
                         // "join t.albums a " +
                           "where title like :title " //+
                          // "and t.artist.name like :artistname "+
                          // "and t.genre.name like :genrename "//+
                          // "and a.title like :albumtitle"
                    )
                    .setParameter("title", title)
                   // .setParameter("artistname",artist)
                   // .setParameter("genrename",genre)
                    //.setParameter("albumtitle",album)
                    .list();
            session.getTransaction().commit();

        } catch (Exception e) {
            System.out.println( e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return tracks;
    }

    @Override
    public void updateTrack(Track track, String[] albums) {
        Session session = null;
        List<Album>newAlbums = new ArrayList<Album>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            newAlbums = session
                    .createQuery("from Album a where a.title in :albums")
                    .setParameterList("albums",albums)
                    .list();
            track.getAlbums().addAll(newAlbums);
            for(int i = 0; i<albums.length;i++){
                track.getAlbums().add(new Album(albums[i]));
            }
            session.update(track);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println( e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void addTrack(Track track, String[] albums) {
        Session session = null;
        List<Album>newAlbums = new ArrayList<Album>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            newAlbums = session
                    .createQuery("from Album a where a.title in :albums")
                    .setParameterList("albums",albums)
                    .list();
            track.getAlbums().addAll(newAlbums);
            for(int i = 0; i<albums.length;i++){
                track.getAlbums().add(new Album(albums[i]));
            }
            session.save(track);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println( e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void removeTrack(Long trackId) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Track track =(Track) session.get(Track.class,trackId);
            session.delete(track);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println( e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public Genre getGenreByName(String name){
        Session session = null;
        Genre genre = new Genre(name);
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            List<Genre> results = session
                    .createQuery("from Genre a where a.name in :genrename")
                    .setParameter("genrename",name)
                    .list();
            if(!results.isEmpty())
                genre = results.get(0);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println( e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
                return genre;
            }
        }
        return genre;
    }
    public Artist getArtistByName(String name){
        Session session = null;
        Artist artist = new Artist(name);
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            List<Artist> results = session
                    .createQuery("from Artist a where a.name in :artistname")
                    .setParameter("artistname",name)
                    .list();
            if(!results.isEmpty())
                artist = results.get(0);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println( e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
                return artist;
            }
        }
        return artist;
    }
}