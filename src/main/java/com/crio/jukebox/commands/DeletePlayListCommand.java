package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.services.IPlaylistService;


public class DeletePlayListCommand implements ICommand{

    IPlaylistService Iplayservice;


    public DeletePlayListCommand(IPlaylistService iplayservice) {
        Iplayservice = iplayservice;
    }


    @Override
    public void execute(List<String> tokens) {
        
        Iplayservice.deletePlayList(tokens.get(1), tokens.get(2));

    }
        
  }
