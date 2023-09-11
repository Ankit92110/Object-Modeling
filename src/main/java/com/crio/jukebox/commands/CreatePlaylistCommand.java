package com.crio.jukebox.commands;

import java.util.ArrayList;
import java.util.List;
import javax.swing.text.PlainDocument;
import com.crio.jukebox.entities.PlayList;
import com.crio.jukebox.services.IPlaylistService;

public class CreatePlaylistCommand implements ICommand{

    IPlaylistService iplayservice;

    public CreatePlaylistCommand(IPlaylistService iplayservice) {
        this.iplayservice = iplayservice;
    }

    // Input Format: [CREATE-PLAYLIST,1,MY_PLAYLIST_1,1,4,5,6]

    @Override
    public void execute(List<String> tokens) {  

        String u_id=tokens.get(1);
        String playListName=tokens.get(2);

        List<String> songs=new ArrayList<>();

        for(int i=3 ;i<tokens.size();i++){

            songs.add(tokens.get(i));
        }
        
        PlayList playlist=iplayservice.createPlayList(u_id,playListName,songs);
        System.out.println(playlist);
        
    }

    

  
    

    
}
