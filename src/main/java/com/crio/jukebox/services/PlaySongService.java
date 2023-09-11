package com.crio.jukebox.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.crio.jukebox.entities.PlayList;
import com.crio.jukebox.entities.PlaylistStatus;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.SongStatus;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.exceptions.UserNotFoundException;
import com.crio.jukebox.repositories.IPlaylistRepo;
import com.crio.jukebox.repositories.IUserRepository;

public class PlaySongService implements IPlaySongService {

    IUserRepository userRepo;
    IPlaylistRepo playRepo;
    Map<Song, List<Song>> playSongData;
    Integer currentlyPlaying;

    public PlaySongService(IUserRepository userRepo, IPlaylistRepo playRepo) {
        this.userRepo = userRepo;
        this.playRepo = playRepo;
        this.playSongData = new HashMap<>();
    }

    public List<PlayList> MakeAGlobalPlayList(String userId) {

        User user = userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException());

        List<PlayList> playList =
                playRepo.findAll().stream()
                        .filter(t -> t.getStatus().equals(PlaylistStatus.Currently_Playing)
                                && t.getUser().getId().equals(user.getId()))
                        .collect(Collectors.toList());

        return playList;
    }

    @Override
    public void PlayNext(String userId) {

        List<PlayList> playList = MakeAGlobalPlayList(userId);

        List<Song> songList = playList.get(0).getListOfSong();

        currentlyPlaying = (currentlyPlaying + 1);
        Song nextSong = songList.get(currentlyPlaying % songList.size());

        nextSong.setStatus(SongStatus.PLAYING);

        System.out.println("Current Song Playing");
        System.out.println("Song - " + nextSong.getName());
        System.out.println("Album - " + nextSong.getAlbumName());
        System.out.println("Artists - " + String.join(",",
                nextSong.getFeaturedArtist().stream().collect(Collectors.toList())));

    }

    private Integer indexFinder(List<Song> songsList, Integer songId) {

        for (int i = 0; i < songsList.size(); i++) {

            if (songsList.get(i).getId().equals(String.valueOf(songId))) {
                return i;
            }
        }
        return -1;
    }


    @Override
    public void PlayPrev(String userId) {

        List<PlayList> playList = MakeAGlobalPlayList(userId);

        List<Song> songList = playList.get(0).getListOfSong();

        currentlyPlaying -= 1;

        Song prevsong = songList.get((currentlyPlaying) % songList.size());

        System.out.println("Current Song Playing");
        System.out.println("Song - " + prevsong.getName());
        System.out.println("Album - " + prevsong.getAlbumName());
        System.out.println("Artists - " + String.join(",",prevsong.getFeaturedArtist().stream().collect(Collectors.toList())));

    }

    @Override
    public void PlaySongByNumber(String userId, String songId) {

        List<PlayList> playList = MakeAGlobalPlayList(userId);

        currentlyPlaying = indexFinder(playList.get(0).getListOfSong(), Integer.valueOf(songId));

        if (currentlyPlaying > 0) {

            Song song = playList.get(0).getListOfSong().get(currentlyPlaying);

            System.out.println("Current Song Playing");
            System.out.println("Song - " + song.getName());
            System.out.println("Album - " + song.getAlbumName());
            System.out.println("Artists - " + String.join(",",
            song.getFeaturedArtist().stream().collect(Collectors.toList())));

        } else {
            System.out.println("Given song id is not a part of the active playlist");
        }

    }

}
