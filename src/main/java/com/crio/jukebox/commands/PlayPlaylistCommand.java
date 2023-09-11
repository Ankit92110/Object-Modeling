package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.services.IPlaylistService;

public class PlayPlaylistCommand implements ICommand{

    IPlaylistService Iplayservice;

    public PlayPlaylistCommand(IPlaylistService iplayservice) {
        Iplayservice = iplayservice;
    }

    // Input= ["PLAY-PLAYLIST" ,1 ,1]
    @Override
    public void execute(List<String> tokens) {
        
        Iplayservice.playPlayList(tokens.get(1),tokens.get(2));
        
    }

    


    
}
