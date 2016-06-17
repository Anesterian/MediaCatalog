package MediaCatalog.consolecommands.commands;

import java.util.List;

import MediaCatalog.MediaFiles;
import MediaCatalog.Utils.RepFactory;
import MediaCatalog.consolecommands.Operation;
import MediaCatalog.services.RepException;

public class Delete implements Operation {
    @Override
    public String getName() {
        return "delete";
    }

    @Override
    public void showHelp() {
        System.out.println("delete {path to media}");
    }


    @Override
    public void execute(List<String> params) {
        MediaFiles media= new MediaFiles();
        if (!params.isEmpty()){
            try {
                media.setPath(params.get(0));
                RepFactory.getInstance().delete(media);
                System.out.println("media file deleted");
            } catch (RepException e) {
                System.err.println(e.getMessage());
            }
        }else{
            System.err.println("Params empty");
        }
    }
}
