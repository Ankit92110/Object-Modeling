package com.crio.jukebox.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.crio.codingame.exceptions.UserNotFoundException;
import com.crio.jukebox.entities.PlayList;
import com.crio.jukebox.entities.PlaylistStatus;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.exceptions.PlayListNotFound;
import com.crio.jukebox.repositories.ILoadDataRepository;
import com.crio.jukebox.repositories.IPlaylistRepo;
import com.crio.jukebox.repositories.IUserRepository;
import com.crio.jukebox.exceptions.SongNotFoundException;

public class PlayListService implements IPlaylistService{

    IUserRepository Irepo;
    ILoadDataRepository songRepository;
    IPlaylistRepo IplayRepo;

    public PlayListService(IUserRepository irepo, ILoadDataRepository songRepo, IPlaylistRepo iplayRepo) {
        Irepo = irepo;
        songRepository = songRepo;
        IplayRepo = iplayRepo;
    }

    @Override
    public PlayList createPlayList(String u_id, String playListName, List<String> songs) {


       User user = Irepo.findById(u_id).orElseThrow( ()-> new PlayListNotFound());

       List<Song> songData = new ArrayList<>();
        
       for(int i=0;i< songs.size();i++){

           Song song = songRepository.findById(songs.get(i)).orElseThrow(()->new SongNotFoundException("Some Requested Songs Not Available. Please try again"));
           
           songData.add(song);

       }

       PlayList playlist=new PlayList(playListName, user, songData);
       playlist.setStatus(PlaylistStatus.Currently_Stopped);
       
       return IplayRepo.save(playlist);
       
    }

    @Override
    public void deletePlayList(String userId, String playlistId) {
        
          IplayRepo.findById(playlistId).orElseThrow(()->new PlayListNotFound("Playlist Not Found"));

          Irepo.findById(userId).orElseThrow(()-> new UserNotFoundException());
          
          IplayRepo.deleteById(playlistId);
          System.out.println("Delete Successful");
          
    }

    @Override
    public void playPlayList(String userId, String playlistId) {

        PlayList playlist= IplayRepo.findById(playlistId).orElseThrow(()->new PlayListNotFound("Playlist is empty"));

        playlist.setStatus(PlaylistStatus.Currently_Playing);

        IplayRepo.save(playlist);

        Song song = playlist.getListOfSong().get(0);
        
        System.out.println("Current Song Playing");
        System.out.println("Song - "+song.getName());
        System.out.println("Album - "+song.getAlbumName());
        System.out.println("Artists - "+ String.join(",", song.getFeaturedArtist().stream().collect(Collectors.toList())));

    }

    @Override
    public void addSong(String userId, String playlistId, List<String> songIdList) {
        
        PlayList playlist=IplayRepo.findById(playlistId).orElseThrow(()-> new PlayListNotFound());
        
        List<String> oldSongIdData = playlist.getListOfSong().stream().map(t->t.getId()).collect(Collectors.toList());
        
        for(int i=0;i<songIdList.size();i++){


           if(!oldSongIdData.contains(songIdList.get(i))){
                
                Song song=songRepository.findById(songIdList.get(i)).orElseThrow(()-> new SongNotFoundException("Some Requested Songs Not Available. Please try again"));
                playlist.getListOfSong().add(song);
                IplayRepo.save(playlist);

           }   
        }

        System.out.println("Playlist ID - "+playlist.getId());
        System.out.println("Playlist Name - "+playlist.getPlaylistname());
        System.out.println("Song IDs - "+String.join(" ", playlist.getListOfSong().stream().map(t->t.getId()).collect(Collectors.toList())));
    }

    @Override
    public void deleteSong(String userId, String playlistId, List<String> songIdList) {

        PlayList playlist=IplayRepo.findById(playlistId).orElseThrow(()->new PlayListNotFound());

        for(String ids: songIdList){
            
            if(!playlist.getListOfSong().stream().map(t->t.getId()).collect(Collectors.toList()).contains(ids))
                throw new SongNotFoundException("Some Requested Songs for Deletion are not present in the playlist. Please try again");

            
            playlist.getListOfSong().removeIf(t->t.getId().equals(ids));
            IplayRepo.save(playlist);
        }

        System.out.println("Playlist ID - "+playlist.getId());
        System.out.println("Playlist Name - "+playlist.getPlaylistname());
        System.out.println("Song IDs - "+String.join(" ", playlist.getListOfSong().stream().map(t->t.getId()).collect(Collectors.toList())));
    }
}
