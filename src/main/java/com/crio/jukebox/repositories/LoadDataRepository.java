package com.crio.jukebox.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.crio.jukebox.entities.Song;

public class LoadDataRepository implements ILoadDataRepository{

    Map<String,Song> songMap;
    Integer count=0;

    public LoadDataRepository(){
        songMap=new HashMap<>();
    }

    public LoadDataRepository(Map<String, Song> songMap) {
        this.songMap = songMap;
    }

    @Override
    public Song save(Song entity) {
        
        if (entity.getId() == null) {
            count++;

            Song song = new Song(String.valueOf(count), entity.getName() , entity.getGenre() , entity.getAlbumName() , entity.getArtist(),entity.getFeaturedArtist(),entity.getStatus());

            songMap.put(song.getId(), song);
            return song;

        } else {
            return songMap.get(entity.getId());

        }
    }

    @Override
    public List<Song> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<Song> findById(String id) {
        // TODO Auto-generated method stub
        return Optional.ofNullable(songMap.get(id));
    }

    @Override
    public boolean existsById(String id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void delete(Song entity) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteById(String id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return 0;
    }

    
}
