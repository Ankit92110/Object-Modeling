package com.crio.jukebox.services;

public interface IPlaySongService{
    
    void PlayNext(String userId);
    void PlayPrev(String userId);
    void PlaySongByNumber(String userId,String songNo);

}
