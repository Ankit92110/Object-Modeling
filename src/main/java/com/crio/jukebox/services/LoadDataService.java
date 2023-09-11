package com.crio.jukebox.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.SongStatus;
import com.crio.jukebox.repositories.ILoadDataRepository;

public class LoadDataService implements IDataLoader {

    ILoadDataRepository Idatarepo = null;

    public LoadDataService(ILoadDataRepository idatarepo) {
        this.Idatarepo = idatarepo;
    }


    @Override
    public void LoadInDB(String filename) {

        BufferedReader br = null;

        try {

            br = new BufferedReader(new FileReader(filename));

            String inp = br.readLine();

            while (inp != null) {

                String[] row = inp.split(",");

                Song song = new Song(row[1], row[2], row[3], row[4],
                        Arrays.asList(row[5].split("#")), SongStatus.NO_ACTION);
                Idatarepo.save(song);
                inp = br.readLine();
            }

            System.out.println("Songs Loaded successfully");

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}
