package MediaCatalog.consolecommands.commands;

import java.util.List;

import MediaCatalog.MediaFiles;
import MediaCatalog.Utils.RepFactory;
import MediaCatalog.consolecommands.Operation;
import MediaCatalog.services.RepException;

public class Move implements Operation {
    @Override
    public String getName() {
        return "move";
    }
    //what to do if user called help for this command
    @Override
    public void showHelp() {
        System.out.println("move {old path} {new path}");
    }

    @Override
    public void execute(List<String> params) {
        if (!params.isEmpty()&&params.size()==2){
            MediaFiles media;
            try {
                media = RepFactory.getInstance().getMediaFiles(params.get(0));
                RepFactory.getInstance().move(media,params.get(1));
                System.out.println("new file path "+params.get(1));
            } catch (RepException e) {
                System.err.println("error:"+e.getMessage());
            }
        }else {
            System.err.println("not found parametrs");
        }
    }
}