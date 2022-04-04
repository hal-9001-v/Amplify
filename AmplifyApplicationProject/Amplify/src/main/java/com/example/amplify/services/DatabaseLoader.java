package com.example.amplify.services;

import com.example.amplify.model.*;
import com.example.amplify.repositories.*;
import com.example.amplify.services.*;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


@Controller
public class DatabaseLoader {

    @Autowired
    SongRepository songRepo;
    @Autowired
    UserRepository userRepo;
    @Autowired
    ArtistRepository artistRepository;
    @Autowired
    AlbumRepository albumRepository;
    @Autowired
    PlaylistRepository playlistRepository;
    @Autowired
    SongFileRepository songFileRepo;
    @Autowired
    SongServices songServices;
    @Autowired
    UserServices userServices;
    @Autowired
    ArtistServices artistServices;
    @Autowired
    AlbumServices albumServices;
    @Autowired
    PlaylistServices playlistServices;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() throws IOException {

        /*
        userRepo.deleteAll();
        songRepo.deleteAll();
        artistRepository.deleteAll();
        albumRepository.deleteAll();
        playlistRepository.deleteAll();
         */

        User user = new User("dexaxi", passwordEncoder.encode("123"), "dexaxi12@gmail.com", "USER", "ADMIN");
        Artist artist0 = new Artist("Porta");
        Artist artist1 = new Artist("Kiko Rivera");
        Artist artist2 = new Artist("Imagine Dragons");
        Album album0 = new Album("He cambiado");
        Album album1 = new Album("No es Cuesion de Edades");
        Album album2 = new Album("Los Mejores Exitos de Kiko Rivera");
        Album album3 = new Album("Origins");

        artistRepository.save(artist0);
        artistRepository.save(artist1);
        artistRepository.save(artist2);
        albumRepository.save(album0);
        albumRepository.save(album1);
        albumRepository.save(album2);
        albumRepository.save(album3);

        String[] genre = new String[6];
        genre[0] = "CHILL";
        genre[1] = "GAMING";
        genre[2] = "SPORT";
        genre[3] = "INTIMATE";
        genre[4] = "DRIVING";
        genre[5] = "PODCAST";


        ArrayList<SongFile> sfs = new ArrayList<SongFile>();
        int songTotal = 10;
        for (int i = 0; i < songTotal; i++) {
            InputStream input1 = getClass().getClassLoader().getResourceAsStream("songs/Cancion1.ogg");
            sfs.add(new SongFile(BlobProxy.generateProxy(input1, input1.available()),null));
        }

        for (int i = 0; i < songTotal; i++) {
            Song s = new Song(GenerateSongName(), genre[(int) (Math.random() * 6)], null);
            switch ((int) (Math.random() * 4)) {
                case 0:
                    s.setAlbum(album0);
                    s.setArtist(artist0);
                    break;
                case 1:
                    s.setAlbum(album1);
                    s.setArtist(artist0);
                    break;
                case 2:
                    s.setAlbum(album2);
                    s.setArtist(artist1);
                    break;
                case 3:
                    s.setAlbum(album3);
                    s.setArtist(artist2);

                    break;

            }
            songRepo.save(s);
        }

        List<Song> allSongs = songServices.findAll();


        user.setSongs(allSongs);
        userRepo.save(user);

        artist0 = artistServices.findByName("Porta").get(0);
        artist1 = artistServices.findByName("Kiko Rivera").get(0);
        artist2 = artistServices.findByName("Imagine Dragons").get(0);
        album0 = albumServices.findByName("He cambiado").get(0);
        album1 = albumServices.findByName("No es Cuesion de Edades").get(0);
        album2 = albumServices.findByName("Los Mejores Exitos de Kiko Rivera").get(0);
        album3 = albumServices.findByName("Origins").get(0);

        Playlist playlist = new Playlist("Lofi Chillstep");
        playlist.setUser(user);
        Playlist playlist1 = new Playlist("Sadness and Sorrow MIX");
        playlist1.setUser(user);
        Playlist playlist2 = new Playlist("Mi musica");
        playlist2.setUser(user);

        ArrayList<Playlist> playlistList = new ArrayList<Playlist>();
        playlistList.add(playlist);
        playlistList.add(playlist1);
        playlistList.add(playlist2);
        user.setPlaylists(playlistList);

        ArrayList<Song> playList0songList = new ArrayList<>();
        ArrayList<Song> playlist1songList = new ArrayList<>();
        ArrayList<Song> playlist2songList = new ArrayList<>();
        ArrayList<Song> album0songList = new ArrayList<>();
        ArrayList<Song> album1songList = new ArrayList<>();
        ArrayList<Song> album2songList = new ArrayList<>();
        ArrayList<Song> album3songList = new ArrayList<>();

        for (int i = 0; i < songTotal/4; i++) {
            playList0songList.add(songServices.findAll().get(i));
            playlist1songList.add(songServices.findAll().get(i + songTotal/4));
            playlist2songList.add(songServices.findAll().get(i + songTotal/4+songTotal/4));
        }

        for (int i = 0; i < songTotal/8; i++) {
            album0songList.add(songServices.findAll().get(i));
            album1songList.add(songServices.findAll().get(i + songTotal/4));
            album2songList.add(songServices.findAll().get(i + songTotal/4 +songTotal/4));
            album3songList.add(songServices.findAll().get(i + songTotal/4+songTotal/4+songTotal/4));
        }


        playlist.setSongs(playList0songList);
        playlist1.setSongs(playlist1songList);
        playlist2.setSongs(playlist2songList);
        album0.setSongs(album0songList);
        album0.setArtist(artist0);
        album1.setSongs(album1songList);
        album1.setArtist(artist0);
        album2.setSongs(album2songList);
        album2.setArtist(artist1);
        album3.setSongs(album3songList);
        album3.setArtist(artist2);

        albumRepository.save(album0);
        albumRepository.save(album1);
        albumRepository.save(album2);
        albumRepository.save(album3);

        ArrayList<Album> albumList = new ArrayList<Album>();
        ArrayList<Artist> artistList = new ArrayList<Artist>();
        albumList.add(album0);
        albumList.add(album1);
        albumList.add(album3);
        artistList.add(artist0);
        artistList.add(artist1);
        artistList.add(artist2);

        user.setAlbums(albumList);
        user.setArtists(artistList);
        playlistRepository.save(playlist);
        playlistRepository.save(playlist1);
        playlistRepository.save(playlist2);
        userRepo.save(user);


        for (int i = 0; i < songTotal; i++) {
            sfs.get(i).setSong(allSongs.get(i));
            songFileRepo.save(sfs.get(i));
        }
    }

    private String GenerateSongName() {

        List<String> start = new ArrayList<>();
        List<String> end = new ArrayList<>();
        start.add("Oda al ");//1
        start.add("Himno de la ");//2
        start.add("Dias de ");//3
        start.add("Mil ");//4
        start.add("Carnaval de ");//5
        start.add("Cinco Noches en ");//6
        start.add("Limon y ");//7
        start.add("Epifania de ");//8
        start.add("Cancion de ");//9
        start.add("La ");//10
        start.add("Colores en ");//11

        end.add("olor corporal");//1
        end.add("volando bien alto");//2
        end.add("demonios");//3
        end.add("tontos");//4
        end.add("oceano de agonia");//5
        end.add("logevidad imputrefactible");//6
        end.add("hartura de queen");//7
        end.add("marzo");//8
        end.add("Bzrp Music Sessions, Vol.45");//9
        end.add("Kingslayer");//10
        end.add("la vida vendra");//11

        return start.get((int) (Math.random() * 11)) + end.get((int) (Math.random() * 11));


    }

}
