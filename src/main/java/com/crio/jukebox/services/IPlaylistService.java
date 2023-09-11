package com.crio.jukebox.services;

import java.util.List;
import com.crio.jukebox.entities.PlayList;

public interface IPlaylistService {

    PlayList createPlayList(String u_id, String playListName, List<String> songs);
    void deletePlayList(String userId, String playlistId);
    void playPlayList(String string, String string2);
    void addSong(String userId, String playlistId, List<String> songIdList);
    void deleteSong(String userId, String playlistId, List<String> songIdList);


}
    