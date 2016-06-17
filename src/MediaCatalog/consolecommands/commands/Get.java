package MediaCatalog.consolecommands.commands;

import java.io.IOException;
import java.util.List;

import MediaCatalog.MediaFiles;
import MediaCatalog.Utils.IOUtils;
import MediaCatalog.Utils.RepFactory;
import MediaCatalog.consolecommands.Operation;
import MediaCatalog.services.RepException;

public class Get implements Operation {
    @Override
    public String getName() {
        return "get";
    }

    @Override
    public void showHelp() {
        System.out.println("get {media path} {file system directory}");
    }

    @Override
    public void execute(List<String> params) {
        if (!params.isEmpty()&&params.size()==2){
            MediaFiles media = null;
            try {
                media = RepFactory.getInstance().getMediaFiles(params.get(0));
                IOUtils.outputMediaFiles(media,params.get(1));
                System.out.println("export successful");
            } catch (RepException e) {
                System.err.println("error readng data:"+e.getMessage());
            }
             catch (IOException e) {
                 System.err.println("error writing to file: "+e.getMessage());
            }

        }else {
            System.err.println("not found parametrs");
        }

    }
}
