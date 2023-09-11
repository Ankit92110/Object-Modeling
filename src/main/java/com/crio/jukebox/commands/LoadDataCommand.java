package com.crio.jukebox.commands;

import java.util.List;
import com.crio.jukebox.services.IDataLoader;

public class LoadDataCommand implements ICommand{

    IDataLoader dataLoader;

    public LoadDataCommand(IDataLoader dataLoader) {
        this.dataLoader = dataLoader;
    }

    // Input format: ["LOAD-DATA","songs.csv"]
    @Override
    public void execute(List<String> tokens) {

        dataLoader.LoadInDB(tokens.get(1));
    }

    
}
