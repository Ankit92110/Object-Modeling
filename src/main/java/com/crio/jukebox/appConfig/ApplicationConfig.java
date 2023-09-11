package com.crio.jukebox.appConfig;


import com.crio.jukebox.commands.CommandInvoker;
import com.crio.jukebox.commands.CreatePlaylistCommand;
import com.crio.jukebox.commands.CreateUserCommand;
import com.crio.jukebox.commands.DeletePlayListCommand;
import com.crio.jukebox.commands.LoadDataCommand;
import com.crio.jukebox.commands.ModifyPlayList;
import com.crio.jukebox.commands.PlayPlaylistCommand;
import com.crio.jukebox.commands.PlaySongCommand;
import com.crio.jukebox.repositories.ILoadDataRepository;
import com.crio.jukebox.repositories.IPlaylistRepo;
import com.crio.jukebox.repositories.ISongRepo;
import com.crio.jukebox.repositories.IUserRepository;
import com.crio.jukebox.repositories.LoadDataRepository;
import com.crio.jukebox.repositories.PlayListRepository;
import com.crio.jukebox.repositories.SongRepository;
import com.crio.jukebox.repositories.UserRepository;
import com.crio.jukebox.services.IDataLoader;
import com.crio.jukebox.services.IPlaySongService;
import com.crio.jukebox.services.IPlaylistService;
import com.crio.jukebox.services.IUserService;
import com.crio.jukebox.services.LoadDataService;
import com.crio.jukebox.services.PlayListService;
import com.crio.jukebox.services.PlaySongService;
import com.crio.jukebox.services.UserService;

public class ApplicationConfig {

    // Repository

    private final ILoadDataRepository irepo=new LoadDataRepository();
    private final IPlaylistRepo iplayrepo=new PlayListRepository();
    private final ISongRepo isongrepo=new SongRepository();
    private final IUserRepository iuserrepo=new UserRepository();


    //Service

    private final IDataLoader iDataLoader=new LoadDataService(irepo);
    private final IPlaylistService iplaylistServices=new PlayListService(iuserrepo,irepo,iplayrepo);
    private final IPlaySongService iplaySongService=new PlaySongService(iuserrepo,iplayrepo);
    private final IUserService iuserService=new UserService(iuserrepo);

    //Command

    private final CreatePlaylistCommand createPlayListCommand =new CreatePlaylistCommand(iplaylistServices);
    private final CreateUserCommand createUserComamnd =new CreateUserCommand(iuserService);
    private final DeletePlayListCommand deletePlayListCommand =new DeletePlayListCommand(iplaylistServices);
    private final LoadDataCommand loadDataCommand = new LoadDataCommand(iDataLoader);
    private final ModifyPlayList modifyPlayListCommand = new ModifyPlayList(iplaylistServices);
    private final PlayPlaylistCommand playlistCommand =new PlayPlaylistCommand(iplaylistServices);
    private final PlaySongCommand playSongCommand = new PlaySongCommand(iplaySongService);
    
    //Command Invoker

    private final CommandInvoker commandInvoker = new CommandInvoker();

    public CommandInvoker getCommandInvoker() {

        commandInvoker.register("LOAD-DATA", loadDataCommand);
        commandInvoker.register("CREATE-USER",createUserComamnd);
        commandInvoker.register("CREATE-PLAYLIST", createPlayListCommand);
        commandInvoker.register("DELETE-PLAYLIST", deletePlayListCommand);
        commandInvoker.register("PLAY-PLAYLIST", playlistCommand);
        commandInvoker.register("MODIFY-PLAYLIST", modifyPlayListCommand);
        commandInvoker.register("PLAY-SONG", playSongCommand);

        return commandInvoker;
    }

}
