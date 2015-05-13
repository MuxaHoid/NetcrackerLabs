package com.netckracker.training.musicdatabase.db;

import com.netcracker.training.musicdatabase.model.*;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by MuxaHoid on 19.11.2014.
 */
public class AudioLibraryDAOImpl implements AudioLibraryDAO {


    public List<Track> getTracks(String title, String artist, String album, String genre) {
        List<Track> tracks = new ArrayList<Track>();
        ;
        Session session = null;
        try {
            if (title.equals("")) title = "%";
            if (album.equals("")) album = "%";
            if (genre.equals("")) genre = "%";
            if (artist.equals("")) artist = "%";
            title.replaceAll("\\*", "_");
            title.replaceAll("\\?", "%");
            artist.replaceAll("\\*", "_");
            artist.replaceAll("\\?", "%");
            album.replaceAll("\\*", "_");
            album.replaceAll("\\?", "%");
            genre.replaceAll("\\*", "_");
            genre.replaceAll("\\?", "%");
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
            System.out.println(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return tracks;
    }

    public void updateTrack(Track track, String[] albums) {
        Session session = null;
        List<Album> newAlbums = new ArrayList<Album>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            newAlbums = session
                    .createQuery("from Album a where a.title in :albums")
                    .setParameterList("albums", albums)
                    .list();
            track.getAlbums().addAll(newAlbums);
            for (int i = 0; i < albums.length; i++) {
                track.getAlbums().add(new Album(albums[i]));
            }
            session.update(track);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void addTrack(Track track, String[] albums) {
        Session session = null;
        List<Album> newAlbums = new ArrayList<Album>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            newAlbums = session
                    .createQuery("from Album a where a.title in :albums")
                    .setParameterList("albums", albums)
                    .list();
            track.getAlbums().addAll(newAlbums);
            for (int i = 0; i < albums.length; i++) {
                track.getAlbums().add(new Album(albums[i]));
            }
            session.save(track);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void removeTrack(Long trackId) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Track track = (Track) session.get(Track.class, trackId);
            session.delete(track);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public User getUser(Long userID) {
        User result = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            result = (User) session.get(User.class, userID);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return result;
    }

    public User getUserByName(String name) {
        Session session = null;
        User user = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            List<User> results = session
                    .createQuery("from User a where a.name in :username")
                    .setParameter("username", name)
                    .list();
            if (!results.isEmpty())
                user = results.get(0);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
                return user;
            }
        }
        return user;
    }

    public void addUser(User user) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void updateUser(User user) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void removeUser(Long userID) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            User user = (User) session.get(User.class, userID);
            session.delete(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void removeArtist(Long artistId) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Artist artist = (Artist) session.get(Artist.class, artistId);
            session.delete(artist);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void removeAlbum(Long albumId) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Album album = (Album) session.get(Album.class, albumId);
            session.delete(album);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public Genre getGenreByName(String name) {
        Session session = null;
        Genre genre = new Genre(name);
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            List<Genre> results = session
                    .createQuery("from Genre a where a.name in :genrename")
                    .setParameter("genrename", name)
                    .list();
            if (!results.isEmpty())
                genre = results.get(0);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
                return genre;
            }
        }
        return genre;
    }

    public Artist getArtistByName(String name) {
        Session session = null;
        Artist artist = new Artist(name);
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            List<Artist> results = session
                    .createQuery("from Artist a where a.name in :artistname")
                    .setParameter("artistname", name)
                    .list();
            if (!results.isEmpty())
                artist = results.get(0);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
                return artist;
            }
        }
        return artist;
    }

    public List<Artist> getArtistsByName(String name) {
        Session session = null;
        List<Artist> artist = new ArrayList<Artist>();
        try {
            if (name.equals("")) name = "%";
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            List<Artist> results = session
                    .createQuery("from Artist a where a.name like :artistname")
                    .setParameter("artistname", name)
                    .list();
            if (!results.isEmpty())
                artist = results;
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
                return artist;
            }
        }
        return artist;
    }

    public List<Track> getTrackByAlbum(Long id) {
        List<Track> tracks = new ArrayList<Track>();
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            tracks = session
                    .createQuery("select t from Track t " +
                                    "join t.albums a " +
                                    "where a.id = :albumid"
                    )
                    .setLong("albumid", id)
                    .list();
            session.getTransaction().commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return tracks;
    }

    public List<Track> getTrackByArtist(Long id) {
        List<Track> tracks = new ArrayList<Track>();
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            tracks = session
                    .createQuery("select t from Track t " +
                                    "where t.artist = :artistid"
                    )
                    .setLong("artistid", id)
                    .list();
            session.getTransaction().commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return tracks;
    }

    public List<Album> getAlbumsByTitle(String title) {
        List<Album> result = new ArrayList<Album>();
        Session session = null;
        try {
            if (title.equals("")) title = "%";
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            result = session
                    .createQuery("select a from Album a " +
                                    "where a.title like :albumtitle"
                    )
                    .setParameter("albumtitle", title)
                    .list();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return result;
    }

    public Track getTrackByID(Long id) {
        Track result = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            result = (Track) session.get(Track.class, id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return result;
    }

    public Album getAlbumByID(Long id) {
        Album result = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            result = (Album) session.get(Album.class, id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return result;
    }

    public Artist getArtistByID(Long id) {
        Artist result = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            result = (Artist) session.get(Artist.class, id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return result;
    }

    public void updateArtist(Artist artist) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(artist);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void addArtist(Artist artist) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(artist);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void updateAlbum(Album album) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(album);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void addAlbum(Album album) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(album);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

}
