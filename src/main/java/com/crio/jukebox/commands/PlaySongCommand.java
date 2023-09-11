package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.services.IPlaySongService;

public class PlaySongCommand implements ICommand{

    IPlaySongService Iplayservice;

    public PlaySongCommand(IPlaySongService iplayservice) {
        Iplayservice = iplayservice;
    }
    

    //Input :

    // [PLAY-SONG 1 5] or 
    // [PLAY-SONG 1 NEXT] or 
    // [PLAY-SONG 1 NEXT] or 
    // [PLAY-SONG 1 BACK ] or 
    // [PLAY-SONG 1 BACK] or 
    // [PLAY-SONG 1 19]
    
    @Override
    public void execute(List<String> tokens) {

        if(tokens.get(2).equals("NEXT"))
            Iplayservice.PlayNext(tokens.get(1));

        else if(tokens.get(2).equals("BACK"))
            Iplayservice.PlayPrev(tokens.get(1));

        else
            Iplayservice.PlaySongByNumber(tokens.get(1), tokens.get(2));
    }
}
