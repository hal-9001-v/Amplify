package com.example.amplify.services;

import com.example.amplify.model.*;
import com.example.amplify.repositories.PlaylistRepository;
import com.example.amplify.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserServices {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PlaylistRepository playlistRepo;

    @Autowired
    PlaylistServices playlistServices;

    @Transactional(readOnly = true)
    public Optional<User> findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Transactional(readOnly = true)
    public List<User> findAllByUsername(String username, Pageable page) {
        return userRepo.findAllByUsername(username, page);
    }

    @Transactional(readOnly = true)
    public List<User> findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Transactional
    public void addSong(Song song, User user) {

        List<Song> allUserSongs = user.getSongs();

        if (user.getSongs() != null) {
            for (Song s : user.getSongs()) {
                if (s.getTitle().equals(song.getTitle())) return;
            }
        } else {
            allUserSongs = new ArrayList<Song>();
        }

        allUserSongs.add(song);
        user.setSongs(allUserSongs);
        userRepo.save(user);
    }

    @Transactional
    public void addPlaylist(Playlist playlist, User user) {

        List<Playlist> allUserPlaylists = user.getPlaylists();

        if (user.getPlaylists() != null) {
            for (Playlist p : user.getPlaylists()) {
                if (p.getName().equals(playlist.getName())) {
                    return;
                }
            }
        } else {
            allUserPlaylists = new ArrayList<Playlist>();
        }

        allUserPlaylists.add(playlist);
        user.setPlaylists(allUserPlaylists);
        userRepo.save(user);
    }

    @Transactional
    public void addArtist(Artist artist, User user) {
        List<Artist> allUserArtists = user.getArtists();

        if (user.getArtists() != null) {
            for (Artist a : user.getArtists()) {
                if (a.getName().equals(artist.getName())) return;
            }
        } else {
            allUserArtists = new ArrayList<Artist>();
        }

        allUserArtists.add(artist);
        user.setArtists(allUserArtists);
        userRepo.save(user);
    }

    @Transactional
    public void addAlbum(Album album, User user) {
        List<Album> allUserAlbums = user.getAlbums();

        if (user.getAlbums() != null) {
            for (Album a : user.getAlbums()) {
                if (a.getName().equals(album.getName())) return;
            }
        } else {
            allUserAlbums = new ArrayList<Album>();
        }

        allUserAlbums.add(album);
        user.setAlbums(allUserAlbums);
        userRepo.save(user);
    }

    @Transactional
    public void removePlaylist(Playlist playlist, User user) {

        List<Playlist> allUserPlaylists = user.getPlaylists();
        Playlist removedPlaylist = null;
        if (user.getPlaylists() != null) {
            for (Playlist p : allUserPlaylists) {
                if (p.getName().equals(playlist.getName())) {

                    removedPlaylist = p;

                }
            }
        }

        if (removedPlaylist == null) return;
        allUserPlaylists.remove(removedPlaylist);
        user.setPlaylists(allUserPlaylists);
        userRepo.save(user);

    }

    @Transactional
    public void removeAlbum(Album album, User user) {

        List<Album> allUserAlbums = user.getAlbums();
        Album removedAlbum = null;
        if (user.getAlbums() != null) {
            for (Album a : allUserAlbums) {
                if (a.getName().equals(album.getName())) {
                    removedAlbum = a;
                }
            }
        }

        if (removedAlbum == null) return;
        allUserAlbums.remove(removedAlbum);
        user.setAlbums(allUserAlbums);
        userRepo.save(user);
    }

    @Transactional
    public void removeArtist(Artist artist, User user) {

        List<Artist> allUserArtists = user.getArtists();
        Artist removedArtist = null;
        if (user.getArtists() != null) {
            for (Artist a : allUserArtists) {
                if (a.getName().equals(artist.getName())) {

                    removedArtist = a;
                }
            }
        }

        if (removedArtist == null) return;
        allUserArtists.remove(removedArtist);
        user.setArtists(allUserArtists);
        userRepo.save(user);

    }

    @Transactional
    public void removeSong(Song song, User user) {

        List<Song> allUserSongs = user.getSongs();
        Song removedSong = null;
        if (user.getSongs() != null) {
            for (Song s : allUserSongs) {
                if (s.getTitle().equals(song.getTitle())) {

                    removedSong = s;
                }
            }
        }

        if (removedSong == null) return;
        allUserSongs.remove(removedSong);
        user.setSongs(allUserSongs);
        userRepo.save(user);
    }

    @Transactional(readOnly = true)
    public String getFavouriteGenre(User user) {

        List<Song> songList = findByUsername(user.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found")).getSongs();

        ArrayList<String> genres = new ArrayList<String>();

        for (Song s : songList) {
            genres.add(s.getGenre());
        }

        String g = maxOccurs(genres);

        if(!g.isEmpty()) return g;

        return "No hay datos suficientes :(";
    }

    @Transactional(readOnly = true)
    public String getFavouriteArtist(User user) {

        List<Song> songList = findByUsername(user.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found")).getSongs();

        ArrayList<String> songArtists = new ArrayList<String>();

        for (Song s : songList) {
            songArtists.add(s.getArtist().getName());
        }

        return maxOccurs(songArtists);
    }

    public void UpdateDownloads(User user){
        User u = userRepo.findByUsername(user.getUsername()).get();
        u.setTotalSongsDownloaded(u.getTotalSongsDownloaded()+1);
        userRepo.save(u);
    }

    public static <T> T maxOccurs(List<T> list) {
        Map<T, Integer> map = new HashMap<>();

        for (T t : list) {
            Integer value = map.get(t);
            map.put(t, value == null ? 1 : value + 1);
        }

        Map.Entry<T, Integer> max = null;

        for (Map.Entry<T, Integer> i : map.entrySet()) {
            if (max == null || i.getValue() > max.getValue())
                max = i;
        }
        return max.getKey();
    }



    public static Object checkLogged() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
