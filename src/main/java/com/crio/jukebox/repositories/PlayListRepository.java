package com.crio.jukebox.repositories;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.HashMap;
import com.crio.jukebox.entities.PlayList;

public class PlayListRepository implements IPlaylistRepo{

    Map<String,PlayList> playListData;
    Integer _c=0;

    public PlayListRepository(){
        
        this.playListData = new HashMap<>();

    }

    @Override
    public PlayList save(PlayList entity) {

        if (entity.getId() == null) {

            _c++;

            PlayList playlist = new PlayList(Integer.toString(_c),entity.getPlaylistname(),entity.getUser(),entity.getListOfSong());

            playListData.put(playlist.getId(), playlist);
            return playlist;
        } 
        
        playListData.put(entity.getId(), entity);
        return playListData.get(entity.getId());

        
    }

    @Override
    public List<PlayList> findAll() {

        return playListData.values().stream().collect(Collectors.toList());

    }

    @Override
    public Optional<PlayList> findById(String id) {

        return Optional.ofNullable(playListData.get(id));
    }

    @Override
    public boolean existsById(String id) {

        return playListData.containsKey(id);
    }

    @Override
    public void delete(PlayList entity) {
        
        playListData.values().stream().dropWhile(t->t.getId().equals(entity.getId()));
        playListData.remove(entity.getId());
    }

    @Override
    public void deleteById(String id) { 
        playListData.remove(id);
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return 0;
    }
    
}
