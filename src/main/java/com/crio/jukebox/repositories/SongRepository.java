package com.crio.jukebox.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import com.crio.jukebox.entities.Song;

public class SongRepository implements ISongRepo{

    Map<String,Song> SongData;
    Integer _c=0;

    public SongRepository() {
        SongData=new HashMap<>();
    }


    public SongRepository(Map<String, Song> songData) {
        SongData = songData;
        _c=songData.size();
    }
    

    public Map<String, Song> getSongData() {
        return SongData;
    }


    public void setSongData(Map<String, Song> songData) {
        SongData = songData;
    }

    @Override
    public Song save(Song entity) {

        if (entity.getId() == null) {

            _c++;

            Song song = new Song(String.valueOf(_c), entity.getName() , entity.getGenre() , entity.getAlbumName() , entity.getArtist(),entity.getFeaturedArtist(),entity.getStatus());

            SongData.put(song.getId(), song);
            return song;
        } else {
            return SongData.get(entity.getId());

        }
    }

    @Override
    public List<Song> findAll() {

        return SongData.values().stream().collect(Collectors.toList());
    }

    @Override
    public Optional<Song> findById(String id) {
        return Optional.ofNullable(SongData.get(id));
    }

    @Override
    public boolean existsById(String id) {
        
        return SongData.containsKey(id);
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
