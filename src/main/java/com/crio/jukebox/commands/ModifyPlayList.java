package com.crio.jukebox.commands;

import java.util.ArrayList;
import java.util.List;
import com.crio.jukebox.repositories.IPlaylistRepo;
import com.crio.jukebox.services.IPlaylistService;

public class ModifyPlayList implements ICommand{

    IPlaylistService Iplayservice;

    public ModifyPlayList(IPlaylistService iplayservice) {
        Iplayservice = iplayservice;
    }

    
    //Input can be 
    //[MODIFY-PLAYLIST,ADD-SONG,1,1,7]
    //[MODIFY-PLAYLIST,DELETE-SONG,1,1,7]

    @Override
    public void execute(List<String> tokens) {
        
        String choise=tokens.get(1);
        List<String> songInfo=new ArrayList<>();

        for(int i=4;i<tokens.size();i++){
            songInfo.add(tokens.get(i));
        }

        if(choise.equals("ADD-SONG")){
             Iplayservice.addSong(tokens.get(2), tokens.get(3), songInfo);
        }

        if(choise.equals("DELETE-SONG")){
            Iplayservice.deleteSong(tokens.get(2), tokens.get(3), songInfo);
        }
    }
}
