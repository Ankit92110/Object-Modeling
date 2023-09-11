package com.crio.jukebox.entities;

import java.util.List;

public class PlayList extends BaseEntity{

    String playlistname;
    User user;
    List<Song> listOfSong;
    PlaylistStatus status;


    public PlayList(String id,String playlistname,User user,List<Song> songlist){
        this(playlistname,user,songlist);
        this.id=id;
        this.status=PlaylistStatus.Currently_Stopped;
    }

    public PlayList(String playlistname, User user, List<Song> listOfSong) {
        this.playlistname = playlistname;
        this.user = user;
        this.listOfSong = listOfSong;
        this.status=PlaylistStatus.Currently_Stopped;
    }

    public String getPlaylistname() {
        return playlistname;
    }

    public List<Song> getListOfSong() {
        return listOfSong;
    }

    public User getUser() {
        return user;
    }

    public PlaylistStatus getStatus() {
        return status;
    }

    public void setStatus(PlaylistStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Playlist ID - "+id;
    }

    
}
