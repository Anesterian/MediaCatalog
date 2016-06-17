package MediaCatalog.consolecommands.commands;

import java.io.IOException;
import java.util.List;

import MediaCatalog.MediaFiles;
import MediaCatalog.Utils.IOUtils;
import MediaCatalog.Utils.RepFactory;
import MediaCatalog.consolecommands.Operation;
import MediaCatalog.services.RepException;

public class Put implements Operation {
    @Override
    public String getName() {
        return "put";
    }

    @Override
    public void showHelp() {
        System.out.println("put {path to media file} [path to media catalog]");
    }


    private boolean emptyParams(List<String> params){
        if (params.isEmpty()){
            System.err.println("Parametrs empty");
            return true;
        }
      return false;
    }

    private boolean toMuchParams(List<String> params){
        if (params.size()>2){
            System.err.println("to much params");
            return true;
        }
        return false;
    }
    @Override
    public void execute(List<String> params) {
            if (emptyParams(params)||toMuchParams(params)) return;
            MediaFiles media;
            try {
                media=IOUtils.inputMediaFiles(params.get(0));
                if (params.size()==2)
                    media.setCatalog(params.get(1));
                RepFactory.getInstance().add(media);
                System.out.println("media file added");
            }
            catch (IOException e) {
                System.err.println("file didn't read: "+e.getMessage());
            } catch (RepException e) {
                System.err.println("file didn't add: "+e.getMessage());
            }

    }
}